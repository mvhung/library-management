package com.app.library.controller;

import com.app.library.dto.PublisherDto;
import com.app.library.model.Category;
import com.app.library.model.Publisher;
import com.app.library.payload.PagedResponse;
import com.app.library.service.impl.MapValidationErrorService;
import com.app.library.service.impl.PublisherServiceImpl;
import com.app.library.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @GetMapping
    public PagedResponse<Publisher> getAllPublishers(
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
        return publisherServiceImpl.getAllPublishers(page, size);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updatePublisher(@Valid @PathVariable(name="id") int id,
                                             @Valid @ModelAttribute PublisherDto dto ,
                                             @RequestParam(value = "publisherImageUrl", required = false) MultipartFile publisherImageUrl,
                                             BindingResult result){
        ResponseEntity<?> responseEntity =  mapValidationErrorService.mapValidationFields(result);
        if(responseEntity != null) {
            return responseEntity;
        }
        Publisher updated = publisherServiceImpl.updatePublisher(id,dto,publisherImageUrl);
        return new ResponseEntity<>(updated,HttpStatus.CREATED);
    }
}
