package br.com.GS.OrbitGuard.model;

import java.time.LocalDate;

public record FocosIncendiosDTO(
        Long id,
        String nivelPerigo,
        double latitude,
        double longitude,
        String pais,
        String localidade,
        double poderRadiativoFogo,
        double temperaturaCelsius,
        double umidade,
        double nuvens,
        double velocidadeVento,
        double rajadasVento,
        LocalDate data
){
}
