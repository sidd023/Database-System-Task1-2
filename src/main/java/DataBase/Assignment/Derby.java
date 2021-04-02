package DataBase.Assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Derby {
	
	public static void main(String args[]) throws IOException, CsvValidationException
	{
		
		
		
		
		PrintWriter pw1 = null,pw2 = null,pw3 = null;
        try {
            pw1 = new PrintWriter(new File("/Users/sid/Desktop/Ped_Count.csv"));
            pw2 = new PrintWriter(new File("/Users/sid/Desktop/Ped_Access.csv"));
            pw3 = new PrintWriter(new File("/Users/sid/Desktop/Sensor.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
        
        try (CSVReader reader = new CSVReader(new FileReader("/Users/sid/Desktop/BigData.csv"))) {
	        String[] record;
	        while ((record = reader.readNext()) != null) {
	            pw1.append(record[0]+","+record[1]+","+record[2]+","+record[3]+"\n");
		        pw2.append(record[0]+","+record[4]+","+record[5]+","+record[6]+"\n");
		        pw3.append(record[0]+","+record[7]+","+record[8]+","+record[9]+"\n");
	        }
	    }
		pw1.flush();
		pw1.close();
		pw2.flush();
		pw2.close();
		pw3.flush();
		pw3.close();
	}

}
