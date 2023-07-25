package com.app.library.exception;

import com.app.library.exception.book.BookException;
import com.app.library.exception.book.BookExceptionResponse;
import com.app.library.exception.category.CategoryException;
import com.app.library.exception.category.CategoryExceptionResponse;
import com.app.library.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CategoryException.class)
//    chua thong tin anh xa message voi noi dung exception
    public final ResponseEntity<Object> handleCategoryException(CategoryException ex, WebRequest request) {
        CategoryExceptionResponse exceptionResponse = new CategoryExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BookException.class)
    public final ResponseEntity<Object> handleBookException(BookException ex, WebRequest request) {
        BookExceptionResponse exceptionResponse = new BookExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
