import java.io.File;

/**
 * Class that manages files selected by user
 */
public class FileManager {
	/**
	 * File to store JSON Schema
	 */
	static private File inputFile;

	/**
	 * File to store generated documentation of JSON Schema
	 */
	static private File outputFile;

	/**
	 * String to store output file's extension
	 */
	static private String outputExtension;
	
	/**
	 * Gets the extension of output file
	 * 
	 * @return the outputExtension
	 */
	public String getOutputExtension() {
		return outputExtension;
	}

	/**
	 * Sets the extension of output file
	 * 
	 * @param outputExtension - the outputExtension to set
	 */
	public void setOutputExtension(String outputExtension) {
		FileManager.outputExtension = outputExtension;
	}

	/**
	 * Constructor of the class
	 */
	FileManager() {
		// By default the extension will be .txt
		outputExtension = ".txt";
		// Set files to null
		outputFile = null;
		inputFile = null;
	}

	/**
	 * Gets the file that stores JSON Schema
	 * 
	 * @return this inputFile(JSON Schema)
	 */
	public File getInputFile() {
		return inputFile;
	}

	/**
	 * Gets the file that stores generated JSON Schema Documentation
	 * 
	 * @return this outputFile(Generatated JSON Schema Documentation)
	 */
	public File getOutputFile() {
		return outputFile;
	}

	/**
	 * Changes the file that stores JSON Schema
	 * 
	 * @param inputFile - This file's new object to store
	 */
	public void setInputFile(File inputFile) {
		FileManager.inputFile = inputFile;
	}

	/**
	 * Changes the file that stores generated JSON Schema Documentation
	 * 
	 * @param outputFile - This file's new object to store
	 */
	public void setOutputFile(File outputFile) {
		FileManager.outputFile = outputFile;
	}

}
