package DataBase.Assignment;

import java.io.BufferedReader; 
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Mongo {
	
	@SuppressWarnings("unchecked")
	public JSONArray add_array3(String Sensor_ID, String Sensor_Name, String Hourly_Counts, String ID)
	{
		JSONObject arrayElementThree = new JSONObject();
		arrayElementThree.put("Sensor_ID", Sensor_ID);
		arrayElementThree.put("Sensor_Name", Sensor_Name);
		arrayElementThree.put("Hourly_count", Hourly_Counts);
		arrayElementThree.put("ID", ID);
		
		JSONArray array3 = new JSONArray();
		array3.add(arrayElementThree);
		
		return array3;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray add_array2(String Date_Time, String Time, JSONArray array3)
	{
		JSONObject arrayElementTwo = new JSONObject();
		arrayElementTwo.put("Date_Time", Date_Time);
		arrayElementTwo.put("Time", Time);
		arrayElementTwo.put("Sensor_Data", array3);
		
		JSONArray array2 = new JSONArray();
		array2.add(arrayElementTwo);

		return array2;
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray add_array1(String Year, String Month, String MDate, String Day, JSONArray array2, JSONArray array3)
	{
		JSONObject arrayElementOne = new JSONObject();
		arrayElementOne.put("Year", Year);
		arrayElementOne.put("Month", Month);
		arrayElementOne.put("MDate", MDate);
		arrayElementOne.put("Day", Day);
		arrayElementOne.put("Ped_Acess", array2);
		
		
		JSONArray array1 = new JSONArray();
		array1.add(arrayElementOne);
		return array1;
		
	}
	
	
	
	
	@SuppressWarnings({ "unchecked", "resource" })
	public static void main (String args[]) throws IOException {
		String line = "";
		String splitBy = ",";
		BufferedReader br = null;
		String ped_details= null;
		String ped_access= null;
		
		
//		if (0 < args.length) {
//		    String filename = args[0];
//		    br = new BufferedReader(new FileReader(filename));
//		  }
//		else {
//			System.out.println("No input file given\n Please enter input file");
//			Scanner scanner = new Scanner(System.in);
//			System.out.print("Enter a file name: ");
//			System.out.flush();
//			String filename = scanner.nextLine();
//			br = new BufferedReader(new FileReader(filename));
//			
//		}
		br = new BufferedReader(new FileReader("/Users/sid/Documents/RMIT/Sem-6 March 2021/Database System/Assignment 1/TestData.csv"));
		double counter=0;
		
        FileWriter file = new FileWriter("/Users/sid/Documents/RMIT/Sem-6 March 2021/Database System/Assignment 1/Task_2_Mongo/Ped_output.json");
        JSONArray array = new JSONArray();
       
        String Year = "";
        String Time = "";
        String ID = "";
       
        
        
        boolean check=false;
	        
		while ((line = br.readLine()) != null) 
		{
			String[] data = line.split(splitBy); 
			
			if(Year.equals(data[2]))
			{
				if(Time.equals(data[6]))
				{
					if(ID.equals(data[0]))
					{
						
					}
					else {
						
					}
				}
				
			}
			else {
				
			}
			
			Year = data[2];
			Time = data[6];
			ID = data[0];
			
			
			
			if(check == true || ped_access == null || ped_details == null) {
				
		JSONObject arrayElementThree = new JSONObject();
		arrayElementThree.put("Sensor_ID", data[7]);
		arrayElementThree.put("Sensor_Name", data[8]);
		arrayElementThree.put("Hourly_count", data[9]);
		arrayElementThree.put("ID", data[0]);
		
		JSONArray array3 = new JSONArray();
		array3.add(arrayElementThree);
		
		
		JSONObject arrayElementTwo = new JSONObject();
		arrayElementTwo.put("Date_Time", data[1]);
		arrayElementTwo.put("Time", data[6]);
		arrayElementTwo.put("Sensor_Data", array3);
		ped_access = data[1];
		
		
		JSONArray array2 = new JSONArray();
		array2.add(arrayElementTwo);
		

		JSONObject arrayElementOne = new JSONObject();
		arrayElementOne.put("Year", data[2]);
		arrayElementOne.put("Month", data[3]);
		arrayElementOne.put("MDate", data[4]);
		arrayElementOne.put("Day", data[5]);
		arrayElementOne.put("Ped_Acess", array2);
		ped_details = data[5];
		
		
		
		JSONArray array1 = new JSONArray();
		array1.add(arrayElementOne);
		

		JSONObject arrayElement = new JSONObject();
		arrayElement.put("Ped_Details", array1);
		
		
		array.add(arrayElement);
		file.write(array.toJSONString()); 
		counter+=1;
		if(counter == 4096)
		{
			array = new JSONArray();
		}
		
		
		
        file.flush();
        
			}
			check = true;
		
		}
		

		
		
			
	}
}
