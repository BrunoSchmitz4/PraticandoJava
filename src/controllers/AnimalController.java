package controllers;

import dao.AnimalDAO;
import models.Animal;

import java.sql.SQLException;
import java.util.List;

public class AnimalController {

    private final AnimalDAO animalDAO;

    public AnimalController() {
        this.animalDAO = new AnimalDAO();
    }

    // Adiciona um animal ao banco de dados
    public void adicionarAnimal(Animal animal) {
        try {
            animalDAO.inserirAnimal(animal);
            System.out.println("Animal adicionado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar animal: " + e.getMessage());
        }
    }

    // Lista todos os animais do banco de dados
    public List<Animal> listarAnimais() {
        try {
            return animalDAO.listarAnimais();
        } catch (SQLException e) {
            System.err.println("Erro ao listar animais: " + e.getMessage());
            return List.of(); // Retorna uma lista vazia em caso de erro
        }
    }

    // Remove um animal pelo ID no banco de dados
    public boolean removerAnimalPorId(int id) {
        try {
            animalDAO.removerAnimal(id);
            System.out.println("Animal removido com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao remover animal: " + e.getMessage());
            return false;
        }
    }
    
    public void editarAnimal(Animal animal) {
        try {
            animalDAO.atualizarAnimal(animal); // Método do DAO que atualiza o animal no banco
            System.out.println("Animal atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar animal: " + e.getMessage());
        }
    }


}
