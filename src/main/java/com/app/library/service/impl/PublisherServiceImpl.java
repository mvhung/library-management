package com.app.library.service.impl;

import com.app.library.dto.PublisherDto;
import com.app.library.exception.object.ObjectException;
import com.app.library.model.Category;
import com.app.library.model.Publisher;
import com.app.library.payload.PagedResponse;
import com.app.library.repository.PublisherRepository;
import com.app.library.service.IPublisherService;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements IPublisherService {

    @Autowired
    private PublisherRepository publisherRepository;
    @Override
    public ResponseEntity<?> getPublisher(int id) {
        try {
            Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("can't find loan id:" + id));
            return new ResponseEntity<>(publisher, HttpStatus.OK);
        } catch (Exception e) {
            throw  new ObjectException("No get detail publisher");
        }
    }

    @Override
    public PagedResponse<Publisher> getAllPublishers(int page, int size) {
        AppUtils.validatePageNumberAndSize(page, size);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");

        Page<Publisher> publishers = publisherRepository.findAll(pageable);

        List<Publisher> content = publishers.getNumberOfElements() == 0 ? Collections.emptyList() : publishers.getContent();

        return new PagedResponse<>(content, publishers.getNumber(), publishers.getSize(), publishers.getTotalElements(),
                publishers.getTotalPages(), publishers.isLast());
    }

    @Override
    public Publisher updatePublisher(int id, PublisherDto dto) {
        Publisher publisher = new Publisher();
        BeanUtils.copyProperties(dto,publisher);
        publisher = update(id,publisher);
        dto.setPublisherId(publisher.getPublisherId());
        return publisher;
    }

    private Publisher update(int id, Publisher publisher) {
        Optional<Publisher> existed = publisherRepository.findById(id);
        if(existed.isEmpty()) {
            throw new ObjectException("Publisher is " + id + "does't exist");
        }
        try {
            Publisher existedPublisher = existed.get();
            existedPublisher.setPublisherName(publisher.getPublisherName());
            existedPublisher.setPublisherIntroduce(publisher.getPublisherIntroduce());
            existedPublisher.setPublisherWebsiteUrl(publisher.getPublisherWebsiteUrl());
            existedPublisher.setPublisherImageUrl(publisher.getPublisherImageUrl());
            return publisherRepository.save(existedPublisher);
        } catch (Exception e) {
            throw new ObjectException("Publisher is updated failed");
        }
    }

}
