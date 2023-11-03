package ru.bluebasooo.wolfenscare.it;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.bluebasooo.wolfenscare.dto.CreatingUserDto;
import ru.bluebasooo.wolfenscare.dto.UserDto;
import ru.bluebasooo.wolfenscare.entity.User;
import ru.bluebasooo.wolfenscare.repository.UserRepository;
import ru.bluebasooo.wolfenscare.service.UserService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Container
    public static GenericContainer<?> mongoDB = new GenericContainer<>("mongo:latest")
            .withExposedPorts(27017)
            .withEnv("MONGO_INITDB_ROOT_USERNAME", "admin")
            .withEnv("MONGO_INITDB_ROOT_PASSWORD", "admin");

    @DynamicPropertySource
    public static void mongoDBProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.host", mongoDB::getHost);
        registry.add("spring.data.mongodb.port", mongoDB::getFirstMappedPort);
        registry.add("spring.data.mongodb.database", () -> "users");
        registry.add("spring.data.mongodb.username", () -> "admin");
        registry.add("spring.data.mongodb.password", () -> "admin");
    }

    @Test
    public void testGetUserByUsername() {
        //given
        var userToStore = User.builder()
                .username("noname")
                .firstname("noname")
                .lastname("noname")
                .seenMovies(List.of())
                .build();

        userRepository.insert(userToStore);

        //when
        var selectedUser = userService.getUserByUsername("noname");


        //then
        assertThat(selectedUser).isEqualTo(userToStore);
    }

    @Test
    public void testCreateUser() {
        //given
        var creatingUserDto = CreatingUserDto.builder()
                .username("noname")
                .firstname("noname")
                .lastname("noname")
                .build();
        var expectedUser = User.builder()
                .username("noname")
                .firstname("noname")
                .lastname("noname")
                .seenMovies(List.of())
                .build();
        var expectedUserDto = UserDto.builder()
                .firstname("noname")
                .lastname("noname")
                .seenMovies(List.of())
                .build();

        //when
        var userDto = userService.createUser(creatingUserDto);

        //then
        assertThat(userRepository.findById("noname"))
                .isNotEmpty()
                .contains(expectedUser);

        assertThat(userDto).isEqualTo(expectedUserDto);
    }
}
