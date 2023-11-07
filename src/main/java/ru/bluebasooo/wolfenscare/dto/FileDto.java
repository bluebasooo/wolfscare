package ru.bluebasooo.wolfenscare.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDto {
    private String id;
    private Long length;
}
