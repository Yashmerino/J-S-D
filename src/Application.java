import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Application {

	private JFrame frame;
	private JTextField FieldURLSchema;
	private JTextField FieldOutputFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton BrowseSchemaButton = new JButton("Browse");
		BrowseSchemaButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		BrowseSchemaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BrowseSchemaButton.setBounds(457, 147, 143, 29);
		frame.getContentPane().add(BrowseSchemaButton);
		
		FieldURLSchema = new JTextField();
		FieldURLSchema.setBounds(27, 147, 420, 29);
		frame.getContentPane().add(FieldURLSchema);
		FieldURLSchema.setColumns(10);
		
		JButton BrowseOutputButton = new JButton("Browse");
		BrowseOutputButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		BrowseOutputButton.setBounds(457, 225, 143, 29);
		frame.getContentPane().add(BrowseOutputButton);
		
		FieldOutputFile = new JTextField();
		FieldOutputFile.setColumns(10);
		FieldOutputFile.setBounds(27, 225, 420, 29);
		frame.getContentPane().add(FieldOutputFile);
		
		JLabel lblNewLabel = new JLabel("JSON Schema URL");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(27, 112, 134, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblOutputFile = new JLabel("Output File");
		lblOutputFile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOutputFile.setBounds(27, 196, 134, 29);
		frame.getContentPane().add(lblOutputFile);
		
		JLabel lblNewLabel_1 = new JLabel("Generate JSON Schema Documentation");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(150, 10, 328, 36);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
