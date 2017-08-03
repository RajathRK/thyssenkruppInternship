
import java.util.ArrayList;
import java.util.HashMap;

public class Demo {
	public static void main(String[] args) throws Exception {
		Object data = "data", se = "se";
		String outputFileNameData = null, outputFileNameTime = null;
		String outputFile1 = null, outputFile = null;
		HashMap<String,HashMap<String,Float>> result = new HashMap<String,HashMap<String,Float>>();
		HashMap<String,Float> hm = new HashMap<String,Float>();
		HashMap<String,Float> startEnd = new HashMap<>();
		ReadProperty readProperty = new ReadProperty();
		SelectFileNew sf = new SelectFileNew();
		ReadFile rd = new ReadFile();
		WriteToExcel wr = new WriteToExcel();
		ScatterGraph graph = new ScatterGraph();
		String[] str = readProperty.raedPropertiesFile();
		//outputFile = "C:\\Users\\cshekhar\\Desktop\\Old\\Project\\Graphs\\DownTime.xlsx";
		//outputFile1 = "C:\\Users\\cshekhar\\Desktop\\Old\\Project\\Graphs\\Day.xlsx";
		outputFile = str[0];
		outputFile1 = str[1];
		ArrayList<String> fileNames = sf.selectFileNew();
		result = rd.readFileData(fileNames);
		hm = result.get(data);
		startEnd = result.get(se);
		outputFileNameData = wr.writeToExcel(hm,outputFile,0);
		graph.createGraph(outputFileNameData,0);
		outputFileNameTime = wr.writeToExcel(startEnd, outputFile1,1);
		graph.createGraph(outputFileNameTime,1);
	}
}
