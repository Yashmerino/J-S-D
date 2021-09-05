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
	 * Constructor of the class
	 */
	FileManager() {

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
	 * @param inputFile This file's new object to store
	 */
	public void setInputFile(File inputFile) {
		FileManager.inputFile = inputFile;
	}

	/**
	 * Changes the file that stores generated JSON Schema Documentation
	 * 
	 * @param outputFile This file's new object to store
	 */
	public void setOutputFile(File outputFile) {
		FileManager.outputFile = outputFile;
	}

}
