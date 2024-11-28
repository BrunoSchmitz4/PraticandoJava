package models;

public class Cachorro extends Animal {

    private String raca;

    public Cachorro(int id, String nome, int idade, String raca) {
        super(id, nome, "Cachorro", idade);
        this.raca = raca;
    }

    @Override
    public String emitirSom() {
        return "Au Au!";
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }
}
