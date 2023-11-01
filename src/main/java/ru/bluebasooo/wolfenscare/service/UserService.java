package ru.bluebasooo.wolfenscare.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bluebasooo.wolfenscare.entity.Movie;
import ru.bluebasooo.wolfenscare.entity.User;
import ru.bluebasooo.wolfenscare.repository.UserRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        var user = userRepository.findById(username);
        return user.get();
    }

    public List<Movie> getMoviesByUserName(String username) {
        var user = userRepository.findById(username);

        return user.map(User::getSeenMovies)
                   .orElse(List.of());
    }
}
