package br.com.GS.OrbitGuard.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenWeatherWeatherDTO(
        @JsonProperty("main")
        String clima
) {
}
