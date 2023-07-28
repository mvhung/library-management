package com.app.library.service.impl;

import com.app.library.dto.PublisherDto;
import com.app.library.exception.object.ObjectException;
import com.app.library.model.Publisher;
import com.app.library.repository.PublisherRepository;
import com.app.library.service.IPublisherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public List<Publisher> getAllPublisher() {
        return publisherRepository.findAll();
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
