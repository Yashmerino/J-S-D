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

	/**
	 * Creates new frame(content within window)
	 */
	private JFrame frame;
	
	/**
	 * Creates a File Manager that manages with selected files
	 */
	FileManager fileManager = new FileManager();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Initialize window
					Application window = new Application();
					// Set the frame visible
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
		//------------Widgets------------
		
		// Initialize the frame and its components
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Declare and initialize text field for input file
		JTextField fieldInputFile;
		fieldInputFile = new JTextField();
		fieldInputFile.setBounds(30, 140, 420, 30);
		frame.getContentPane().add(fieldInputFile);
		fieldInputFile.setColumns(10);
		
		// Declare and initialize text field for output file
		JTextField fieldOutputFile;
		fieldOutputFile = new JTextField();
		fieldOutputFile.setColumns(10);
		fieldOutputFile.setBounds(30, 220, 420, 30);
		frame.getContentPane().add(fieldOutputFile);
		
		// Declare and initialize label for input text field
		JLabel InputFileLabel = new JLabel("JSON Schema URL");
		InputFileLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		InputFileLabel.setBounds(30, 110, 135, 30);
		frame.getContentPane().add(InputFileLabel);
		
		// Declare and initialize label for output text field
		JLabel OutputFileLabel = new JLabel("Output File");
		OutputFileLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		OutputFileLabel.setBounds(30, 190, 135, 30);
		frame.getContentPane().add(OutputFileLabel);
		
		// Declare and initialize label for title
		JLabel titleLabel = new JLabel("Generate JSON Schema Documentation");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		titleLabel.setBounds(150, 25, 330, 40);
		frame.getContentPane().add(titleLabel);
		
		// Declare and initialize check box for "only required content"
		JCheckBox requiredCheckBox = new JCheckBox("Only required content");
		requiredCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		requiredCheckBox.setBounds(30, 275, 180, 20);
		frame.getContentPane().add(requiredCheckBox);
		
		// Declare and initialize check box for "include examples"
		JCheckBox includeCheckBox = new JCheckBox("Include examples");
		includeCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		includeCheckBox.setBounds(30, 300, 180, 20);
		frame.getContentPane().add(includeCheckBox);
		
		// Declare and initialize generate button
		JButton generateButton = new JButton("Generate");
		generateButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		generateButton.setBounds(150, 380, 140, 30);
		frame.getContentPane().add(generateButton);
		
		// Declare and initialize cancel button
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cancelButton.setBounds(330, 380, 140, 30);
		frame.getContentPane().add(cancelButton);
		
		// Declare and initialize browse input file button
		JButton browseInputButton = new JButton("Browse");
		browseInputButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		browseInputButton.setBounds(460, 140, 140, 30);
		frame.getContentPane().add(browseInputButton);
		
		// Declare and initialize browse output file button
		JButton browseOutputButton = new JButton("Browse");
		browseOutputButton.setBounds(460, 220, 140, 30);
		browseOutputButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(browseOutputButton);
		
		//----------------------------------------
		
		// When cancel button clicked
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Close the application
				System.exit(1);
			}
		});
		
		// When browse input file clicked
		browseInputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Declare and initialize a JFileChooser to select a file
				JFileChooser fileChooser = new JFileChooser();
				// Filter for files to show only JSON files
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files", "json");
				// Set the filter for JFileChooser
				fileChooser.setFileFilter(filter);
				// Disable "all files" section in dialog box when selecting a file
				fileChooser.setAcceptAllFileFilterUsed(false);
				
				// Open dialog box to select a file and store the return value in "response"
				int response = fileChooser.showOpenDialog(null);
				
				// If file has been chosen
				if(response == JFileChooser.APPROVE_OPTION) {
					// Save the input file via File Manager
					fileManager.setInputFile(new File(fileChooser.getSelectedFile().getAbsolutePath()));
					// Set the text field content to the path of the input file
					fieldInputFile.setText(fileManager.getInputFile().toString());
					// Message Dialog to notify the user that everything went good
					JOptionPane.showMessageDialog(null, fileManager.getInputFile().getName() + " has been opened successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		// When browse output file clicked
		browseOutputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Declare and initialize a JFileChooser to select a file
				JFileChooser fileChooser = new JFileChooser();
				// Filter for files to show only JSON files
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT Files", "txt");
				// Set the filter for JFileChooser
				fileChooser.setFileFilter(filter);
				// Disable "all files" section in dialog box when selecting a file
				fileChooser.setAcceptAllFileFilterUsed(false);
				
				// Open dialog box to select a file and store the return value in "response"
				int response = fileChooser.showSaveDialog(null);
				
				
				// If file has been chosen
				if(response == JFileChooser.APPROVE_OPTION) {
					// Save the output file via File Manager
					fileManager.setOutputFile(new File(fileChooser.getSelectedFile().getAbsolutePath()));
					// Set the text field content to the path of the output file
					fieldOutputFile.setText(fileManager.getOutputFile().toString());
					// Message Dialog to notify the user that everything went good
					JOptionPane.showMessageDialog(null, fileManager.getOutputFile().getName() + " has been selected successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}
}
