package br.com.GS.OrbitGuard.API;

import br.com.GS.OrbitGuard.model.NasaFirmsDados;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CsvParserService {

    private final CsvMapper mapper;

    public CsvParserService() {

        mapper = new CsvMapper();

        mapper.registerModule(new JavaTimeModule());
    }

    public List<NasaFirmsDados> fromCsv(String csv)
            throws IOException {

        CsvSchema schema = CsvSchema.emptySchema()
                .withHeader();

        return mapper
                .readerFor(NasaFirmsDados.class)
                .with(schema)
                .<NasaFirmsDados>readValues(csv)
                .readAll();
    }
}