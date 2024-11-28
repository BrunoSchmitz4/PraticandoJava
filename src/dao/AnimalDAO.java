package dao;

//import models.Animal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Animal;
import models.Cachorro;

public class AnimalDAO {

    private final String url = "jdbc:mysql://localhost:3306/petshop";
    private final String user = "root"; // Troque pelo seu usuário
    private final String password = ""; // Troque pela sua senha

    public void inserirAnimal(Animal animal) throws SQLException {
        String sql = "INSERT INTO Animal (nome, especie, idade, raca) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getEspecie());
            stmt.setInt(3, animal.getIdade());
            stmt.setString(4, animal instanceof models.Cachorro ? ((models.Cachorro) animal).getRaca() : null);
            stmt.executeUpdate();
        }
    }

    public List<Animal> listarAnimais() throws SQLException {
        List<Animal> animais = new ArrayList<>();
        String sql = "SELECT * FROM Animal";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Animal animal = new models.Cachorro(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("idade"),
                    rs.getString("raca")
                );
                animais.add(animal);
            }
        }
        return animais;
    }

    public void removerAnimal(int id) throws SQLException {
        String sql = "DELETE FROM Animal WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    public void atualizarAnimal(Animal animal) throws SQLException {
        String sql = "UPDATE Animal SET nome = ?, especie = ?, idade = ?, raca = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getEspecie());
            stmt.setInt(3, animal.getIdade());
            stmt.setString(4, animal instanceof Cachorro ? ((Cachorro) animal).getRaca() : null);
            stmt.setInt(5, animal.getId());

            stmt.executeUpdate();
        }
    }


}
