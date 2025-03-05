package com.rcgomez.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserResponseDTO {

    private String name;
    private String job;
    private Integer id;
    private String createdAt;
}

