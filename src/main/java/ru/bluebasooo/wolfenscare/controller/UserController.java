package ru.bluebasooo.wolfenscare.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bluebasooo.wolfenscare.entity.Movie;
import ru.bluebasooo.wolfenscare.entity.User;
import ru.bluebasooo.wolfenscare.service.UserService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/{username}/movie")
    public List<Movie> getUsersSeenFilms(@PathVariable String username) {
        return userService.getMoviesByUserName(username);
    }
}
