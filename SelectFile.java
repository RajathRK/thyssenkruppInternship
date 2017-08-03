import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JButton;
public class SelectFile {
	public ArrayList<String> selectFile() {
		String filename = "";
		JButton open = new JButton();
		JFileChooser fc = new JFileChooser();
		fc.setMultiSelectionEnabled(true);
		fc.setDialogTitle("Select CSV files");
		ArrayList<String> fileNames = new ArrayList<String>();                
		if(fc.showOpenDialog(open)==JFileChooser.APPROVE_OPTION){
			File[] files = fc.getSelectedFiles();
			for(int x=0;x<files.length;x++){
				filename = files[x].getAbsolutePath();
				filename = filename.replace("\\","\\\\");
				fileNames.add(filename);                        
			}
		}
		return fileNames;
	}
}

