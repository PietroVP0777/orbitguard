package br.com.GS.OrbitGuard.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NasaFirmsDados(
        double latitude,

        double longitude,

        @JsonAlias("bright_ti4")
        double IntensidadeTermica,

        @JsonAlias("confidence")
        String Confianca,

        @JsonAlias("frp")
        double PoderRadiativoFogo,

        @JsonAlias("acq_date")
        LocalDate Data
) {
}
