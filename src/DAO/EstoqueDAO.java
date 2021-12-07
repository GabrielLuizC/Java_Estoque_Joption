package DAO;

import Model.Estoque;
import Connection.ConnectionFactory;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {

    private Connection connection;

    public EstoqueDAO() {
        this.connection = new ConnectionFactory().getConnection();
        verificaBanco();
    }

    public void verificaBanco(){
        String sql = "CREATE TABLE IF NOT EXISTS insumos (" +
                "idInsumo INT PRIMARY KEY AUTO_INCREMENT," +
                "nomeInsumo VARCHAR(255) UNIQUE NOT NULL," +
                "qntInsumo INT NOT NULL," +
                "precoInsumo DOUBLE," +
                "dataValidade VARCHAR(255)" +
                ");";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void cadastrarEstoque(Estoque estoque) throws ParseException{
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/estoque", "root", "");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO insumos (nomeInsumo, qntInsumo, precoInsumo, dataValidade) VALUES(?, ?, ?, ?)");

            preparedStatement.setString(1, estoque.getNomeInsumo());
            preparedStatement.setInt(2, estoque.getQntInsumo());
            preparedStatement.setDouble(3, estoque.getPrecoInsumo());
            preparedStatement.setString(4, estoque.getData());

            int rowsAffected = preparedStatement.executeUpdate(); //Atualizar/Inserir linhas no banco de dados

            if (rowsAffected > 0) javax.swing.JOptionPane.showMessageDialog( null, "ITEM CADASTRADO COM SUCESSO!!");
            else javax.swing.JOptionPane.showMessageDialog( null, "ERRO AO CADASTRAR ITEM!!");

        }catch (SQLException e){
            e.printStackTrace();
        }
        return;
    }

    public List<Estoque> listarBanco(){
        List<Estoque> estoqueList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from insumos");

            while (resultSet.next()){
                Estoque estoque = new Estoque();
                estoque.setIdInsumo(resultSet.getInt("idInsumo"));
                estoque.setNomeInsumo(resultSet.getString("nomeInsumo"));
                estoque.setQntInsumo(resultSet.getInt("qntInsumo"));
                estoque.setPrecoInsumo(resultSet.getDouble("precoInsumo"));
                estoque.setRecebeData(resultSet.getString("dataValidade"));
                estoqueList.add(estoque);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estoqueList;
    }

    public void editarEstoque(Estoque estoque) throws ParseException{
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/estoque", "root", "");

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE insumos SET nomeInsumo = ?, qntInsumo = ?, precoInsumo = ?, dataValidade = ? WHERE idInsumo = ?");
            preparedStatement.setString(1, estoque.getNomeInsumo());
            preparedStatement.setInt(2, estoque.getQntInsumo());
            preparedStatement.setDouble(3, estoque.getPrecoInsumo());
            preparedStatement.setString(4, estoque.getData());
            preparedStatement.setInt(5, estoque.getIdInsumo());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void editarNome(Estoque estoque){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/estoque", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE insumos SET nomeInsumo = ? WHERE idInsumo = ?");
            preparedStatement.setString(1, estoque.getNomeInsumo());
            preparedStatement.setInt(2, estoque.getIdInsumo());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void editarQuantidade(Estoque estoque){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/estoque", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE insumos SET qntInsumo = ? WHERE idInsumo = ?");
            preparedStatement.setInt(1, estoque.getQntInsumo());
            preparedStatement.setInt(2, estoque.getIdInsumo());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void editarPreco(Estoque estoque){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/estoque", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE insumos SET precoInsumo = ? WHERE idInsumo = ?");
            preparedStatement.setDouble(1, estoque.getPrecoInsumo());
            preparedStatement.setInt(2, estoque.getIdInsumo());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void editarValidade(Estoque estoque) throws ParseException{
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/estoque", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE insumos SET dataValidade = ? WHERE idInsumo = ?");
            preparedStatement.setString(1, estoque.getData());
            preparedStatement.setInt(2, estoque.getIdInsumo());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void excluirEstoque(Estoque estoque){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/estoque", "root", "");

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM insumos WHERE idInsumo = ?");
            preparedStatement.setInt(1, estoque.getIdInsumo());

            int rowsAffected = preparedStatement.executeUpdate(); //Atualizar/Inserir linhas no banco de dados

            if (rowsAffected > 0) javax.swing.JOptionPane.showMessageDialog( null, "ITEM EXCLUIDO COM SUCESSO!!");
            else javax.swing.JOptionPane.showMessageDialog( null, "ERRO AO EXCLUIDO ITEM!!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int tamanhoBanco(int contador){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/estoque", "root", "");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from insumos");

            while (resultSet.next()){
                contador++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contador;
    }
}
