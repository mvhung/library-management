package com.app.library.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.app.library.model.Author}
 */
@Data
@AllArgsConstructor
public class AuthorDto implements Serializable {
    String authorFullName;
    String authorIntroduce;
    String authorImageUrl;
}