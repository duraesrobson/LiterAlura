package br.com.alura.literalura.literalura.model;

public enum Idioma {
    pt("[pt]", "Português"),
    en("[en]", "Inglês"),
    fr("[fr]", "Francês"),
    es("[es]", "Espanhol"),
    de("[de]", "Alemão"),
    it("[it]", "Italiano"),
    nl("[nl]", "Holandês"),
    ru("[ru]", "Russo"),
    sv("[sv]", "Sueco"),
    fi("[fi]", "Finlandês"),
    da("[da]", "Dinamarquês"),
    cs("[cs]", "Tcheco"),
    pl("[pl]", "Polonês"),
    ja("[ja]", "Japonês"),
    zh("[zh]", "Chinês"),
    ar("[ar]", "Árabe"),
    el("[el]", "Grego"),
    hi("[hi]", "Hindi"),
    DESCONHECIDO("desconhecido", "Desconhecido");

    private String idiomaGutendex;
    private String idiomaBrasileiro;

    Idioma(String idiomaGutendex, String idiomaBrasileiro){
        this.idiomaGutendex = idiomaGutendex;
        this.idiomaBrasileiro = idiomaBrasileiro;
    }

    public static Idioma fromString(String text){
        for(Idioma idioma: Idioma.values()){
            if (idioma.idiomaGutendex.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        return Idioma.DESCONHECIDO;
    }

    public static Idioma fromBrasileiro(String text){
        for(Idioma idioma : Idioma.values()){
            if (idioma.idiomaBrasileiro.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        return Idioma.DESCONHECIDO;
    }

    public String getIdiomaGutendex(){
        return idiomaGutendex;
    }

    public String getIdiomaBrasileiro(){
        return idiomaBrasileiro;
    }
    

}
