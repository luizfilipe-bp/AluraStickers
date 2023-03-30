public enum API {
    IMDBTOPMOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json", new ExtratorConteudoIMDB()), 
    NASAAPOD("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-08-16&end_date=2022-08-18", new ExtratorConteudoNasa()),
    TOPLANGUAGENS("http://localhost:8080/linguagens", new ExtratorConteudoIMDB());

    private String url;
    private ExtratorConteudo extratorConteudo;
    API(String url, ExtratorConteudo extratorConteudo) {
        this.url = url;
        this.extratorConteudo = extratorConteudo;
    }

    public String getUrl(){
        return url;
    }
    public ExtratorConteudo getExtratorConteudo(){
        return extratorConteudo;
    }
}
