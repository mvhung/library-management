package com.app.library.service;

import com.app.library.dto.AuthorDto;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IAuthorService {
    public ResponseEntity<?> getAuthor(int id);

    public ResponseEntity<List<String>> listAuthorNames();

    public ResponseEntity<?> updateAuthor(AuthorDto dto);

    public ResponseEntity<?> deleteAuthor(int id);

}
