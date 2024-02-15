package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class Funcionarios extends JDialog{
	private JTextField inputNome;
	private JTextField inputLogin;
	private JTextField inputPerfil;
	private JTextField inputEmail;
	private JPasswordField inputSenha;
	public Funcionarios() {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().setLayout(null);
		
		JLabel nomeFunc = new JLabel("Nome:\r\n");
		nomeFunc.setBounds(27, 89, 38, 14);
		getContentPane().add(nomeFunc);
		
		JLabel loginFunc = new JLabel("Login:");
		loginFunc.setBounds(27, 166, 32, 14);
		getContentPane().add(loginFunc);
		
		JLabel senhaFunc = new JLabel("Senha:");
		senhaFunc.setBounds(233, 166, 46, 14);
		getContentPane().add(senhaFunc);
		
		JLabel perfilFunc = new JLabel("Perfil:");
		perfilFunc.setBounds(27, 226, 32, 14);
		getContentPane().add(perfilFunc);
		
		JLabel emailFunc = new JLabel("E-mail:");
		emailFunc.setBounds(228, 226, 38, 14);
		getContentPane().add(emailFunc);
		
		inputNome = new JTextField();
		inputNome.setBounds(60, 86, 375, 20);
		getContentPane().add(inputNome);
		inputNome.setColumns(10);
		
		inputLogin = new JTextField();
		inputLogin.setBounds(58, 163, 160, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);
		
		inputPerfil = new JTextField();
		inputPerfil.setBounds(58, 223, 160, 20);
		getContentPane().add(inputPerfil);
		inputPerfil.setColumns(10);
		
		inputEmail = new JTextField();
		inputEmail.setBounds(263, 223, 175, 20);
		getContentPane().add(inputEmail);
		inputEmail.setColumns(10);
		
		inputSenha = new JPasswordField();
		inputSenha.setBounds(269, 163, 166, 20);
		getContentPane().add(inputSenha);
		
		JLabel imgCreate = new JLabel("\r\n");
		imgCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgCreate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/create.png")));
		imgCreate.setBounds(225, 293, 60, 57);
		getContentPane().add(imgCreate);
		
		JLabel imgUpdate = new JLabel("");
		imgUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgUpdate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/update.png")));
		imgUpdate.setBounds(303, 293, 60, 57);
		getContentPane().add(imgUpdate);
		
		JLabel imgDelete = new JLabel("");
		imgDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgDelete.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/delete.png")));
		imgDelete.setBounds(384, 293, 60, 57);
		getContentPane().add(imgDelete);
		setResizable(false);
		setBounds(new Rectangle(0, 0, 450, 400));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Funcionarios.class.getResource("/img/logo.png")));
		setTitle("Funcionarios");
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
