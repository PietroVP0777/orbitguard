package br.com.GS.OrbitGuard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Locale;

@Entity
@NoArgsConstructor
public class FocosIncendios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nivelPerigo;
    private double latitude;
    private double longitude;
    private String pais;
    private String localidade;
    private double poderRadiativoFogo;
    private double temperaturaCelsius;
    private double umidade;
    private double nuvens;
    private double velocidadeVento;
    private double rajadasVento;
    private LocalDate data;


    public FocosIncendios(double latitude, double longitude, String pais, String localidade, double poderRadiativoFogo, double temperaturaCelsius, double umidade, double nuvens, double velocidadeVento, double rajadasVento, LocalDate data) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.pais =  traduzirLocal(pais);
        this.localidade = localidade;
        this.poderRadiativoFogo = poderRadiativoFogo;
        this.temperaturaCelsius = temperaturaCelsius - 273.15;
        this.umidade = umidade;
        this.nuvens = nuvens;
        this.velocidadeVento = velocidadeVento;
        this.rajadasVento = rajadasVento;
        this.nivelPerigo = calcularPerigo();
        this.data = data;
    }

    private String calcularPerigo() {

        int score = 0;

        if (poderRadiativoFogo > 50) {
            score += 35;
        } else if (poderRadiativoFogo > 20) {
            score += 20;
        } else {
            score += 10;
        }

        if (temperaturaCelsius >= 40) {
            score += 25;
        } else if (temperaturaCelsius >= 30) {
            score += 15;
        } else if (temperaturaCelsius >= 20) {
            score += 5;
        }

        if (umidade < 20) {
            score += 25;
        } else if (umidade < 40) {
            score += 15;
        } else if (umidade < 60) {
            score += 5;
        }

        if (nuvens < 20) {
            score += 10;
        } else if (nuvens < 50) {
            score += 5;
        }

        if (velocidadeVento > 12) {
            score += 15;
        } else if (velocidadeVento > 7) {
            score += 8;
        }

        if (rajadasVento > 20) {
            score += 15;
        } else if (rajadasVento > 10) {
            score += 8;
        }

        if (score >= 80) {
            return "EXTREMO";
        } else if (score >= 45) {
            return "MÉDIO";
        } else {
            return "BAIXO";
        }
    }

    private String traduzirLocal(String sigla){
        Locale locale = new Locale("",sigla);
        return locale.getDisplayCountry(new Locale("pt", "BR"));
    }

    public Long getId() {
        return id;
    }

    public String getNivelPerigo() {
        return nivelPerigo;
    }

    public void setNivelPerigo(String nivelPerigo) {
        this.nivelPerigo = nivelPerigo;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public double getPoderRadiativoFogo() {
        return poderRadiativoFogo;
    }

    public void setPoderRadiativoFogo(double poderRadiativoFogo) {
        this.poderRadiativoFogo = poderRadiativoFogo;
    }

    public double getTemperaturaCelsius() {
        return temperaturaCelsius;
    }

    public void setTemperaturaCelsius(double temperaturaCelsius) {
        this.temperaturaCelsius = temperaturaCelsius;
    }

    public double getUmidade() {
        return umidade;
    }

    public void setUmidade(double umidade) {
        this.umidade = umidade;
    }

    public double getNuvens() {
        return nuvens;
    }

    public void setNuvens(double nuvens) {
        this.nuvens = nuvens;
    }

    public double getVelocidadeVento() {
        return velocidadeVento;
    }

    public void setVelocidadeVento(double velocidadeVento) {
        this.velocidadeVento = velocidadeVento;
    }

    public double getRajadasVento() {
        return rajadasVento;
    }

    public void setRajadasVento(double rajadasVento) {
        this.rajadasVento = rajadasVento;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
