package ru.bluebasooo.wolfenscare.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieInfo {
    private String name;
    private LocalDate cameOut;
    private List<String> genres;
}
