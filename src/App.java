import java.net.http.HttpClient;
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        // fazer conexão http e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        HttpClient client = HttpClient.newHttpClient();
    
        // extrair os dados que são necessários (titulo, poster, classificação)
        // exibir e manipular os dados


    }
}
