package Utils;

import java.net.URL;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class Util {

    public static Image getIcone() {
        URL caminhoImagem = Util.class.getResource("/img/logo_mini.png");

        ImageIcon icon = new ImageIcon(caminhoImagem);

        return icon.getImage();
    }

    public static Date converterStringToDate(String texto) {
        //Contruo o formato que quero transformar em texto
        SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
        //creio minha variável data que será o retorno do método
        Date data = null;
        try {
            //temta converter a String em Date baseado no formato
            //construido anteriormente
            data = formato.parse(texto);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao converter a data");
        }
        return data;
    }

    public static String calcularHash(String senha) {
        String hashSHA1 = "";
        try {
            //crie uma instância do MessageDigest
            //com o algoritimo SHA1
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");

            //atualize o digest com o bytes do texto
            sha1.update(senha.getBytes());

            //calcule o hash SHA1
            byte[] digest = sha1.digest();

            //converta o hash de bytes para uma representação hexadecimal
            for (byte b : digest) {
                hashSHA1 = hashSHA1 + String.format("%02x", b);
            }

        } catch (NoSuchAlgorithmException e) {
            System.err.println("Algoritmo SHA1 não encontrado");
        }

        return hashSHA1;
    }

    public static String converterDateToString(Date data) {

        SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");

        String texto = "";

        try {
            //Ira formatar a data para o formato dd/mm/yyyy
            texto = formato.format(data);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao formatar a data");
        }
        return texto;
    }

}
