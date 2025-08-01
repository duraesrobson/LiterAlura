package br.com.alura.literalura.literalura.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversorDeJson implements IConversorDeJson {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T converterJsonParaObjeto(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
