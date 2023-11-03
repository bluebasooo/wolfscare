package ru.bluebasooo.wolfenscare.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bluebasooo.wolfenscare.dto.CreatingUserDto;
import ru.bluebasooo.wolfenscare.dto.UserDto;
import ru.bluebasooo.wolfenscare.entity.MovieInfo;
import ru.bluebasooo.wolfenscare.entity.User;
import ru.bluebasooo.wolfenscare.exception.InvalidInputDataException;
import ru.bluebasooo.wolfenscare.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        var user = userRepository.findById(username);
        return user.get();
    }

    public List<MovieInfo> getMoviesByUserName(String username) {
        var user = userRepository.findById(username);

        return user.map(User::getSeenMovies)
                   .orElse(List.of());
    }

    public UserDto createUser(CreatingUserDto creatingUserDto) {
        var user = creatingUserDtoToUser.apply(creatingUserDto)
                .orElseThrow(() -> new InvalidInputDataException("Invalid data for creating"));

        var insertedUser = userRepository.insert(user);

        return userToUserDto.apply(insertedUser);
    }

    private static final Function<CreatingUserDto, Optional<User>> creatingUserDtoToUser = (creatingUserDto) -> {
        return Optional.of(
                User.builder()
                    .username(creatingUserDto.getUsername())
                    .firstname(creatingUserDto.getFirstname())
                    .lastname(creatingUserDto.getLastname())
                    .seenMovies(List.of())
                    .build());
    };

    private static final Function<User, UserDto> userToUserDto = (user) -> {
        return UserDto.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .seenMovies(user.getSeenMovies())
                .build();
    };
}
