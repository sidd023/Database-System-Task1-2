package DataBase.Assignment;

import java.math.BigInteger;

public class Record_Details {

	private int ID;

	private String Date_Time;

	private int Year;

	private String Month;

	private String Day;

	private String Sensor_Name;

	private int Mdate;

	private int Time;

	private int Sensor_ID;

	private int Hourly_Counts;

	private int record_size = 0;

	private String STD_NAME;

	private char delimiter = ',';

	private char end_of_file = '%';

	public String getSTD_NAME() {
		return STD_NAME;
	}

	public int getRecord_size() {
		return record_size;
	}

	public int getID() {
		return ID;
	}

	public String getDate_Time() {
		return Date_Time;
	}

	public int getYear() {
		return Year;
	}

	public String getMonth() {
		return Month;
	}

	public String getDay() {
		return Day;
	}

	public String getSensor_Name() {
		return Sensor_Name;
	}

	public int getMdate() {
		return Mdate;
	}

	public int getTime() {
		return Time;
	}

	public int getSensor_ID() {
		return Sensor_ID;
	}

	public int getHourly_Counts() {
		return Hourly_Counts;
	}

	public void setID(String iD) {
		ID = Integer.parseInt(iD);
		record_size = +1;
	}

	public void setSTD_NAME(String sensor_ID, String DateTime) {
		STD_NAME = sensor_ID + ' ' + DateTime;
		record_size += STD_NAME.length();
	}

	public void setDate_Time(String date_Time) {
		Date_Time = String.format("%1$" + 23 + "s", date_Time);
		record_size += 23;
	}

	public void setYear(String year) {
		Year = Integer.parseInt(year);
		record_size += 1;
	}

	public void setMonth(String month) {
		Month = String.format("%1$" + 9 + "s", month);
		record_size += 9;
	}

	public void setDay(String day) {
		Day = String.format("%1$" + 9 + "s", day);
		record_size += 9;
	}

	public void setSensor_Name(String sensor_Name) {
		Sensor_Name = sensor_Name;
		record_size += Sensor_Name.length();
	}

	public void setMdate(String mdate) {
		Mdate = Integer.parseInt(mdate);
		record_size += 1;

	}

	public void setTime(String time) {
		Time = Integer.parseInt(time);
		record_size += 1;
	}

	public void setSensor_ID(String sensor_ID) {
		Sensor_ID = Integer.parseInt(sensor_ID);
		record_size += 1;
	}

	public void setHourly_Counts(String hourly_Counts) {
		Hourly_Counts = Integer.parseInt(hourly_Counts.replace(",", ""));
		record_size += 1;
	}
	
	
	public String convertStringToBinary(String input) {

        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            result.append(
                    String.format("%10s", Integer.toBinaryString(aChar))   
                           .replaceAll(" ", "0")                         
            );
        }
        return result.toString();

    }
	
	public void getBinary(Record_Details data)
	{
		// read each array of bytes and add them into a SUPER array 
		// with delimiters(Think how to do it )
		
	    
	}
	
	
	

}

