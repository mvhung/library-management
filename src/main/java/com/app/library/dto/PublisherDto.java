package com.app.library.dto;

import com.app.library.model.Publisher;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Publisher}
 */
@Value
public class PublisherDto implements Serializable {
    int publisherId;
    String publisherName;
    String publisherIntroduce;
    String publisherWebsiteUrl;
    String publisherImageUrl;
}