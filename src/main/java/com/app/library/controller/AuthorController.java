package com.app.library.controller;
import com.app.library.dto.AuthorDto;
import com.app.library.model.Author;
import com.app.library.model.Publisher;
import com.app.library.payload.PagedResponse;
import com.app.library.service.impl.AuthorServiceImpl;
import com.app.library.service.impl.MapValidationErrorService;
import com.app.library.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/api/v1/authors")
public class AuthorController {

    @Autowired
    AuthorServiceImpl authorService;

    @Autowired
    MapValidationErrorService mapValidationErrorService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable("id") int id) {
        return authorService.getAuthor(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAuthorById(@PathVariable("id") int id) {
        return authorService.deleteAuthor(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateAuthor(@Valid @PathVariable(name="id") int id,
                                          @Valid @ModelAttribute AuthorDto dto,
                                          @RequestParam(value = "authorImageUrl", required = false) MultipartFile authorImageUrl,
                                          BindingResult result) {
        ResponseEntity<?> responseEntity =  mapValidationErrorService.mapValidationFields(result);
        if(responseEntity != null) {
            return responseEntity;
        }
        Author updated = authorService.updateAuthor(id, dto, authorImageUrl);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping
    public PagedResponse<Author> getAllPublishers(
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
        return authorService.getAllAuthors(page, size);
    }
}
