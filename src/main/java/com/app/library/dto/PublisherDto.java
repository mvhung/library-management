package com.app.library.dto;

import com.app.library.model.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Publisher}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherDto implements Serializable {
    int publisherId;
    String publisherName;
    String publisherIntroduce;
    String publisherWebsiteUrl;
    String publisherImageUrl;
}