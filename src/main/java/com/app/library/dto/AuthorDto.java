package com.app.library.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.app.library.model.Author}
 */
@Value
public class AuthorDto implements Serializable {
    String authorFullName;
    String authorIntroduce;
    String authorImageUrl;
}