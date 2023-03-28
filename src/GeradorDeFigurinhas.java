import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {
    public void gerarImagem(InputStream inputStream, String nomeArquivo) throws Exception {
        //ler imagem
        //InputStream inputStream = new FileInputStream(new File("./img/filme.jpg"));
        //InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //criar imagem em memória com transparencia e com tamanho novo
        int larguraImagem = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage imagemNova = new BufferedImage(larguraImagem, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar imagem original para o nova imagem
        Graphics2D graphics = (Graphics2D)imagemNova.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //configurar fonte
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setFont(fonte);
        graphics.setColor(Color.YELLOW);

        //escrever uma frase na nova imagem 
        String texto = "TOPZERA";
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D rectangle = fontMetrics.getStringBounds(texto, graphics);
        int larguraTexto = (int) rectangle.getWidth();
        int posicaoXTexto = (larguraImagem - larguraTexto) / 2;
        int posicaoYTexto = novaAltura - 100;
        graphics.drawString(texto, posicaoXTexto, posicaoYTexto);

        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        TextLayout textLayout = new TextLayout(texto, fonte, fontRenderContext);

        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(posicaoXTexto, posicaoYTexto);
        graphics.setTransform(transform);
        BasicStroke basicStroke = new BasicStroke(larguraTexto * 0.004f);
        graphics.setStroke(basicStroke);

        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);
        //escrever a nova imagem em um arquivo
        ImageIO.write(imagemNova, "png", new File(nomeArquivo));
    }
}
