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
public class PetDTO {
    private Long id;
    @JsonProperty(value = "category")
    private CategoryDTO categoryDTO;
    private String name;
    private List<String> photoUrls;
    @JsonProperty(value = "tags")
    private List<TagsDTO> tagsDTO;
    private String status;
}
