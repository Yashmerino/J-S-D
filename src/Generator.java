
/**
 * Class that generates documentation for JSON Schema
 */
public class Generator {
	/**
	 * Creates a File Manager that manages with selected files
	 */
	FileManager fileManager = new FileManager();
	
	public void Generate() {
		System.out.println(fileManager.getOutputFile());
	}
}
