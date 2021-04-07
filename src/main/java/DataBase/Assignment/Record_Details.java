package DataBase.Assignment;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Record_Details {

	private byte[] ID = new byte[4];

	private byte[] Date_Time = new byte[22];

	private byte[] Year = new byte[4];

	private byte[] Month = new byte[9];

	private byte[] Day = new byte[9];

	private byte[] Sensor_Name;

	private byte[] Mdate = new byte[4];

	private byte[] Time = new byte[4];

	private byte[] Sensor_ID = new byte[4];

	private byte[] Hourly_Counts = new byte[4];


	private byte[] STD_NAME = new byte[25];

	private byte delimiter = ';';
	
	private byte end_of_record = '|';
	

	public Record_Details() {
		super();
		
		
		
	}

	public byte[] getSTD_NAME() {
		return STD_NAME;
	}

	
	public byte[] getID() {
		return ID;
	}

	public byte[] getDate_Time() {
		return Date_Time;
	}

	public byte[] getYear() {
		return Year;
	}

	public byte[] getMonth() {
		return Month;
	}

	public byte[] getDay() {
		return Day;
	}

	public byte[] getSensor_Name() {
		return Sensor_Name;
	}

	public byte[] getMdate() {
		return Mdate;
	}

	public byte[] getTime() {
		return Time;
	}

	public byte[] getSensor_ID() {
		return Sensor_ID;
	}

	public byte[] getHourly_Counts() {
		return Hourly_Counts;
	}
	
	public byte getDelimiter() {
		return delimiter;
	}
	
	public byte getEndofRecord() {
		return end_of_record;
	}
	
	
	
	//----------------------------------------INT FIELDS-----------------------------------------------------------------------------------------------

	public void setID(String iD) {
		ID = int_to_byte_array(Integer.parseInt(iD));	
		//System.out.println(ID.length);
	}

	public void setYear(String year) {
		Year = int_to_byte_array(Integer.parseInt(year));
	}
	public void setMdate(String mdate) {
		Mdate = int_to_byte_array(Integer.parseInt(mdate));
	}

	public void setTime(String time) {
		Time = int_to_byte_array(Integer.parseInt(time));
	}

	public void setSensor_ID(String sensor_ID) {
		Sensor_ID = int_to_byte_array(Integer.parseInt(sensor_ID));
	}

	public void setHourly_Counts(String hourly_Counts) {
		Hourly_Counts = int_to_byte_array(Integer.parseInt(hourly_Counts.replace(",", "")));
		
	}
	
	
	//----------------------------------------STRING FIELDS-----------------------------------------------------------------------------------------------
	
	
	public void setSTD_NAME(String sensor_ID, String DateTime) {
		STD_NAME = string_to_byte_array(strToBinary(sensor_ID + " " + DateTime));
		
	}

	public void setDate_Time(String date_Time) {
		Date_Time = string_to_byte_array(strToBinary(date_Time));
		
	}

	public void setMonth(String month) {
		Month = string_to_byte_array(strToBinary(month));
		
	}

	public void setDay(String day) {
		Day = string_to_byte_array(strToBinary(day));
		
	}

	//variable size
	public void setSensor_Name(String sensor_Name) {
		Sensor_Name = string_to_byte_array(strToBinary(sensor_Name));
		
	}

	

	//----------------------------------------FUNCTIONS -----------------------------------------------------------------------------------------------
	
	public byte[] int_to_byte_array(int i){
		    ByteBuffer bb = ByteBuffer.allocate(4); 
		    bb.putInt(i); 
		   // System.out.println(byte_array_to_int(bb.array()));
		    return bb.array();
	}
	
	public int byte_array_to_int(byte[] data){
		return ByteBuffer.wrap(data).getInt();
	}
	
	public byte[] string_to_byte_array(String string)
	{
		string = string.replaceAll("\\s+","");
		
		List<Integer> list = new ArrayList<>();
        for(String str : string.split("(?<=\\G.{8})"))
        {
        	
        	list.add(Integer.parseInt(str,2));
        }
            
        
        byte[] data = new byte[list.size()];
        for(int i=0; i< list.size(); i++)
        {
        	int bin = list.get(i);
        	data[i] = (byte) bin; 
        }
        //System.out.println(new String(data));
		return data;
	}
	
	
	
	
	
	public String byte_array_to_string(byte[] data)
	{
		 return new String(data);
	}
	
	public int get_total_bytes()
	{
		int tot_bytes = this.ID.length + this.Date_Time.length + this.Year.length + this.Month.length + this.Mdate.length 
				 + this.Day.length + this.Time.length + this.Sensor_ID.length  + this.Hourly_Counts.length;
		
		
		
		System.out.println(tot_bytes);
		
		
		tot_bytes+= this.Sensor_Name.length;
		
		return tot_bytes;
		
	}
	
	public byte[] get_byte_data(Record_Details record , int record_size)
	{
		
		byte[] allByteArray = new byte[record_size];

		ByteBuffer buff = ByteBuffer.wrap(allByteArray);
		
			buff.put(record.getID());
			buff.put(delimiter);
			buff.put(record.getDate_Time());
			buff.put(delimiter);
			buff.put(record.getYear());
			buff.put(delimiter);
			buff.put(record.getMonth());
			buff.put(delimiter);
			buff.put(record.getMdate());
			buff.put(delimiter);
			buff.put(record.getDay());
			buff.put(delimiter);
			buff.put(record.getTime());
			buff.put(delimiter);
			buff.put(record.getSensor_ID());
			buff.put(delimiter);
			buff.put(record.getSensor_Name());
			buff.put(delimiter);
			buff.put(record.getHourly_Counts());
			buff.put(delimiter);
			buff.put(record.getSTD_NAME());
			
			buff.put(end_of_record);
				
			
			
		return buff.array();
	}
	
	
	
	public String strToBinary(String s)
    {
        int n = s.length();
        String bin_str = "";
        for (int i = 0; i < n; i++) 
        {
            int val = Integer.valueOf(s.charAt(i));
            String bin = "";
            while (val > 0) 
            {
                if (val % 2 == 1)
                {
                    bin += '1';
                }
                else
                    bin += '0';
                val /= 2;
            }
            bin = reverse(bin);
            while(bin.length()%8 != 0)
            {
            	bin = "0"+ bin;
            }
            bin_str+=bin+ " ";
            
        } 
		return bin_str;
    }
  
    public static String reverse(String input) 
    {
        char[] a = input.toCharArray();
        int l, r = 0;
        r = a.length - 1;
  
        for (l = 0; l < r; l++, r--)
        {
            
            char temp = a[l];
            a[l] = a[r];
            a[r] = temp;
        }
        return String.valueOf(a);
    }
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------
   
    
    

}

