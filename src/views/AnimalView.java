package views;

import controllers.AnimalController;
import models.Cachorro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AnimalView extends JFrame {

    private final AnimalController animalController;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nomeField, idadeField, racaField;
    private JButton addButton, editButton;
    private int selectedAnimalId = -1;

    public AnimalView() {
        this.animalController = new AnimalController();
        initUI();
        carregarTabela();
    }

    private void initUI() {
        setTitle("Gerenciamento de Animais");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Formulário para adicionar/editar animal
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Adicionar/Editar Animal"));

        nomeField = new JTextField();
        idadeField = new JTextField();
        racaField = new JTextField();

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Idade:"));
        formPanel.add(idadeField);
        formPanel.add(new JLabel("Raça:"));
        formPanel.add(racaField);

        addButton = new JButton("Adicionar");
        addButton.addActionListener(e -> adicionarAnimal());

        editButton = new JButton("Editar");
        editButton.setEnabled(false); // Só será ativado quando um animal for selecionado
        editButton.addActionListener(e -> editarAnimal());

        formPanel.add(addButton);
        formPanel.add(editButton);

        mainPanel.add(formPanel, BorderLayout.NORTH);

        // Tabela para listar animais
        tableModel = new DefaultTableModel(new String[]{"ID", "Nome", "Espécie", "Idade", "Raça"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        table.getSelectionModel().addListSelectionListener(e -> selecionarAnimal());

        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Botão para remover animal
        JButton removeButton = new JButton("Remover Selecionado");
        removeButton.addActionListener(e -> removerAnimal());

        mainPanel.add(removeButton, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void adicionarAnimal() {
            try {
                String nome = nomeField.getText();
                int idade = Integer.parseInt(idadeField.getText());
                String raca = racaField.getText();

                Cachorro cachorro = new Cachorro(0, nome, idade, raca);
                animalController.adicionarAnimal(cachorro);

                carregarTabela();
                limparFormulario();
                JOptionPane.showMessageDialog(this, "Animal adicionado com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Idade deve ser um número!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void editarAnimal() {
        if (selectedAnimalId == -1) {
            JOptionPane.showMessageDialog(this, "Nenhum animal selecionado para edição!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String nome = nomeField.getText();
            int idade = Integer.parseInt(idadeField.getText());
            String raca = racaField.getText();

            // Cria um objeto cachorro com o ID do animal selecionado
            Cachorro cachorro = new Cachorro(selectedAnimalId, nome, idade, raca);

            // Chama o método correto no controlador
            animalController.editarAnimal(cachorro); 

            carregarTabela();
            limparFormulario();
            JOptionPane.showMessageDialog(this, "Animal editado com sucesso!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Idade deve ser um número!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void selecionarAnimal() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            selectedAnimalId = (int) tableModel.getValueAt(selectedRow, 0);
            nomeField.setText((String) tableModel.getValueAt(selectedRow, 1));
            idadeField.setText(String.valueOf(tableModel.getValueAt(selectedRow, 3)));
            racaField.setText((String) tableModel.getValueAt(selectedRow, 4));

            addButton.setEnabled(false);
            editButton.setEnabled(true);
        }
    }

    private void removerAnimal() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            animalController.removerAnimalPorId(id);
            carregarTabela();
            JOptionPane.showMessageDialog(this, "Animal removido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um animal para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarTabela() {
        tableModel.setRowCount(0); // Limpa a tabela
        List<models.Animal> animais = animalController.listarAnimais();
        for (models.Animal animal : animais) {
            tableModel.addRow(new Object[]{
                    animal.getId(),
                    animal.getNome(),
                    animal.getEspecie(),
                    animal.getIdade(),
                    (animal instanceof Cachorro) ? ((Cachorro) animal).getRaca() : "N/A"
            });
        }

        limparFormulario();
    }

    private void limparFormulario() {
        nomeField.setText("");
        idadeField.setText("");
        racaField.setText("");
        selectedAnimalId = -1;
        addButton.setEnabled(true);
        editButton.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AnimalView().setVisible(true));
    }
}
