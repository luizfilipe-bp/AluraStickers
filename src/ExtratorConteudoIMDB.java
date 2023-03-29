import java.util.List;
import java.util.Map;

public class ExtratorConteudoIMDB implements ExtratorConteudo{
    @Override
    public List<Conteudo> extrairConteudos(String json) {
        // extrair os dados que são necessários (titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        return listaDeAtributos.stream()
            .map(atributos -> new Conteudo(atributos.get("image"), atributos.get("title")))
            .toList();


        /*
        List<Conteudo> conteudos = new ArrayList<>(); 

        for (Map<String, String> atributos : listaDeAtributos) {
            String titulo = atributos.get("title");
            String url = atributos.get("image");  
            Conteudo conteudo = new Conteudo(url, titulo);

            conteudos.add(conteudo);
        }

        return conteudos;
         */
    }
}
