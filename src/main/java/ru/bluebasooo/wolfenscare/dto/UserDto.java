package ru.bluebasooo.wolfenscare.dto;

import lombok.*;
import ru.bluebasooo.wolfenscare.entity.MovieInfo;

import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String firstname;
    private String lastname;
    private List<MovieInfo> seenMovies;
}
