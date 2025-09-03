package Utils;

import java.net.URL;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Util {

    public static Image getIcone() {
        URL caminhoImagem = Util.class.getResource("/img/logo_mini.png");

        ImageIcon icon = new ImageIcon(caminhoImagem);

        return icon.getImage();
    }

}
