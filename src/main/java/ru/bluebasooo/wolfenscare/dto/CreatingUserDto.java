package ru.bluebasooo.wolfenscare.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatingUserDto {
    private String username;
    private String firstname;
    private String lastname;

}
