import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FormularioClientes extends JFrame {
    private JTextField txtId, txtNome, txtEmail;
    private JTextArea areaClientes;
    private ClienteDAO clienteDAO = new ClienteDAO();

    public FormularioClientes() {
        setTitle("Cadastro de Clientes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());

        // Formulário
        JPanel painelForm = new JPanel(new GridLayout(4, 2));
        painelForm.add(new JLabel("ID:"));
        txtId = new JTextField();
        painelForm.add(txtId);

        painelForm.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelForm.add(txtNome);

        painelForm.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        painelForm.add(txtEmail);

        add(painelForm, BorderLayout.NORTH);

        // Botões
        JPanel painelBotoes = new JPanel(new GridLayout(1, 4));
        JButton btnSalvar = new JButton("Salvar");
        JButton btnListar = new JButton("Listar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnRemover = new JButton("Remover");

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnListar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnRemover);

        add(painelBotoes, BorderLayout.CENTER);

        // Área de clientes
        areaClientes = new JTextArea();
        areaClientes.setEditable(false);
        add(new JScrollPane(areaClientes), BorderLayout.SOUTH);

        // Ações dos botões
        btnSalvar.addActionListener(e -> salvarCliente());
        btnListar.addActionListener(e -> listarClientes());
        btnAtualizar.addActionListener(e -> atualizarCliente());
        btnRemover.addActionListener(e -> removerCliente());

        setVisible(true);
    }

    private void salvarCliente() {
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();

        if (nome.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        clienteDAO.salvar(new Cliente(nome, email));
        limparCampos();
        listarClientes();
    }

    private void listarClientes() {
        List<Cliente> clientes = clienteDAO.listar();
        StringBuilder sb = new StringBuilder();
        for (Cliente c : clientes) {
            sb.append("ID: ").append(c.getId())
              .append(", Nome: ").append(c.getNome())
              .append(", Email: ").append(c.getEmail())
              .append("\n");
        }
        areaClientes.setText(sb.toString());
    }

    private void atualizarCliente() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String nome = txtNome.getText().trim();
            String email = txtEmail.getText().trim();
            clienteDAO.atualizar(id, nome, email);
            limparCampos();
            listarClientes();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        }
    }

    private void removerCliente() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            clienteDAO.remover(id);
            limparCampos();
            listarClientes();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtEmail.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FormularioClientes::new);
    }
}
