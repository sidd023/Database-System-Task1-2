package DataBase.Assignment;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class dbload {

	
	@SuppressWarnings("null")
	public static void main(String args[]) throws IOException, CsvValidationException {

		String filepath = "/Users/sid/Desktop/Data1.csv";
		int bytes_used = 0;
		int record_size = 0;
		int page_no = 0;
		int page_size = 4096;
		int offset = 0;
		String heapfile = "HeapFile.dat";
		byte[] ped_records = null;
		boolean first_line = true;
		int record_read = 0;
		Record_Details[] record = new Record_Details[page_size];
		
//		byte nnn = (byte) ':';
//		System.out.println("SIZE  "+nnn);
		

		try (CSVReader row = new CSVReader(new FileReader(filepath))) {
			String[] data;
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
					record[record_read].setSTD_NAME(data[7],data[1]);
					record_size = record[record_read].get_total_bytes();
					
					
				    
						if (bytes_used + record_size > page_size) {
							
							page_no += 1;
							offset = page_no * bytes_used;
							
							RandomAccessFile raf = null;
							try {
								raf = new RandomAccessFile(heapfile, "w");
								raf.seek((long) offset);
								raf.write(ped_records, 0, ped_records.length - 1);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} finally {
								raf.close();
							}

							bytes_used = 0;
						}
						
							bytes_used += record_size;
							byte[] this_rec = record[record_read].get_byte_data(record[record_read], record_size);	
							ByteBuffer buff = ByteBuffer.wrap(ped_records);
							buff.put(this_rec);
							
							record_read += 1;
							
						
				}
				first_line = false;

			}
		}

	}
}
