package br.com.GS.OrbitGuard.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenWeatherMainDTO(
        @JsonProperty("temp")
        double temperatura,

        @JsonProperty("humidity")
        double umidade,

        @JsonProperty("pressure")
        double pressao
) {
    public double temperaturaCelsius() {
        return temperatura - 273.15;
    }
}
