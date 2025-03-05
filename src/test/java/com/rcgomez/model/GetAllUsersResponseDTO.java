package com.rcgomez.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAllUsersResponseDTO {

    private Integer page;
    @JsonProperty(value = "per_page")
    private Integer perPage;
    private int total;
    @JsonProperty(value = "total_pages")
    private Integer totalPages;
    @JsonProperty(value = "data")
    private List<UserDTO> users;
}
