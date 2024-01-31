package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Sobre extends JDialog {
	public Sobre() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/logo.png")));
		getContentPane().setBackground(new Color(192, 192, 192));
		getContentPane().setLayout(null);
		
		JLabel titulo = new JLabel("Sobre o software");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(0, 41, 576, 20);
		titulo.setBackground(new Color(255, 255, 255));
		titulo.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		getContentPane().add(titulo);
		
		JLabel descricao2 = new JLabel(" possibilitar o gerenciamento de reserva de salas em um espaço colaborativo.\r\n");
		descricao2.setHorizontalAlignment(SwingConstants.CENTER);
		descricao2.setFont(new Font("Sitka Small", Font.PLAIN, 12));
		descricao2.setBounds(0, 108, 576, 30);
		getContentPane().add(descricao2);
		
		JLabel descricao1 = new JLabel("O sofware CoWorking trata-se de um protótipo cujo objetivo é ");
		descricao1.setHorizontalAlignment(SwingConstants.CENTER);
		descricao1.setFont(new Font("Sitka Small", Font.PLAIN, 12));
		descricao1.setBounds(0, 79, 576, 30);
		getContentPane().add(descricao1);
		
		JLabel versao = new JLabel("Versão: 1.0.0");
		versao.setFont(new Font("Sitka Small", Font.PLAIN, 12));
		versao.setHorizontalAlignment(SwingConstants.CENTER);
		versao.setBounds(0, 161, 576, 20);
		getContentPane().add(versao);
		
		JLabel atualizacao = new JLabel("Última atualização: 31/01/2024");
		atualizacao.setFont(new Font("Sitka Small", Font.PLAIN, 12));
		atualizacao.setHorizontalAlignment(SwingConstants.CENTER);
		atualizacao.setBounds(0, 192, 576, 14);
		getContentPane().add(atualizacao);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Sobre.class.getResource("/img/mitLicense.png")));
		lblNewLabel.setBounds(505, 315, 50, 61);
		getContentPane().add(lblNewLabel);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
