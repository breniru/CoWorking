package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.UIManager;

import model.DAO;
import javax.swing.DefaultComboBoxModel;

public class Funcionarios extends JDialog{
	private JTextField inputNome;
	private JTextField inputLogin;
	private JTextField inputEmail;
	private JPasswordField inputSenha;
	public JButton imgCreate;
	public JButton imgUpdate;
	public JButton imgDelete;
	
	public Funcionarios() {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		getContentPane().setLayout(null);
		
		JLabel nomeFunc = new JLabel("Nome:\r\n");
		nomeFunc.setBounds(17, 89, 38, 14);
		getContentPane().add(nomeFunc);
		
		JLabel loginFunc = new JLabel("Login:");
		loginFunc.setBounds(14, 166, 38, 14);
		getContentPane().add(loginFunc);
		
		JLabel senhaFunc = new JLabel("Senha:");
		senhaFunc.setBounds(233, 166, 40, 14);
		getContentPane().add(senhaFunc);
		
		JLabel perfilFunc = new JLabel("Perfil:");
		perfilFunc.setBounds(14, 226, 35, 14);
		getContentPane().add(perfilFunc);
		
		JLabel emailFunc = new JLabel("E-mail:");
		emailFunc.setBounds(228, 226, 38, 14);
		getContentPane().add(emailFunc);
		
		inputNome = new JTextField();
		inputNome.setBounds(58, 86, 375, 20);
		getContentPane().add(inputNome);
		inputNome.setColumns(10);
		
		inputLogin = new JTextField();
		inputLogin.setBounds(52, 163, 166, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);
		
		inputEmail = new JTextField();
		inputEmail.setBounds(269, 223, 175, 20);
		getContentPane().add(inputEmail);
		inputEmail.setColumns(10);
		
		inputSenha = new JPasswordField();
		inputSenha.setBounds(274, 163, 166, 20);
		getContentPane().add(inputSenha);
		
		imgCreate = new JButton("");
		imgCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgCreate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/create.png")));
		imgCreate.setBounds(228, 300, 60, 57);
		getContentPane().add(imgCreate);
		
		imgUpdate = new JButton("");
		imgUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgUpdate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/update.png")));
		imgUpdate.setBounds(308, 300, 60, 57);
		getContentPane().add(imgUpdate);
		
		imgDelete = new JButton("");
		imgDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgDelete.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/delete.png")));
		imgDelete.setBounds(384, 300, 60, 57);
		getContentPane().add(imgDelete);
		
		inputPerfil = new JComboBox();
		inputPerfil.setModel(new DefaultComboBoxModel(new String[] {"", "Administrador", "Gerência", "Atendimento", "Suporte"}));
		inputPerfil.setBounds(52, 222, 166, 22);
		getContentPane().add(inputPerfil);
		
		setResizable(false);
		setBounds(new Rectangle(0, 0, 470, 400));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Funcionarios.class.getResource("/img/logo.png")));
		setTitle("Funcionarios");
		
		imgCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarFuncionario();
				
			}
		});
	}
	
	
	DAO dao = new DAO();
	private JComboBox inputPerfil;
	
	private void adicionarFuncionario() {
		String create = "insert into funcionario (nomeFunc, login, senha, perfil, email) values (?, ?, md5(?), ?, ?);";
		
		
		try {
			// Estabelecer a conexão
			Connection conexaoBanco = dao.conectar();
			
			// Preparar a execusão do script SQL
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(create);
			
			//Substituir os pontos de interrogação pelo conteúdo das caixas de texto (inputs)
			executarSQL.setString(1, inputNome.getText());
			executarSQL.setString(2, inputLogin.getText());
			executarSQL.setString(3, inputSenha.getPassword().toString());
			
			executarSQL.setString(4, inputPerfil.getSelectedItem().toString());
			
			executarSQL.setString(5, inputEmail.getText());
			
			executarSQL.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Usuário Cadastrado");

			inputNome.setText(null);
			inputSenha.setText(null);
			inputLogin.setText(null);
			inputEmail.setText(null);
			inputPerfil.setSelectedItem("");
			
			conexaoBanco.close();
		} 
		catch (SQLIntegrityConstraintViolationException error) {
			JOptionPane.showMessageDialog(null, "Login em uso. \nEscolha outro nome de usuário.");
		}
		
		catch (Exception e) {
			System.out.println(e);	
		}
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Funcionarios dialog = new Funcionarios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
