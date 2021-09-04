import java.io.File;

public class FileManager {
	// File that contains all the JSON Schema data
	private File openFile;
	// File that will contain documentation
	private File saveFile;
	
	FileManager() {
		
	}
	
	// Return file that contains JSON Schema
	public File getOpenFile() {
		return openFile;
	}
	
	// Return file that will contain documentation
	public File getSaveFile() {
		return saveFile;
	}
	
	// Set file that contains JSON Schema
	public void setOpenFile(File openFile) {
		this.openFile = openFile;
	}
	
	// Set file that will contain documentation
	public void setSaveFile(File saveFile) {
		this.saveFile = saveFile;
	}
	
}
