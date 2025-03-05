package com.rcgomez.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Integer id;
    private String email;
    @JsonProperty(value = "first_name")
    private String firstName;
    @JsonProperty(value = "last_name")
    private String lastName;
    private String avatar;
}
