//package br.com.GS.OrbitGuard;
//
//import br.com.GS.OrbitGuard.API.CsvParserService;
//import br.com.GS.OrbitGuard.API.HttpClientService;
//import br.com.GS.OrbitGuard.API.JsonParserService;
//import br.com.GS.OrbitGuard.model.*;
//
//import java.io.IOException;
//import java.util.Comparator;
//import java.util.List;
//
//public class Teste {
//    public static void main(String[] args) throws IOException, InterruptedException {
//        HttpClientService consumo = new HttpClientService();
//        CsvParserService conversorCSV = new CsvParserService();
//        JsonParserService conversorJSON = new JsonParserService();
//
//
//        var csv = consumo.get("https://firms.modaps.eosdis.nasa.gov/api/area/csv/55bef5f8a99b05ea51a0d3682f1fd240/VIIRS_SNPP_NRT/world/1");
//        var dadosSatelite = conversorCSV.fromCsv(csv);
//
//        List<NasaFirmsDados> focosImportantes = dadosSatelite.stream()
//                .filter(d -> d.PoderRadiativoFogo() > 5)
//                .sorted(Comparator.comparing(
//                        NasaFirmsDados::PoderRadiativoFogo).reversed())
//                .limit(20)
//                .toList();
//
//        List<FocosIncendiosDTO> focosAltoRisco = focosImportantes.stream()
//                .map(f -> {
//                    try {
//
//                        String url =
//                                "https://api.openweathermap.org/data/2.5/weather?lat="
//                                        + f.latitude()
//                                        + "&lon="
//                                        + f.longitude()
//                                        + "&appid=836505e7f2b2ac92d5e6dfe04ae93cbc";
//
//                        String json = consumo.get(url);
//
//                         var dados = conversorJSON.fromJson(
//                                json,
//                                OpenWeatherDados.class
//                        );
//
//                        FocosIncedios teste = new FocosIncedios(f.latitude(),f.longitude(),dados.country().pais(),dados.localidade(),f.PoderRadiativoFogo(),dados.main().temperatura(),dados.main().umidade(), dados.clouds().nuvens(), dados.wind().speed(), dados.wind().rajadas());
//
//
//                        return new FocosIncendiosDTO(teste);
//
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                })
//                .toList();
//
//        System.out.println(focosAltoRisco);
//
////        package br.com.GS.OrbitGuard.model;
////
////        public class FocosIncendioDTO {
////
////            private NasaFirmsDados dadosNasa;
////
////            private OpenWeatherDados dadosOpenWeather;
////
////            public FocosIncendioDTO(NasaFirmsDados dadosNasa, OpenWeatherDados dadosOpenWeather){
////                this.dadosNasa = dadosNasa;
////                this.dadosOpenWeather = dadosOpenWeather;
////            }
////        }
//
//
//    }
//}
