import java.text.SimpleDateFormat;


public class TimeDifference {
	public Float timeDifference(String time1, String time2){
		String dateStart = time1;
		String dateStop = time2;
		long diffHours = 0;
		long diffMinutes = 0;
		long diffSeconds = 0;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        
		java.util.Date d1 = null;
		java.util.Date d2 = null;
		try{
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);
			long diff = d2.getTime() - d1.getTime();
			diffSeconds = (diff / 1000 % 60);
			diffMinutes = (diff / (60 * 1000) % 60)*60;
			diffHours = (diff / (60 * 60 * 1000) % 24)*60*60;
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return ((float) ((diffSeconds+diffMinutes+diffHours)));
	}
}
