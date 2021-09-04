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
import javax.swing.JCheckBox;

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
		BrowseSchemaButton.setBounds(457, 140, 143, 29);
		frame.getContentPane().add(BrowseSchemaButton);
		
		FieldURLSchema = new JTextField();
		FieldURLSchema.setBounds(27, 140, 420, 29);
		frame.getContentPane().add(FieldURLSchema);
		FieldURLSchema.setColumns(10);
		
		JButton BrowseOutputButton = new JButton("Browse");
		BrowseOutputButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		BrowseOutputButton.setBounds(457, 218, 143, 29);
		frame.getContentPane().add(BrowseOutputButton);
		
		FieldOutputFile = new JTextField();
		FieldOutputFile.setColumns(10);
		FieldOutputFile.setBounds(27, 218, 420, 29);
		frame.getContentPane().add(FieldOutputFile);
		
		JLabel lblNewLabel = new JLabel("JSON Schema URL");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(27, 111, 134, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblOutputFile = new JLabel("Output File");
		lblOutputFile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOutputFile.setBounds(27, 189, 134, 29);
		frame.getContentPane().add(lblOutputFile);
		
		JLabel lblNewLabel_1 = new JLabel("Generate JSON Schema Documentation");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(150, 22, 322, 36);
		frame.getContentPane().add(lblNewLabel_1);
		
		JCheckBox RequiredCheckBox = new JCheckBox("Only required content");
		RequiredCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		RequiredCheckBox.setBounds(27, 275, 181, 21);
		frame.getContentPane().add(RequiredCheckBox);
		
		JCheckBox includeCheckBox = new JCheckBox("Include examples");
		includeCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		includeCheckBox.setBounds(27, 298, 181, 21);
		frame.getContentPane().add(includeCheckBox);
		
		JButton GenerateButton = new JButton("Generate");
		GenerateButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GenerateButton.setBounds(150, 378, 143, 29);
		frame.getContentPane().add(GenerateButton);
		
		JButton CancelButton = new JButton("Cancel");
		CancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		CancelButton.setBounds(329, 378, 143, 29);
		frame.getContentPane().add(CancelButton);
	}
}
