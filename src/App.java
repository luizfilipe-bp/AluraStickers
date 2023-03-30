import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer conexão http e buscar informações
        API api = API.TOPLANGUAGENS;
        String url = api.getUrl();

        ClienteHttp clienteHttp = new ClienteHttp();
        String json = clienteHttp.buscarDados(url);
        
        // exibir e manipular os dados
        ExtratorConteudo extrator = api.getExtratorConteudo();
        List<Conteudo> conteudos = extrator.extrairConteudos(json);
        
        InputStream inputStream;
        GeradorDeFigurinhas geradorDeFigurinhas = new GeradorDeFigurinhas();
        File diretorio = new File("saida");
        diretorio.mkdir();
        for(Conteudo conteudo: conteudos) {
            String titulo = conteudo.titulo();
            System.out.println("\u001b[1mTítulo: \u001b[m" + titulo);


            //double rating = Double.parseDouble(conteudo.get("imDbRating"));
            //for(int i = 0; i < (int)rating; i++) {
            //    System.out.print("⭐");
            //}
            //System.out.println();
            //System.out.println("\u001b[1mClassificação: \u001b[m" + rating);


            String urlImagem = conteudo.urlImagem();
            System.out.println("\u001b[1mURL do poster: \u001b[m" + urlImagem);
            System.out.println();

            inputStream = new URL(urlImagem).openStream();
            titulo = "./" + diretorio.getName() + "/" + titulo + ".png";
            geradorDeFigurinhas.gerarImagem(inputStream, titulo.replace(":", ""));
        }

    }
}
