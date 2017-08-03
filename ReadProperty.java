import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperty {
	String[] raedPropertiesFile(){
		FileInputStream fileInput = null;
		File file = new File("C:\\Users\\cshekhar\\Desktop\\Old\\Project\\test.properties");
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Properties properties = new Properties();
		try {
			properties.load(fileInput);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String outputFile = properties.getProperty("path");
		String outputFile1 = properties.getProperty("path1");
		try {
			fileInput.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String[] str = {outputFile, outputFile1};
		return str;
	}
}
