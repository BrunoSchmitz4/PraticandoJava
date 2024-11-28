/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author bruno
 */
// Animal.java
public abstract class Animal {
    private int id;
    private String nome;
    private String especie;
    private int idade;

    public Animal(int id, String nome, String especie, int idade) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.idade = idade;
    }

    // Métodos abstratos para serem implementados pelas subclasses
    public abstract String emitirSom();

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
