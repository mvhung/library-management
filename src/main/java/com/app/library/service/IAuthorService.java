package com.app.library.service;

import com.app.library.dto.AuthorDto;
import com.app.library.model.Author;
import com.app.library.payload.PagedResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAuthorService {
    public ResponseEntity<?> getAuthor(int id);

    public PagedResponse<Author> getAllAuthors(int page, int size);

    public Author updateAuthor(int id, AuthorDto dto, MultipartFile authorImageUrl);

    public ResponseEntity<?> deleteAuthor(int id);

}
