package ru.bluebasooo.wolfenscare.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MediaFile {
    @Id
    private String name;
    private byte[] payload;
}
