import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Application {
	/**
	 * Creates new frame(content within window)
	 */
	private JDialog dialog;
	
	/**
	 * Creates a File Manager that manages with selected files
	 */
	FileManager fileManager = new FileManager();
	
	/**
	 * Creates a Generator that generates documentation for JSON Schema
	 */
	Generator generator = new Generator();
	
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
					window.dialog.setVisible(true);
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
		dialog = new JDialog();
		dialog.setBounds(100, 100, 640, 480);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setTitle("JSON Schema Documentation Generator");
		dialog.getContentPane().setLayout(null);
		
		// Declare and initialize text field for input file
		JTextField fieldInputFile;
		fieldInputFile = new JTextField();
		fieldInputFile.setBounds(30, 140, 420, 30);
		dialog.getContentPane().add(fieldInputFile);
		fieldInputFile.setColumns(10);
		
		// Declare and initialize text field for output file
		JTextField fieldOutputFile;
		fieldOutputFile = new JTextField();
		fieldOutputFile.setColumns(10);
		fieldOutputFile.setBounds(30, 220, 420, 30);
		dialog.getContentPane().add(fieldOutputFile);
		
		// Declare and initialize label for input text field
		JLabel InputFileLabel = new JLabel("JSON Schema URL");
		InputFileLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		InputFileLabel.setBounds(30, 110, 135, 30);
		dialog.getContentPane().add(InputFileLabel);
		
		// Declare and initialize label for output text field
		JLabel OutputFileLabel = new JLabel("Output File");
		OutputFileLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		OutputFileLabel.setBounds(30, 190, 135, 30);
		dialog.getContentPane().add(OutputFileLabel);
		
		// Declare and initialize label for title
		JLabel titleLabel = new JLabel("Generate JSON Schema Documentation");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		titleLabel.setBounds(150, 25, 330, 40);
		dialog.getContentPane().add(titleLabel);
		
		// Declare and initialize check box for "only required content"
		JCheckBox requiredCheckBox = new JCheckBox("Only required content");
		requiredCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		requiredCheckBox.setBounds(30, 275, 180, 20);
		dialog.getContentPane().add(requiredCheckBox);
		
		// Declare and initialize check box for "include examples"
		JCheckBox includeCheckBox = new JCheckBox("Include examples");
		includeCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		includeCheckBox.setBounds(30, 300, 180, 20);
		dialog.getContentPane().add(includeCheckBox);
		
		// Declare and initialize generate button
		JButton generateButton = new JButton("Generate");
		generateButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		generateButton.setBounds(150, 380, 140, 30);
		dialog.getContentPane().add(generateButton);
		
		// Declare and initialize cancel button
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cancelButton.setBounds(330, 380, 140, 30);
		dialog.getContentPane().add(cancelButton);
		
		// Declare and initialize browse input file button
		JButton browseInputButton = new JButton("Browse");
		browseInputButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		browseInputButton.setBounds(460, 140, 140, 30);
		dialog.getContentPane().add(browseInputButton);
		
		// Declare and initialize browse output file button
		JButton browseOutputButton = new JButton("Browse");
		browseOutputButton.setBounds(460, 220, 140, 30);
		browseOutputButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dialog.getContentPane().add(browseOutputButton);
		
		//----------------------------------------
		
		// When cancel button clicked
		cancelButton.addActionListener(new ActionListener() {
			@ Override
			public void actionPerformed(ActionEvent e) {
				// Close the application
				System.exit(1);
			}
		});
		
		// When browse input file clicked
		browseInputButton.addActionListener(new ActionListener() {
			@Override
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
				
				try {
					// Save the output file via File Manager
					fileManager.setInputFile(new File(fileChooser.getSelectedFile().getAbsolutePath()));
				}
				catch(Exception ex) {
					// Print exception
					ex.printStackTrace();
					// If no file was selected then show message about this
					JOptionPane.showMessageDialog(null, "Please select a file!", "Error", JOptionPane.ERROR_MESSAGE);
					// Stop the execution of this function
					return;
				}
				
				// Save the selected file's extension
				String fileExtension = fileManager.getInputFile().getName().toLowerCase();
				
				// If file doesn't end with json then show error message and set the file to null
				if(!fileExtension.endsWith(".json")) {
					JOptionPane.showMessageDialog(null, fileManager.getInputFile().getName() + " doesn't have the correct extension!", "Error", JOptionPane.ERROR_MESSAGE);
					fileManager.setInputFile(null);
				}// If file has been chosen
				else if(response == JFileChooser.APPROVE_OPTION) {
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
				
				try {
					// Save the output file via File Manager
					fileManager.setOutputFile(new File(fileChooser.getSelectedFile().getAbsolutePath()));
				}
				catch(Exception ex) {
					// Print exception
					ex.printStackTrace();
					// If no file was selected then show message about this
					JOptionPane.showMessageDialog(null, "Please select a file!", "Error", JOptionPane.ERROR_MESSAGE);
					// Stop the execution of this function
					return;
				}
				
				// Save the selected file's extension
				String fileExtension = fileManager.getOutputFile().getName().toLowerCase();
				
				// If file doesn't end with txt/html/json then show error message and set the file to null
				if(!fileExtension.endsWith(".txt") && !fileExtension.endsWith(".html") && !fileExtension.endsWith(".json")) {
					JOptionPane.showMessageDialog(null, fileManager.getOutputFile().getName() + " doesn't have the correct extension!", "Error", JOptionPane.ERROR_MESSAGE);
					fileManager.setOutputFile(null);
				}// If file has been chosen
				else if(response == JFileChooser.APPROVE_OPTION) {
					// Set the text field content to the path of the output file
					fieldOutputFile.setText(fileManager.getOutputFile().toString());
					// Message Dialog to notify the user that everything went good
					JOptionPane.showMessageDialog(null, fileManager.getOutputFile().getName() + " has been selected successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
			}	
		});
		
		// When generate button clicked
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileManager.getOutputFile() == null || fileManager.getInputFile() == null) {
					JOptionPane.showMessageDialog(null, "Please select required files!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				} // If file already exists, ask to overwrite it
				else if(fileManager.getOutputFile().exists()) {
					int input = JOptionPane.showConfirmDialog(null, "The file already exists. Overwrite it?", "File exists", JOptionPane.YES_NO_OPTION);
					
					switch(input) {
					// If user clicked yes then erase all contents of the file and generate documentation
					case JOptionPane.YES_OPTION:
						try {
							generator.Generate();
						}
						catch(IOException ex) {
							// Print exception
							ex.printStackTrace();
						}
						return;
					// If user clicked no then stop execution of this function
					case JOptionPane.NO_OPTION:
						return;
					}
				}
				
				try {
					// If everything went good then generate documentation
					generator.Generate();
				}
				catch(IOException ex) {
					// Print exception
					ex.printStackTrace();
				}
			}
		});
	}
}
