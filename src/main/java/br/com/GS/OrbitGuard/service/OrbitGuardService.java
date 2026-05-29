package br.com.GS.OrbitGuard.service;

import br.com.GS.OrbitGuard.API.CsvParserService;
import br.com.GS.OrbitGuard.API.HttpClientService;
import br.com.GS.OrbitGuard.API.JsonParserService;
import br.com.GS.OrbitGuard.exception.ResourceNotFoundException;
import br.com.GS.OrbitGuard.model.FocosIncendios;
import br.com.GS.OrbitGuard.model.FocosIncendiosDTO;
import br.com.GS.OrbitGuard.model.NasaFirmsDados;
import br.com.GS.OrbitGuard.model.OpenWeatherDados;
import br.com.GS.OrbitGuard.repository.OrbitGuardRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class OrbitGuardService {

    private final OrbitGuardRepository repository;
    private final HttpClientService consumo;
    private final CsvParserService conversorCSV;
    private final JsonParserService conversorJSON;

    public OrbitGuardService(OrbitGuardRepository repository, HttpClientService consumo, CsvParserService conversorCSV, JsonParserService conversorJSON) {
        this.repository = repository;
        this.consumo = consumo;
        this.conversorCSV = conversorCSV;
        this.conversorJSON = conversorJSON;
    }

    public void importarFocos() throws IOException, InterruptedException {

        var csv = consumo.get("https://firms.modaps.eosdis.nasa.gov/api/area/csv/{API_KEY}/VIIRS_SNPP_NRT/world/1");

        var dadosSatelite = conversorCSV.fromCsv(csv);

        List<NasaFirmsDados> focosImportantes = dadosSatelite.stream()
                .filter(d -> d.PoderRadiativoFogo() > 5)
                .sorted(Comparator.comparing(
                        NasaFirmsDados::PoderRadiativoFogo).reversed())
                .limit(20)
                .toList();

        focosImportantes.forEach(f -> {

            try {

                String url =
                        "https://api.openweathermap.org/data/2.5/weather?lat="
                                + f.latitude()
                                + "&lon="
                                + f.longitude()
                                + "&appid={SUA_APIKEY}";

                String json = consumo.get(url);

                var dados = conversorJSON.fromJson(
                        json,
                        OpenWeatherDados.class
                );

                FocosIncendios foco = new FocosIncendios(
                        f.latitude(),
                        f.longitude(),
                        dados.country().pais(),
                        dados.localidade(),
                        f.PoderRadiativoFogo(),
                        dados.main().temperatura(),
                        dados.main().umidade(),
                        dados.clouds().nuvens(),
                        dados.wind().speed(),
                        dados.wind().rajadas(),
                        f.Data()
                );

                Optional<FocosIncendios> existente = repository.findByLatitudeAndLongitude(foco.getLatitude(), foco.getLongitude());{
                    if(existente.isPresent()) {
                        FocosIncendios focoBanco = existente.get();
                        focoBanco.setNivelPerigo(foco.getNivelPerigo());
                        focoBanco.setPoderRadiativoFogo(foco.getPoderRadiativoFogo());
                        focoBanco.setTemperaturaCelsius(foco.getTemperaturaCelsius());
                        focoBanco.setUmidade(foco.getUmidade());
                        focoBanco.setNuvens(foco.getNuvens());
                        focoBanco.setVelocidadeVento(foco.getVelocidadeVento());
                        focoBanco.setRajadasVento(foco.getRajadasVento());

                        repository.save(focoBanco);

                    } else {

                        repository.save(foco);
                    }
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public FocosIncendiosDTO converterDTO(FocosIncendios f){
        return new FocosIncendiosDTO(f.getId(),f.getNivelPerigo(),f.getLatitude(),f.getLongitude(),f.getPais(),f.getLocalidade(),f.getPoderRadiativoFogo(),f.getTemperaturaCelsius(),f.getUmidade(),f.getNuvens(),f.getVelocidadeVento(),f.getRajadasVento(),f.getData());
    }

    public List<FocosIncendiosDTO> listarFocos() {

        return repository.findAll()
                .stream()
                .sorted((f1,f2) -> f2.getData().compareTo(f1.getData()))
                .map(this::converterDTO)
                .toList();
    }

    public List<FocosIncendiosDTO> buscarFocosPais(String pais){
        List<FocosIncendiosDTO> focos = repository.findByPais(pais)
                .stream()
                .sorted((f1, f2) -> f2.getData().compareTo(f1.getData()))
                .map(this::converterDTO)
                .toList();


        if(focos.isEmpty()){
            throw new ResourceNotFoundException(
                    "Nenhum foco encontrado para o país: " + pais
            );
        }

        return focos;
    }

}
