package br.com.alura.literalura.literalura.util;

public interface IConversorDeJson {
    <T> T converterJsonParaObjeto(String json, Class<T> classe);
}
