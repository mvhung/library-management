package com.app.library.controller;
import com.app.library.dto.AuthorDto;
import com.app.library.model.Author;
import com.app.library.model.Book;
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
    private AuthorServiceImpl authorServiceImpl;

    @Autowired
    MapValidationErrorService mapValidationErrorService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable("id") int id) {
        return authorServiceImpl.getAuthor(id);
    }


    @GetMapping
    public PagedResponse<Author> getAllPublishers(
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
        return authorServiceImpl.getAllAuthors(page, size);
    }

    @GetMapping("/books/{id}")
    public PagedResponse<Book> getBooksByAuthorId(
            @Valid @PathVariable(name = "id") int id,
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size
    ) {
        PagedResponse<Book> pagedResponse = authorServiceImpl.getBooksByAuthorId(id, page, size);
        return pagedResponse;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAuthorById(@PathVariable("id") int id) {
        return authorServiceImpl.deleteAuthor(id);
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
        Author updated = authorServiceImpl.updateAuthor(id, dto, authorImageUrl);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
