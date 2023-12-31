package ru.bluebasooo.wolfenscare.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Movie {
    @Id
    private String name;
    private LocalDate cameOut;
    private List<String> genres;
    private String description;
}
