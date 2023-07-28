package com.app.library.controller;

import com.app.library.dto.PublisherDto;
import com.app.library.model.Publisher;
import com.app.library.service.impl.MapValidationErrorService;
import com.app.library.service.impl.PublisherServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publishers")
public class PublisherController {
    @Autowired
    PublisherServiceImpl publisherServiceImpl;
    @Autowired
    MapValidationErrorService mapValidationErrorService;
    @RequestMapping("{id}")
    public ResponseEntity<?> getPublisherById(@PathVariable(name="id") int id) {
        ResponseEntity<?> responseEntity = publisherServiceImpl.getPublisher(id);
        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }
    @RequestMapping
    public ResponseEntity<List<Publisher>> getAllPublisher() {
        List<Publisher> publishers =  publisherServiceImpl.getAllPublisher();
        return new ResponseEntity<>(publishers,HttpStatus.OK);
    }
    @PatchMapping("{id}")
    public ResponseEntity<?> updatePublisher(@Valid @PathVariable(name="id") int id,
                                             @RequestBody PublisherDto dto , BindingResult result){
        ResponseEntity<?> responseEntity =  mapValidationErrorService.mapValidationFields(result);
        if(responseEntity != null) {
            return responseEntity;
        }
        Publisher updated = publisherServiceImpl.updatePublisher(id,dto);
        return new ResponseEntity<>(updated,HttpStatus.CREATED);
    }
}
