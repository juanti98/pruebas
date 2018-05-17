import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class tablas extends JFrame {
	private String login = "ProyectoIntegrador";
	private String pwd = "contraseña";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private Connection conexion;

	private DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] { "NOMBRE", "PASS", "ROL", "NUMERO CONVENIO", "NOMBRE EMPRESA" });
	private JPanel contentPane;
	private JTable table;

	public void Modelo() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection(url, login, pwd);
			if (conexion != null) {
				System.out.println(" - Conexión con ORACLE establecida -");

			}
		} catch (Exception e) {
			System.out.println(" – Error de Conexión con ORACLE -");
			e.printStackTrace();
		}
	}

	public void insercion() {
		try {
			PreparedStatement pstmt = conexion.prepareStatement("select NOMBRE, PASS, ROL, NUM_CONV, NOM_EMPR from ProyectoIntegrador.usuario, ProyectoIntegrador.empresa WHERE NOMBRE = 'Adrian'");
			ResultSet rset = pstmt.executeQuery();
			while (rset.next()) {
				model.addRow(new Object[] { rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5) });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public tablas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 546, 368);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(model);
		scrollPane.setViewportView(table);
	}

}
