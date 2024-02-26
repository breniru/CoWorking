package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.DAO;
import net.proteanit.sql.DbUtils;
import java.awt.Color;

public class Salas extends JDialog {
	private JTextField inputLogin;
	private JTextField inputOcup;
	public JButton imgCreate;
	public JButton imgUpdate;
	public JButton imgDelete;
	public JButton btnPesquisar;

	public Salas() {
		setResizable(false);
		setBounds(new Rectangle(0, 0, 520, 400));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Salas.class.getResource("/img/logo.png")));
		setTitle("Salas");
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().setLayout(null);

		JLabel tipoSala = new JLabel("Categoria:\r\n");
		tipoSala.setBounds(10, 41, 60, 14);
		getContentPane().add(tipoSala);

		JLabel numSala = new JLabel("Número:");
		numSala.setBounds(13, 254, 57, 14);
		getContentPane().add(numSala);

		JLabel andarSala = new JLabel("Andar:");
		andarSala.setBounds(241, 200, 40, 14);
		getContentPane().add(andarSala);

		JLabel codSala = new JLabel("Código:");
		codSala.setBounds(13, 200, 46, 14);
		getContentPane().add(codSala);

		JLabel ocupSala = new JLabel("Ocupação Máximo:");
		ocupSala.setBounds(193, 254, 116, 14);
		getContentPane().add(ocupSala);

		inputLogin = new JTextField();
		inputLogin.setBounds(67, 251, 97, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);

		inputOcup = new JTextField();
		inputOcup.setBounds(308, 251, 175, 20);
		getContentPane().add(inputOcup);
		inputOcup.setColumns(10);

		imgCreate = new JButton("");
		imgCreate.setBackground(new Color(240, 240, 240));
		imgCreate.setBorderPainted(false);
		imgCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgCreate.setIcon(new ImageIcon(Salas.class.getResource("/img/create.png")));
		imgCreate.setBounds(228, 300, 60, 57);
		getContentPane().add(imgCreate);

		imgUpdate = new JButton("");
		imgUpdate.setBackground(new Color(240, 240, 240));
		imgUpdate.setBorderPainted(false);
		imgUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgUpdate.setIcon(new ImageIcon(Salas.class.getResource("/img/update.png")));
		imgUpdate.setBounds(308, 300, 60, 57);
		getContentPane().add(imgUpdate);

		imgDelete = new JButton("");
		imgDelete.setBackground(new Color(240, 240, 240));
		imgDelete.setBorderPainted(false);
		imgDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgDelete.setIcon(new ImageIcon(Salas.class.getResource("/img/delete.png")));
		imgDelete.setBounds(384, 300, 60, 57);
		getContentPane().add(imgDelete);

		inputCod = new JComboBox();
		inputCod.setModel(
				new DefaultComboBoxModel(new String[] {"", "REU", "CONF", "EVENT", "PRIV"}));
		inputCod.setBounds(60, 197, 141, 22);
		getContentPane().add(inputCod);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 59, 312, 69);
		getContentPane().add(scrollPane);

		tblSalas = new JTable();
		scrollPane.setViewportView(tblSalas);

		btnPesquisar = new JButton("");
		btnPesquisar.setBackground(new Color(240, 240, 240));
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setIcon(new ImageIcon(Salas.class.getResource("/img/search.png")));
		btnPesquisar.setBounds(231, 158, 38, 22);
		getContentPane().add(btnPesquisar);
		
		JLabel IDSala = new JLabel("ID:");
		IDSala.setBounds(22, 161, 18, 14);
		getContentPane().add(IDSala);
		
		inputID = new JTextField();
		inputID.setEnabled(false);
		inputID.setBounds(60, 158, 141, 20);
		getContentPane().add(inputID);
		inputID.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Sala de Reunião", "Sala de Conferência", "Espaço de Eventos", "Escritório Privado"}));
		comboBox.setBounds(70, 37, 242, 22);
		getContentPane().add(comboBox);
		
		inputAndar = new JComboBox();
		inputAndar.setModel(new DefaultComboBoxModel(new String[] {"", "Subsolo", "Térrio", "1° andar", "2° andar", "3° andar"}));
		inputAndar.setBounds(289, 196, 165, 22);
		getContentPane().add(inputAndar);

		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//buscarFuncionario();
			}

		});

		tblSalas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//setarCaixasTexto();
			}
		});

		

		imgCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//adicionarFuncionario();
			}
			
			

		});
		
		imgUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//atualizarFuncionario();
			}
		});
		
		imgDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//deletarFuncionario();
			}
		});
		
	}

	DAO dao = new DAO();
	private JComboBox inputCod;
	private JTable tblSalas;
	private JTextField inputID;
	private JComboBox comboBox;
	private JComboBox inputAndar;

	/*private void adicionarFuncionario() {
		String create = "insert into funcionario (nomeFunc, login, senha, perfil, email) values (?, ?, md5(?), ?, ?);";

		if(inputNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nome do usuário obrigátorio!");
			inputLogin.requestFocus();
		
		}

		else if (inputLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Login do usuário obrigátorio!");
			inputLogin.requestFocus();
		}
		
		else if (inputSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Senha do usuário obrigátorio!");
			inputLogin.requestFocus();
		}
		else if(inputOcup.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Email do usuário obrigátorio!");
			inputLogin.requestFocus();
		}
		else {
		
		try {
			// Estabelecer a conexão
			Connection conexaoBanco = dao.conectar();

			// Preparar a execusão do script SQL
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(create);

			// Substituir os pontos de interrogação pelo conteúdo das caixas de texto
			// (inputs)
			executarSQL.setString(1, inputNome.getText());
			executarSQL.setString(2, inputLogin.getText());
			executarSQL.setString(3, inputSenha.getPassword().toString());

			executarSQL.setString(4, inputCod.getSelectedItem().toString());

			executarSQL.setString(5, inputOcup.getText());

			executarSQL.executeUpdate();

			JOptionPane.showMessageDialog(null, "Usuário Cadastrado!");

			limparCampos();

			conexaoBanco.close();
			
		} catch (SQLIntegrityConstraintViolationException error) {
			JOptionPane.showMessageDialog(null, "Login em uso. \nEscolha outro nome de usuário.");
		}

		catch (Exception e) {
			System.out.println(e);
		}
		
	}

	}
	
	private void atualizarFuncionario() {
		String update = "update funcionario set nomeFunc = ?, login = ?, senha = md5(?), perfil = ?, email = ? where idFuncionario = ?";
		
		if(inputNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nome do usuário obrigátorio!");
			inputLogin.requestFocus();
		
		}

		else if (inputLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Login do usuário obrigátorio!");
			inputLogin.requestFocus();
		}
		
		else if (inputSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Senha do usuário obrigátorio!");
			inputLogin.requestFocus();
		}
		else if(inputOcup.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Email do usuário obrigátorio!");
			inputLogin.requestFocus();
		}
		else {
		
		try {
			Connection conexaoBanco = dao.conectar();
			
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(update);
			
			executarSQL.setString(1, inputNome.getText());
			executarSQL.setString(2, inputLogin.getText());
			executarSQL.setString(3, inputSenha.getText()); 
			executarSQL.setString(4, inputCod.getSelectedItem().toString());
			executarSQL.setString(5, inputOcup.getText());
			executarSQL.setString(6, inputID.getText());
			
			executarSQL.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso.");
			
			limparCampos();
			
			conexaoBanco.close();
		}
		
		catch (Exception e) {
			System.out.print(e);
	
		}
		}
	}
	

	private void deletarFuncionario() {
		String delete = "delete from funcionario where idFuncionario = ?;";
		
		try {
			Connection conexaoBanco = dao.conectar();
			
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(delete);
			
			executarSQL.setString(1, inputID.getText());
			
			executarSQL.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso.");
			
			limparCampos();
			
			
			conexaoBanco.close();
		}
		
		catch (Exception e) {
			System.out.print(e);
	
		}
	}

	private void buscarFuncionario() {

		String readBtn = "select * from funcionario where nomeFunc = ?;";

		try {
			// Estabelecer a conexão
			Connection conexaoBanco = dao.conectar();

			// Preparar a execução dos comandos SQL
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(readBtn);

			// Substituir o ? pelo conteúdo da caixa de texto
			executarSQL.setString(1, inputNome.getText());

			// Executar o comando SQL e exibir o resultado no formulário funcionário (todos
			// os seus dados)
			ResultSet resultadoExecucao = executarSQL.executeQuery();

			if (resultadoExecucao.next()) {
				// Preencher os campos do formulário
				inputLogin.setText(resultadoExecucao.getString(3));
				inputSenha.setText(resultadoExecucao.getString(4));
				inputCod.setSelectedItem(resultadoExecucao.getString(5));
				inputOcup.setText(resultadoExecucao.getString(6));
			}
		}

		catch (Exception e) {
			System.out.println(e);
		}

	}

	private void setarCaixasTexto() {

		int setarLinha = tblSalas.getSelectedRow();

		inputNome.setText(tblSalas.getModel().getValueAt(setarLinha, 1).toString());
		
		inputID.setText(tblSalas.getModel().getValueAt(setarLinha, 0).toString());

	}

	private void limparCampos() {
		inputNome.setText(null);
		inputSenha.setText(null);
		inputLogin.setText(null);
		inputOcup.setText(null);
		inputCod.setSelectedItem("");
		inputNome.requestFocus();
	}
*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Salas dialog = new Salas();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
