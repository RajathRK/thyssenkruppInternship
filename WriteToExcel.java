import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteToExcel {
	public String writeToExcel(HashMap<String,Float> hm,String outputFile,int id) {
		int rownum = 0;
		String[] array1 = {"0001","0003","0004","0010","0020","0021","0022","0030","0100","0101","0200","0120","0300","9000","9001"
				,"9003","9004","9005","9006","9007","9008","9009","9010","9011","9012","9013","9014","9015",
				"9016","9017","9018","9019"};
		String[] array2 = {"Machine on","Operator log on","Operator log off","Cut","Saw blade change","Cutting plate changed",
				"New Miller installed","Automatic Labeling – Label applied","Book started","Book canceled","Format produced",
				"Book produced","Start Order in Infeed area","Miscellaneous","Break","Repair","No Material","No Tool",
				"No Dust Extraction","No forklift","storage full","Cleaning / Service","Sawblade change","Info / Training",
				"Material Transport","Empty waste container","Fault external infeed","Fault rip cut saw","Fault cross cut saw",
				"Paper change / Fautl printer","No Order / schedule","Other work"};
		HashMap<String,String> names = new HashMap<String, String>();
		for(int i=0;i<array1.length;i++){
			names.put(array1[i], array2[i]);
		}
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Details");
		for(String key : hm.keySet()){
			Row row = sheet.createRow(rownum++);
			int cellnum = 0;
			Cell cell = row.createCell(cellnum++);
			if(id==0){
				cell.setCellValue(names.get(key));
			}else{
				cell.setCellValue(key);
			}
			Cell cell1 = row.createCell(cellnum++);
			cell1.setCellValue(hm.get(key)/60);
		}
		try
        {
            FileOutputStream out = new FileOutputStream(new File(outputFile));
            workbook.write(out);
            out.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return outputFile;
	}
}
