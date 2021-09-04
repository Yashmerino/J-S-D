import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

public class Application {

	// Components
	private JFrame frame;
	private JTextField FieldOpenFile;
	private JTextField FieldSaveFile;
	
	FileManager fileManager = new FileManager();
	
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
		
		FieldOpenFile = new JTextField();
		FieldOpenFile.setBounds(27, 140, 420, 29);
		frame.getContentPane().add(FieldOpenFile);
		FieldOpenFile.setColumns(10);
		
		FieldSaveFile = new JTextField();
		FieldSaveFile.setColumns(10);
		FieldSaveFile.setBounds(27, 218, 420, 29);
		frame.getContentPane().add(FieldSaveFile);
		
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
		
		// When cancel button clicked
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Close the application
				System.exit(1);
			}
		});
		CancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		CancelButton.setBounds(329, 378, 143, 29);
		frame.getContentPane().add(CancelButton);
		
		JButton BrowseOpenButton = new JButton("Browse");
		BrowseOpenButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		// When browse file to open clicked
		BrowseOpenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// To select files
				JFileChooser fileChooser = new JFileChooser();
				// Filter for files
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files", "json");
				// Set the filter
				fileChooser.setFileFilter(filter);
				// Disable "all files" section in dialog box
				fileChooser.setAcceptAllFileFilterUsed(false);
				
				// Open dialog box to select file
				int response = fileChooser.showOpenDialog(null);
				
				// If selected file is good
				if(response == JFileChooser.APPROVE_OPTION) {
					// Save the file
					fileManager.setOpenFile(new File(fileChooser.getSelectedFile().getAbsolutePath()));
					// Set the text field to the open file
					FieldOpenFile.setText(fileManager.getOpenFile().toString());
					// Message Dialog that everything went good
					JOptionPane.showMessageDialog(null, fileManager.getOpenFile().getName() + " has been opened successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		BrowseOpenButton.setBounds(457, 140, 143, 29);
		frame.getContentPane().add(BrowseOpenButton);
		
		
		JButton BrowseSaveButton = new JButton("Browse");
		BrowseSaveButton.setBounds(457, 218, 143, 29);
		
		// When browse file to save clicked
		BrowseSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// To select files
				JFileChooser fileChooser = new JFileChooser();
				// Filter for files
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT Files", "txt");
				// Set the filter
				fileChooser.setFileFilter(filter);
				// Disable "all files" section in dialog box
				fileChooser.setAcceptAllFileFilterUsed(false);
				
				// Open dialog box to select file
				int response = fileChooser.showSaveDialog(null);
				
				
				// If selected file is good
				if(response == JFileChooser.APPROVE_OPTION) {
					// Save the file
					fileManager.setSaveFile(new File(fileChooser.getSelectedFile().getAbsolutePath()));
					// Set the text field to the save file
					FieldSaveFile.setText(fileManager.getSaveFile().toString());
					// Message Dialog that everything went good
					JOptionPane.showMessageDialog(null, fileManager.getSaveFile().getName() + " has been selected successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		BrowseSaveButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(BrowseSaveButton);
	}
}
