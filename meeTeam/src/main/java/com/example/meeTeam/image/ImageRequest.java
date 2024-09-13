package com.example.meeTeam.image;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageRequest {

    private Long id;
    private String url;
    private Object imageUrl;

    public ImageRequest(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    public static ImageRequest fromEntity(Image image) {
        return new ImageRequest(image.getId(), image.getUrl());
    }

    public Image toEntity() {
        Image image = new Image();
        setUrl(this.url);
        return image;
    }
}
