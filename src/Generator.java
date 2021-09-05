import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

import org.json.JSONException;
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
	 * 
	 * @throws IOException
	 */
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

		try {
			// Declare and initialize objects that will store info we need to get
			JSONObject properties = object.getJSONObject("properties");
			JSONObject definitions = object.getJSONObject("definitions");

			// Get documentation about properties and definitions
			getDocumentation(properties);
			getDocumentation(definitions);
		} catch (JSONException ex) {
			System.out.println("Some key not found");
			ex.printStackTrace();
		}
	}

	/**
	 * Gets the documentation about an certain object
	 * 
	 * @param objects - Stores the object we need to get info about
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

			try {
				currentObject.getString("description");
				currentObject.getString("title");

				// Store the documentation about current object in documentation HashMap
				if (fileManager.getOutputExtension() == ".txt") {
					documentation.put(currentObjectName, " Title: " + currentObject.getString("title") + ",\n "
							+ " Description: " + currentObject.getString("description"));
				} else if (fileManager.getOutputExtension() == ".json") {
					documentation.put(currentObjectName, " \"Title\": \"" + currentObject.getString("title") + ",\" \n "
							+ " \"Description\": \"" + currentObject.getString("description") + "\"");
				} else {
					documentation.put(currentObjectName, " Title: " + currentObject.getString("title") + ",\n "
							+ " Description: " + currentObject.getString("description"));
				}
			} catch (JSONException ex) {
				System.out.println("Some key not found");
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Exports generated documentation into a file
	 * 
	 * @throws IOException
	 */
	private void exportDocumentation() throws IOException {
		// Create a BufferedWriter to write documentation into a file
		BufferedWriter writer = null;

		// Delete the output file if it already exists
		if (fileManager.getOutputFile().exists()) {
			fileManager.getOutputFile().delete();
		}

		// Link the BufferedWriter with output file
		if(fileManager.getOutputFile().toString().contains(fileManager.getOutputExtension())){
			writer = new BufferedWriter(new FileWriter(fileManager.getOutputFile()));
		}
		else {
			// If output file doesn't have extension then add it
			writer = new BufferedWriter(new FileWriter(fileManager.getOutputFile() + fileManager.getOutputExtension()));
		}
		
		
		if (fileManager.getOutputExtension() == ".txt") {
			exportDocumentationTXT(writer);
		} else if (fileManager.getOutputExtension() == ".json") {
			exportDocumentationJSON(writer);
		} else {
			exportDocumentationHTML(writer);
		}
	}

	/**
	 * Export generated documentation into a .txt file
	 * @param writer - BufferedWriter to write documentation into file
	 * @throws IOException
	 */
	private void exportDocumentationTXT(BufferedWriter writer) throws IOException {
		// Write that this is Generated Documentation and open brackets
		writer.write("Generated Documentation: { \n");

		// Write documentation into the file
		for (Map.Entry<String, String> element : documentation.entrySet()) {
			writer.write("\"" + element.getKey() + "\":{\n " + element.getValue() + "\n");
		}

		// Write a closing bracket to close Generated Data section
		writer.write("}");

		// Close the writer
		writer.close();
	}
	
	/**
	 * Export generated documentation into a .json file
	 * @param writer - BufferedWriter to write documentation into file 
	 * @throws IOException
	 */
	private void exportDocumentationJSON(BufferedWriter writer) throws IOException {
		writer.write("\"Generated data\": {\n");
		
		for(Map.Entry<String, String> element : documentation.entrySet()) {
			writer.write("\"" + element.getKey() + "\":{ \n " + element.getValue() + "\n");
		}
		
		writer.write("}");
		
		writer.close();
	}
	
	/**
	 * Export generated documentation into a .html file
	 * @param writer - BufferedWriter to write documentation into file
	 * @throws IOException
	 */
	private void exportDocumentationHTML(BufferedWriter writer) throws IOException {
		writer.write("<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "\r\n" + "<head>\r\n"
				+ "<title>Generate JSON Schema Documentation</title>\r\n" + "</head>\r\n" + "\r\n" + "<body>");
		
		for(Map.Entry<String, String> element : documentation.entrySet()) {
			writer.write("<h3>" + element.getKey() + " : </h3> \n " + " <h4>" + element.getValue() + "</h4>\n");
		}
		
		writer.write("</body>\r\n" + "</html>");
		
		writer.close();
	}
}
