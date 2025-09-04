package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.Usuario;

public class UsuarioController {

    public boolean autenticar(String usuario, String senha) {
        //Montar o comando a ser executado
        // os ? são variáveis que são preenchidas mais adiante

        String sql = "SELECT * from TBUSUARIO "
                + " WHERE email = ? and senha = ? "
                + " and ativo = true";

        //Cria uma instância do gerenciador de conexão
        //(Conexao com o banco de dados)
        GerenciadorConexao gerenciador = new GerenciadorConexao();

        //Declara as variáveis como nulas antes do try
        //para poder usar no finally
        PreparedStatement comando = null;
        ResultSet resultado = null;

        try {
            //Prepara o sql, analisando o formato e as variáveis
            comando = gerenciador.prepararComando(sql);

            //Define o valor de cada variável(?) pela posição em que aparece no sql
            comando.setString(1, usuario);
            comando.setString(2, senha);
            //executa o comando e guarda o resultado da consulta
            //o resultado é semelhante a uma grade
            resultado = comando.executeQuery();

            if (resultado.next()) {
                return true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            //Depois de executar o try, dando erro ou não, executa o finally
            gerenciador.fecharConexao(comando, resultado);
        }
        return false;

    }

    public boolean inserir(Usuario usu) {
        //Montar o comando a ser executado
        // os ? são variáveis que são preenchidas mais adiante

        String sql = "INSERT into TBUSUARIO (nome, email, senha, dataNasc, ativo) "
                + " values (?,?,?,?,?)";

        //Cria uma instância do gerenciador de conexão
        //(Conexao com o banco de dados)
        GerenciadorConexao gerenciador = new GerenciadorConexao();

        //Declara as variáveis como nulas antes do try
        //para poder usar no finally
        PreparedStatement comando = null;

        try {
            //Prepara o sql, analisando o formato e as variáveis
            comando = gerenciador.prepararComando(sql);

            //Define o valor de cada variável(?) pela posição em que aparece no sql
            comando.setString(1, usu.getNome());
            comando.setString(2, usu.getEmail());
            comando.setString(3, usu.getSenha());
            comando.setDate(4, new java.sql.Date(usu.getData().getTime()));
            comando.setBoolean(5, usu.isAtivo());

            comando.executeUpdate();

            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            //Depois de executar o try, dando erro ou não, executa o finally
            gerenciador.fecharConexao(comando);
        }
        return false;

    }
}
