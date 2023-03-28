import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer conexão http e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //System.out.println(body);

        // extrair os dados que são necessários (titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        GeradorDeFigurinhas geradorDeFigurinhas = new GeradorDeFigurinhas();
        InputStream inputStream;
        // exibir e manipular os dados
        for(Map<String,String> filme: listaDeFilmes) {
            String titulo = filme.get("title");
            System.out.println("\u001b[1mTítulo: \u001b[m" + titulo);

            double rating = Double.parseDouble(filme.get("imDbRating"));
            for(int i = 0; i < (int)rating; i++) {
                System.out.print("⭐");
            }
            System.out.println();
            System.out.println("\u001b[1mClassificação: \u001b[m" + rating);

            String urlImagem = filme.get("image");
            System.out.println("\u001b[1mURL do poster: \u001b[m" + urlImagem);
            System.out.println();

            inputStream = new URL(urlImagem).openStream();
            titulo += ".png";
            geradorDeFigurinhas.gerarImagem(inputStream, titulo.replace(":", ""));
        }

    }
}
