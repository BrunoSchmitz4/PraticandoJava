package praticandoparaprova;

import controllers.AnimalController;
import models.Cachorro;

public class Main {
    public static void main(String[] args) {
        AnimalController controller = new AnimalController();

        // Adicionar um cachorro ao banco
        Cachorro dog = new Cachorro(0, "Rex", 5, "Golden Retriever");
        controller.adicionarAnimal(dog);

        // Listar todos os animais cadastrados
        System.out.println("Animais cadastrados:");
        controller.listarAnimais().forEach(animal -> 
            System.out.println(animal.getId() + " - " + animal.getNome() + " - " + animal.getEspecie())
        );

        // Remover o animal com ID 1
        if (controller.removerAnimalPorId(1)) {
            System.out.println("Animal com ID 1 removido.");
        }

        // Listar novamente para verificar a remoção
        System.out.println("Animais restantes:");
        controller.listarAnimais().forEach(animal -> 
            System.out.println(animal.getId() + " - " + animal.getNome() + " - " + animal.getEspecie())
        );
    }
}
