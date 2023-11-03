package ru.bluebasooo.wolfenscare.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document("users")
public class User {
    @Id
    private String username;
    private String firstname;
    private String lastname;
    private List<MovieInfo> seenMovies;
}
