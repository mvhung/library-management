package com.app.library.controller;
import com.app.library.dto.AuthorDto;
import com.app.library.model.Author;
import com.app.library.model.Publisher;
import com.app.library.payload.PagedResponse;
import com.app.library.service.impl.AuthorServiceImpl;
import com.app.library.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/authors")
public class AuthorController {

    @Autowired
    AuthorServiceImpl authorService;

    @GetMapping("/info/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable("id") int id) {
        return authorService.getAuthor(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteAuthorById(@PathVariable("id") int id) {
        return authorService.deleteAuthor(id);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable("id") int id, @RequestBody AuthorDto author) {
        authorService.updateAuthor(author);
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(author, HttpStatus.OK);
    }


    @GetMapping
    public PagedResponse<Author> getAllPublishers(
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
        return authorService.getAllAuthors(page, size);
    }
}
