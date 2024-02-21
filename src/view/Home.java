package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.EmptyBorder;

public class Home extends JDialog {
	
	public JLabel txtUsuarioLogado;
	public JPanel panelUsuario;
	public JLabel txtData;
    public JLabel txtPerfilLogado;
	
	public Home() {
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				Date dataSistema = new Date();
				DateFormat formatadorData = DateFormat.getDateInstance(DateFormat.FULL);
				txtData.setText(formatadorData.format(dataSistema));
			}
		}); 
		
		
		setTitle("Home");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/img/logo.png")));
		setResizable(false);
		setBounds(new Rectangle(0, 0, 450, 400));
		getContentPane().setLayout(null);
		
		JButton btnUser = new JButton("");
		btnUser.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnUser.setBorderPainted(false);
		btnUser.setBackground(new Color(240, 240, 240));
		btnUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUser.setIcon(new ImageIcon(Home.class.getResource("/img/user.png")));
		btnUser.setBounds(22, 21, 107, 105);
		getContentPane().add(btnUser);
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionarios funcionarios = new Funcionarios();
				funcionarios.setVisible(true);
				dispose();
			}
		});
		
		JButton btnRoom = new JButton("");
		btnRoom.setBackground(new Color(240, 240, 240));
		btnRoom.setBorderPainted(false);
		btnRoom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRoom.setIcon(new ImageIcon(Home.class.getResource("/img/room.png")));
		btnRoom.setBounds(168, 21, 107, 105);
		getContentPane().add(btnRoom);
		btnRoom.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Salas salas = new Salas();
			salas.setVisible(true);
			dispose();
		}
	});
		
		JButton btnReserve = new JButton("");
		btnReserve.setBackground(new Color(240, 240, 240));
		btnReserve.setBorderPainted(false);
		btnReserve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReserve.setIcon(new ImageIcon(Home.class.getResource("/img/reserve.png")));
		btnReserve.setBounds(301, 21, 104, 105);
		getContentPane().add(btnReserve);
		
		panelUsuario = new JPanel();
		panelUsuario.setBounds(0, 308, 434, 53);
		getContentPane().add(panelUsuario);
		panelUsuario.setLayout(null);
		
		txtUsuarioLogado = new JLabel("");
		txtUsuarioLogado.setBounds(10, 7, 157, 14);
		panelUsuario.add(txtUsuarioLogado);
		
		txtData = new JLabel("");
		txtData.setBounds(205, 28, 224, 14);
		panelUsuario.add(txtData);
		
		txtPerfilLogado = new JLabel("");
		txtPerfilLogado.setBounds(10, 28, 157, 14);
		panelUsuario.add(txtPerfilLogado);
		
		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reservas reservas = new Reservas();
				reservas.setVisible(true);
				dispose();
			}
		});
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home dialog = new Home();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
