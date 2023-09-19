/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexao;

import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class Conexao {
    
    //Definição do driver MySql para acesso aos dados
    final private String driver = "com.mysql.jdbc.Driver";
    
    //Acesso ao bd clientes no servidor
    final private String url = "jdbc:mysql://localhost/clientes";
    
    //Usuario dp MySql - usbwebserver
    final private String usuario = "root";
    
    //Senha do MySql - usbwebserver
    final private String senha = "";
    
    //variavel que armazenará a conexão aberta
    private Connection conexao;
    
    //Variavel para execução dos comandos SQL dentro do ambiente Java
    public Statement statement;
    
    //Variavel que armazenará o resultado da execução de um comando SQL
    public ResultSet resultset;
    
    public boolean conecta(){
        boolean result = true;
        try{
            Class.forName(driver);
            
            conexao = DriverManager.getConnection(url, usuario, senha);
            JOptionPane.showMessageDialog(null, "Conexão estabelecida","Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
            }catch (ClassNotFoundException Driver){
                JOptionPane.showMessageDialog(null, "Driver não localizado" + Driver, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                result = false;
           }catch (SQLException Fonte){
               JOptionPane.showMessageDialog(null, "Fonte de dados não localizada" + Fonte, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
               result = false;
           }
        return result;
        
    }
    public void desconecta(){
        try {
            conexao.close();
            JOptionPane.showMessageDialog(null, "Conexão com o banco fechada", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException fecha){
            
        }
    }
    public void executaSQL (String sql){
        try{
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(sql);
        }catch (SQLException excecao){
            JOptionPane.showMessageDialog(null, "Erro no comando SQL! \n  Erro: " + excecao, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
