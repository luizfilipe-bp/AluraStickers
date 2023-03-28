import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {
    public void gerarImagem(InputStream inputStream, String nomeArquivo) throws Exception {
        //ler imagem
        //InputStream inputStream = new FileInputStream(new File("./img/filme.jpg"));
        //InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //criar imagem em memória com transparencia e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage imagemNova = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar imagem original para o nova imagem
        Graphics2D graphics = (Graphics2D)imagemNova.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //configurar fonte
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setFont(fonte);
        graphics.setColor(Color.YELLOW);

        //escrever uma frase na nova imagem 
        graphics.drawString("TOPZERA", 100, novaAltura - 100);
        //escrever a nova imagem em um arquivo
        ImageIO.write(imagemNova, "png", new File("./saida/" + nomeArquivo));
    }
}
