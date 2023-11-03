package ru.bluebasooo.wolfenscare.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bluebasooo.wolfenscare.dto.CreatingUserDto;
import ru.bluebasooo.wolfenscare.dto.UserDto;
import ru.bluebasooo.wolfenscare.entity.MovieInfo;
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
    public List<MovieInfo> getUsersSeenFilms(@PathVariable String username) {
        return userService.getMoviesByUserName(username);
    }

    @PostMapping("/")
    public UserDto createUser(@RequestBody CreatingUserDto creatingUserDto) {
        return userService.createUser(creatingUserDto);
    }
}
