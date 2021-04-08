package DataBase.Assignment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class dbload {

	public static void main(String args[]) throws IOException, CsvValidationException {
		
//		 String filename = "";
//		 int page_size = 0;
//		if ( args.length == 2) {
//			page_size = Integer.parseInt(args[0]); 
//		    filename = args[1];
//		    
//		  }
//		else {
//			System.out.println("NO FILE");
//			return;
//		}
			
//"/Users/sid/Desktop/BigData.csv";
		String filepath = "/Users/sid/Desktop/BigData.csv";
		int bytes_used = 0;
		int record_size = 0;
		int page_no = 0;
		int page_size = 4096;
		int offset = 0;
		String heapfile = String.format("heapfile.%s", page_size);
		byte[] ped_records = new byte[page_size];
		boolean first_line = true;
		int record_read = 0;
		long total_records = 0;
		long endTime = 0;
		long startTime = 0;
		Record_Details[] record = new Record_Details[page_size];

		try (CSVReader row = new CSVReader(new FileReader(filepath))) {
			String[] data;
			startTime = System.currentTimeMillis();
			while ((data = row.readNext()) != null) {
				if (first_line == false) {

					record[record_read] = new Record_Details();
					record[record_read].setID(data[0]);
					record[record_read].setDate_Time(data[1]);
					record[record_read].setYear(data[2]);
					record[record_read].setMonth(data[3]);
					record[record_read].setMdate(data[4]);
					record[record_read].setDay(data[5]);
					record[record_read].setTime(data[6]);
					record[record_read].setSensor_ID(data[7]);
					record[record_read].setSensor_Name(data[8]);
					record[record_read].setHourly_Counts(data[9]);
					record[record_read].setSTD_NAME(data[7], data[1]);

					record_size = record[record_read].get_total_bytes();

					if (bytes_used + record_size > page_size) {

						page_no += 1;
						offset = page_no * bytes_used;

						RandomAccessFile raf = null;
						try {
							raf = new RandomAccessFile(heapfile, "rw");
							raf.seek((long) offset);
							raf.write(ped_records, 0, ped_records.length - 1);
//							for(int i=0; i<ped_records.length - 1; i++)
//							{
//								System.out.print(ped_records[i]);
//							}

						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} finally {
							raf.close();
						}
						total_records+=record_read;
						bytes_used = 0;
						record_read = 0;
					}
					bytes_used += record_size;
					byte[] this_rec = record[record_read].get_byte_data(record[record_read], page_size);
					ByteBuffer buff = ByteBuffer.wrap(ped_records);
					buff.put(this_rec);
					record_read += 1;
				}
				first_line = false;
			}
		 endTime = System.currentTimeMillis();
		}
		
		System.out.println("Total Records loaded "+ total_records);
		System.out.println("Total Pages used "+ page_no);
		System.out.println("That took " + (endTime - startTime) + " milliseconds to ceate heapfile");
		

	}
}
