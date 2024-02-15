
package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;

public class Login extends JDialog {
	private JTextField inputLogin;
	private JPasswordField inputSenha;
	private JLabel imgDatabase;

	public Login() {
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				statusConexaoBanco();
			}
		});

		setResizable(false);
		getContentPane().setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		setTitle("Login");
		setBounds(new Rectangle(0, 0, 400, 360));
		getContentPane().setBackground(new Color(206, 206, 206));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);

		JLabel txtLogin = new JLabel("Login:");
		txtLogin.setFont(new Font("Sitka Small", Font.PLAIN, 12));
		txtLogin.setBounds(78, 138, 46, 14);
		getContentPane().add(txtLogin);

		JLabel txtSenha = new JLabel("Senha:");
		txtSenha.setFont(new Font("Sitka Small", Font.PLAIN, 12));
		txtSenha.setBounds(78, 178, 46, 14);
		getContentPane().add(txtSenha);

		inputLogin = new JTextField();
		inputLogin.setBounds(121, 133, 176, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);

		inputSenha = new JPasswordField();
		inputSenha.setBounds(121, 173, 176, 20);
		getContentPane().add(inputSenha);

		JLabel tituloLogin = new JLabel("Acessar Conta");
		tituloLogin.setFont(new Font("Sitka Small", Font.BOLD, 14));
		tituloLogin.setHorizontalAlignment(SwingConstants.CENTER);
		tituloLogin.setBounds(0, 76, 384, 20);
		getContentPane().add(tituloLogin);

		JButton btnLogin = new JButton("Entrar");
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.setBounds(159, 249, 80, 20);
		getContentPane().add(btnLogin);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});

		imgDatabase = new JLabel("");
		imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOff.png")));
		imgDatabase.setBounds(10, 252, 53, 58);
		getContentPane().add(imgDatabase);
		
		//Acessar o botão "Entrar" com a tecla "Enter"
		getRootPane().setDefaultButton(btnLogin);
		
		//Validação dos campos utilizando a biblioteca Atxy2k
		
		//Validação do campo inputLogin
		RestrictedTextField validarLogin = new RestrictedTextField(inputLogin, "abcdefghijklmnopqrstuvwxyz0123456789_-.");
			
		//Determinar o uso de alguns caracteres especiais(_ - .) e alfanúmericos
		validarLogin.setOnlyCustomCharacters(true);
		
		//Limitar a somente 20 caracteres no campo login
		validarLogin.setLimit(20);

		//Validação do campo inputSenha
		RestrictedTextField validarSenha = new RestrictedTextField(inputSenha);
		
		//Limitar a somente 15 caracteres no campo senha
		validarSenha.setLimit(15);
		
		
		
	}

	DAO dao = new DAO();

	private void statusConexaoBanco() {
		try {
			Connection conexaoBanco = dao.conectar();

			if (conexaoBanco == null) {
				// Escolher a imagem para quando não há conexão
				imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOff.png")));
			} else {
				imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOn.png")));
			}
			conexaoBanco.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void logar() {
		String read = "select * from funcionario where login=? and senha=md5(?)";

		// Validação do login do usuário
		if (inputLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Login do usuário obrigátorio!");
			inputLogin.requestFocus();
		}

		else if (inputSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Senha do usuário obrigátorio!");
			inputLogin.requestFocus();
		}

		else {

			try {
				// Estabelecer a conexão
				Connection conexaoBanco = dao.conectar();

				// Preparar a execusão do script SQL
				PreparedStatement executarSQL = conexaoBanco.prepareStatement(read);

				// Atribuir valores de login e senha
				// Substituir as interrogações ?? pelo conteúdo da caixa de texto (input)
				executarSQL.setString(1, inputLogin.getText());
				executarSQL.setString(2, inputSenha.getText());

				// Executar os comandos SQL e de acordo com o resultado liberar os recursos na
				// tela
				ResultSet resultadoExecucao = executarSQL.executeQuery();

				// Validação do funcionário (autenticação)
				// resultadoExecucao.next() significa que o login e a senha existem, ou seja,
				// correspondem

				if (resultadoExecucao.next()) {
					System.out.println("lalalalalala");
					Home home = new Home();
					home.setVisible(true);
					
					home.txtUsuarioLogado.setText("Usuário: " + resultadoExecucao.getString(2));
					
					dispose();

				} else {
					// Criar um alerta (pop-pop) que informe ao usuário que login e/ou senha estão
					// inválidos
					JOptionPane.showMessageDialog(null, "Login e/ou senha inválido(s)!");
					inputLogin.setText(null);
					inputSenha.setText(null);
					inputLogin.requestFocus();
					
				}

				conexaoBanco.close();
			}

			catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login dialog = new Login();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
