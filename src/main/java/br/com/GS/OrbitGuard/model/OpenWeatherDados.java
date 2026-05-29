package br.com.GS.OrbitGuard.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenWeatherDados(

        @JsonProperty("weather")
        List<OpenWeatherWeatherDTO> weather,

        @JsonProperty("main")
        OpenWeatherMainDTO main,

        @JsonProperty("wind")
        OpenWeatherWindDTO wind,

        @JsonProperty("clouds")
        OpenWeatherCloudsDTO clouds,

        @JsonProperty("sys")
        OpenWeatherCountryDTO country,

        @JsonProperty("name")
        String localidade

) {
}
