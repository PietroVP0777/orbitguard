package br.com.GS.OrbitGuard.API;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsonParserService {

    private final ObjectMapper mapper =
            new ObjectMapper();

    public <T> T fromJson(String json, Class<T> responseType)
            throws IOException {

        return mapper.readValue(json, responseType);
    }
}