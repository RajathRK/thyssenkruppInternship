import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
public class ReadFile {
	public HashMap<String,HashMap<String,Float>> readFileData(ArrayList<String> file) {
		HashMap<String,Float> hm = new HashMap<String,Float>();
		HashMap<String,HashMap<String,Float>> result = new HashMap<>();
		TimeDifference td = new TimeDifference();
		HashMap<String,Float> startEnd = new HashMap<>(); 
		String time1 = null,time2,timear,time9013;
		String data = "data",se = "se";
		Float time;
		int BUFFER_SIZE = 1000;
		String[] array = {"0001","0003","0004","0010","0020","0021","0022","0030","0100","0101","0200","0120","0300","9000","9001"
				,"9003","9004","9005","9006","9007","9008","9009","9010","9011","9012","9013","9014","9015",
				"9016","9017","9018","9019"};
		for(String str:array){
			hm.put(str, (float) 0);
		}
		for(String f : file){
			Float timetemp=(float) 0.0;
			Float timeTaken=(float)0;
			String startTemp = null,endTemp;
			String[] fileName = f.split("\\\\");
			String fileN = fileName[fileName.length-1];
			//ArrayList<String> ar = new ArrayList<String>();
			String ar= null;
			HashMap<String,Float> temp = new HashMap<String,Float>();
			for(String str:array){
				temp.put(str, (float) 0);
			}
			try (BufferedReader br = new BufferedReader(new FileReader(f),327689)) {
			    String line,line2;
			    while ((line = br.readLine()) != null) {
			       String[] words = line.split(";");
			       if(words[0].equals("0003")){
			    	   String[] str1;
		    		   str1 = words[1].split("-");
		    		   startTemp = str1[1];
			       }
			       if(words[0].equals("0002")){
			    	   String[] str1;
		    		   str1 = words[1].split("-");
		    		   endTemp = str1[1];
		    		   timeTaken += td.timeDifference(startTemp, endTemp);
			    	   continue;
			       }
			       if(words[0].equals("0010")){
			    	   String[] str1;
		    		   str1 = words[1].split("-");
		    		   timear = str1[1];
		    		   ar = (timear);
			       }
			       if(words[0].equals("9013")){
			    	   String[] str1;
		    		   str1 = words[1].split("-");
		    		   time9013 = str1[1];
		    		   //timear = ar.get(ar.size()-1);
		    		   timear = ar;
		    		   time = td.timeDifference(timear, time9013);
		    		   temp.put(words[0],((time)+temp.get(words[0])));
		    		   timetemp += time;
		    		   continue;
			       }
			       br.mark(BUFFER_SIZE);
			       if((line2 = br.readLine()) == null){
			    	   continue;
			       }
			       String[] words1 = line2.split(";");
			       for(String str : temp.keySet()){
			    	   if(str.equals(words[0])){
			    		   String[] str1;
			    		   str1 = words[1].split("-");
			    		   time1 = str1[1];
			    		   break;
			    	   }
			       }
			       String[] str1;
			       str1 = words1[1].split("-");
			       time2 = str1[1];
			       time = td.timeDifference(time1, time2);
			       temp.put(words[0],((time)+temp.get(words[0])));
			       br.reset();
			    }
			    br.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
			startEnd.put(fileN, timeTaken/60);
			time = temp.get("0010");
			temp.put("0010",(time-timetemp));
			for(String key : temp.keySet()){
				hm.put(key, temp.get(key)+hm.get(key));
			}
		}
		result.put(data, hm);
		result.put(se, startEnd);
		return result;
	}
}
