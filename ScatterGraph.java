import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.plot.PlotOrientation;

import java.util.HashMap;
import java.util.Iterator;
public class ScatterGraph {  
        public void createGraph(String outputFilename, int id) throws Exception{                
                FileInputStream chart_file_input = new FileInputStream(new File(outputFilename));
                XSSFWorkbook my_workbook = new XSSFWorkbook(chart_file_input);
                String[] array1 = {"0001","0003","0004","0010","0020","0021","0022","0030","0100","0101","0200","0120","0300","9000","9001"
        				,"9003","9004","9005","9006","9007","9008","9009","9010","9011","9012","9013","9014","9015",
        				"9016","9017","9018","9019"};
        		String[] array2 = {"Machine on","Operator log on","Operator log off","Cut","Saw blade change","Cutting plate changed",
        				"New Miller installed","Automatic Labeling – Label applied","Book started","Book canceled","Format produced",
        				"Book produced","Start Order in Infeed area","Miscellaneous","Break","Repair","No Material","No Tool",
        				"No Dust Extraction","No forklift","storage full","Cleaning / Service","Sawblade change","Info / Training",
        				"Material Transport","Empty waste container","Fault external infeed","Fault rip cut saw","Fault cross cut saw",
        				"Paper change / Fautl printer","No Order / schedule","Other work"};
        		HashMap<String,String> hash = new HashMap<>();
        		for(int i=0;i<array1.length;i++){
        			hash.put(array2[i], array1[i]);
        		}
                XSSFSheet my_sheet = my_workbook.getSheetAt(0);
                DefaultCategoryDataset my_bar_chart_dataset = new DefaultCategoryDataset();
                Iterator<Row> rowIterator = my_sheet.iterator(); 
                String chart_label="a";
                Number chart_data=0;            
                while(rowIterator.hasNext()) {
                        Row row = rowIterator.next();  
                        Iterator<Cell> cellIterator = row.cellIterator();
                                while(cellIterator.hasNext()) {
                                        Cell cell = cellIterator.next(); 
                                        switch(cell.getCellType()) { 
                                        case Cell.CELL_TYPE_NUMERIC:
                                                chart_data=cell.getNumericCellValue();
                                                break;
                                        case Cell.CELL_TYPE_STRING:
                                                chart_label=cell.getStringCellValue();
                                                if(id==0){
                                                	chart_label = hash.get(chart_label);
                                                }
                                                break;
                                        }
                                }            
                my_bar_chart_dataset.addValue(chart_data.doubleValue(),"Downtime",chart_label);
                }               
                JFreeChart BarChartObject=ChartFactory.createBarChart("Downtime Graph","Entity","Time",my_bar_chart_dataset,PlotOrientation.VERTICAL,true,true,false);                
                int width=1280; 
                int height=700;             
                ByteArrayOutputStream chart_out = new ByteArrayOutputStream();          
                ChartUtilities.writeChartAsPNG(chart_out,BarChartObject,width,height);
                int my_picture_id = my_workbook.addPicture(chart_out.toByteArray(), Workbook.PICTURE_TYPE_PNG);
                chart_out.close();
                XSSFDrawing drawing = my_sheet.createDrawingPatriarch();
                ClientAnchor my_anchor = new XSSFClientAnchor();
                my_anchor.setCol1(4);
                my_anchor.setRow1(5);
                XSSFPicture  my_picture = drawing.createPicture(my_anchor, my_picture_id);                
                my_picture.resize();                
                chart_file_input.close();               
                FileOutputStream out = new FileOutputStream(new File(outputFilename));
                my_workbook.write(out);
                out.close();   
                my_workbook.close();
        }
}
