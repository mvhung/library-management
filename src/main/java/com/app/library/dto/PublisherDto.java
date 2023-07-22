package com.app.library.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.app.library.model.Publisher}
 */
@Value
public class PublisherDto implements Serializable {
    String publisherName;
    String publisherIntroduce;
    String publisherWebsiteUrl;
    String publisherImageUrl;
}