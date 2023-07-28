package com.app.library.service;

import com.app.library.dto.PublisherDto;
import com.app.library.model.Publisher;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPublisherService {
    public ResponseEntity<?> getPublisher(int id);
    public List<Publisher> getAllPublisher();
    public Publisher updatePublisher(int id, PublisherDto dto);

}
