package com.app.library.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.app.library.model.Publisher}
 */
@Data
@AllArgsConstructor
public class PublisherDto implements Serializable {
    String publisherName;
    String publisherIntroduce;
    String publisherWebsiteUrl;
    String publisherImageUrl;
}