package com.app.library.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.app.library.model.Publisher;
import com.app.library.payload.PagedResponse;
import com.app.library.utils.AppUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.library.dto.AuthorDto;
import com.app.library.exception.object.ObjectException;
import com.app.library.model.Author;
import com.app.library.repository.AuthorRepository;
import com.app.library.service.IAuthorService;

@Service
public class AuthorServiceImpl implements IAuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public ResponseEntity<?> getAuthor(int id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("can't find author id:" + id));
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @Override
    public PagedResponse<Author> getAllAuthors(int page, int size) {
        AppUtils.validatePageNumberAndSize(page, size);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");

        Page<Author> authors = authorRepository.findAll(pageable);

        List<Author> content = authors.getNumberOfElements() == 0 ? Collections.emptyList() : authors.getContent();

        return new PagedResponse<>(content, authors.getNumber(), authors.getSize(), authors.getTotalElements(),
                authors.getTotalPages(), authors.isLast());
    }

    @Override
    public ResponseEntity<?> updateAuthor(AuthorDto dto) {
        try {
            Optional<Author> existAuthor = authorRepository.findById(dto.getAuthorId());

            if (existAuthor.isPresent()) {
                Author authorUpdate = existAuthor.get();
                authorUpdate.setAuthorFullName(authorUpdate.getAuthorFullName() + dto.getAuthorFullName());
                authorUpdate.setAuthorIntroduce(authorUpdate.getAuthorIntroduce() + dto.getAuthorIntroduce());
                authorUpdate.setAuthorImageUrl(authorUpdate.getAuthorImageUrl() + dto.getAuthorImageUrl());
                authorUpdate = save(authorUpdate); // Lưu cập nhật vào cơ sở dữ liệu
                BeanUtils.copyProperties(authorUpdate, dto);
                return new ResponseEntity<>(dto, HttpStatus.CREATED);
            }

            Author newAuthor = new Author();
            BeanUtils.copyProperties(dto, newAuthor);
            newAuthor = save(newAuthor);
            dto.setAuthorId(newAuthor.getAuthorId());
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log thông tin lỗi
            e.printStackTrace();
            throw new ObjectException("Author creation failed ");
        }
    }

    @Override
    public ResponseEntity<?> deleteAuthor(int id) {
        try {
            Author existed = authorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("can't find author id:" + id));
            authorRepository.delete(existed);
            return new ResponseEntity<>("Author with Id " + id + " was deleted", HttpStatus.OK);
        } catch (Exception e) {
            throw new ObjectException("deleted author failed ");
        }
    }
}
