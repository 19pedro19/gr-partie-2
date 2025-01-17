package org.example.videoservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoRequest {
    private String name;
    private String url;
    private String description;
    private Date datePublication;
    private Creator creator;
}
