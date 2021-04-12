package DataBase.Assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Derby {
	public static void main(String args[]) throws IOException, CsvValidationException
	{
		String filename=null;
		
//		if (0 < args.length) {
//		    filename = args[0];
//		  }
//		else {
//			System.out.println("No input file given\n Please enter input file");
//			Scanner scanner = new Scanner(System.in);
//			System.out.print("Enter a file name: ");
//			System.out.flush();
//			filename = scanner.nextLine();
//			
//		}
		filename ="/Users/sid/Documents/RMIT/Sem-6 March 2021/Database System/Assignment 1/BigData.csv";
		
		PrintWriter pw1 = null,pw2 = null,pw3 = null;
        try {
          //  pw1 = new PrintWriter(new File("/Users/sid/Documents/RMIT/Sem-6 March 2021/Database System/Assignment 1/Task_1_Derby/Ped_Count.txt"));
            pw2 = new PrintWriter(new File("/Users/sid/Documents/RMIT/Sem-6 March 2021/Database System/Assignment 1/Task_1_Derby/Ped_Access.csv"));
            pw3 = new PrintWriter(new File("/Users/sid/Documents/RMIT/Sem-6 March 2021/Database System/Assignment 1/Task_1_Derby/Sensor.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
        boolean check = true;
        double counter=0;
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
	        String[] record;
	        while ((record = reader.readNext()) != null) {
	        	
	        	if(check == false){
	        		// pw1.append(record[0]+","+record[1]+","+record[2]+","+record[3]+"\n");
	 		        pw2.append(record[0]+","+record[1]+","+record[2]+","+record[3]+ ","+record[4]+","+record[5]+","+record[6]+"\n");
	 		        pw3.append(record[0]+","+record[7]+","+record[8]+","+record[9].replace(",", "")+"\n");
	 		        counter+=1;
	        	}
	        	check = false;
	           
	        }
	    }
//		pw1.flush();
//		pw1.close();
		pw2.flush();
		pw2.close();
		pw3.flush();
		pw3.close();
		
		System.out.println(counter);
	}

}
