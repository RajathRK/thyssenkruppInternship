import java.io.File;
import java.util.ArrayList;

public class SelectFileNew {
	public ArrayList<String> selectFileNew() {
		//String filename = "";
		ArrayList<String> fileNames = new ArrayList<String>();                
		File folder = new File("C:\\Users\\cshekhar\\Desktop\\Old\\Project\\Data");
		File[] listOfFiles = folder.listFiles();
		for (File file:listOfFiles){
			//System.out.println(file.getName());
			String filename1 = file.getName();
			filename1 = file.getAbsolutePath();
			filename1 = filename1.replace("\\","\\\\");
			//System.out.println(filename1);
			fileNames.add(filename1);
		}
		return fileNames;
	}
}
