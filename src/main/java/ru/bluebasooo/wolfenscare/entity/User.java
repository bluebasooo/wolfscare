package ru.bluebasooo.wolfenscare.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("users")
public class User {
    @Id
    private String username;
    private String firstName;
    private String lastName;
    private List<Movie> seenMovies;
}
