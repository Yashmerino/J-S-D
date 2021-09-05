import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Class that generates documentation for JSON Schema
 */
public class Generator {
	/**
	 * Creates a File Manager that manages with selected files
	 */
	private FileManager fileManager = new FileManager();

	/**
	 * Creates a hashmap to store properties and definitions documentation
	 */
	private HashMap<String, String> documentation = new HashMap<String, String>();

	/**
	 * Generates documentation for JSON Schema
	 * @throws IOException 	 
	 * */
	public void Generate() throws IOException {
		getObjects();
		exportDocumentation();
	}

	/**
	 * Gets objects from a .JSON Schema file
	 */
	private void getObjects() {
		// String that holds the name of file
		String inputFile = "/" + fileManager.getInputFile().getName().toString();
		// Get input stream from the input file
		InputStream inputStream = Generator.class.getResourceAsStream(inputFile);

		// If input stream is equal to null then notify user about it
		if (inputStream == null) {
			JOptionPane.showMessageDialog(null, "Cannot access the input file: " + inputFile, "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Declare and initalize a tokener and an object to parse JSON file
		JSONTokener tokener = new JSONTokener(inputStream);
		JSONObject object = new JSONObject(tokener);

		// Declare and initialize objects that will store info we need to get
		JSONObject properties = object.getJSONObject("properties");
		JSONObject definitions = object.getJSONObject("definitions");

		// Get documentation about properties and definitions
		getDocumentation(properties);
		getDocumentation(definitions);
	}

	/**
	 * Gets the documentation about an certain object
	 * 
	 * @param objects - stores the object we need to get info about
	 */
	private void getDocumentation(JSONObject objects) {
		// String that will store the name of current object we need to get info about
		String currentObjectName;
		// Object that will store the current objectw we need to get info about
		JSONObject currentObject;

		// Iterator that will go throughout all titles and descriptions
		Iterator<String> iterator = objects.keys();

		while (iterator.hasNext()) {
			// currentObjectName now stores the name of the next object
			currentObjectName = iterator.next().toString();
			// currentObject now stores the object with currentObjectName name
			currentObject = objects.getJSONObject(currentObjectName);

			// Store the documentation about current object in documentation HashMap
			documentation.put(currentObjectName, " Title: \"" + currentObject.getString("title") + ", \n "
					+ " Description: " + currentObject.getString("description"));
		}
	}
	
	/**
	 * Export generated documentation into a file
	 * @throws IOException
	 */
	private void exportDocumentation() throws IOException {
		// Create a BufferedWriter to write documentation into a file
		BufferedWriter writer = null;
		
		// Delete the output file if it already exists
		if(fileManager.getOutputFile().exists()) {
			fileManager.getOutputFile().delete();
		}
		
		// Link the BufferedWriter with output file
		writer = new BufferedWriter(new FileWriter(fileManager.getOutputFile()));
		
		// Write that this is Generated Documentation and open brackets
		writer.write(" Generated Documentation: { \n");
		
		// Write documentation into the file
		for(Map.Entry<String, String> element : documentation.entrySet()) {
			writer.write("\"" + element.getKey() + "\":{\n " + element.getValue() + "\n");
		}
		
		// Write a closing bracket to close Generated Data section
		writer.write("}");
		
		// Close the writer
		writer.close();
	}
}
