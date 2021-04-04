package DataBase.Assignment;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class dbload {

	public static ByteArrayOutputStream bos = new ByteArrayOutputStream();
	public static DataOutputStream dos = new DataOutputStream(bos);

	private static void intToByteArray(int i) throws IOException {
		dos.writeInt(i);
		dos.flush();
	}

	private static void stringToByteArray(String s) throws IOException {
		dos.writeBytes(s);
		dos.flush();
	}

	private static byte[] getByteArray() throws IOException {
		return bos.toByteArray();
	}

	public static void main(String args[]) throws IOException, CsvValidationException {

		String filepath = "/Users/sid/Desktop/Data1.csv";
		int bytes_used = 0;
		int record_size = 0;
		int page_no = 0;
		int page_size = 4096;
		int offset = 0;
		String heapfile = "HeapFile.dat";
		byte[] get_byte = null;
		byte[] ped_records = null;
		boolean first_line = true;
		double record_read = 0;

		try (CSVReader row = new CSVReader(new FileReader(filepath))) {
			String[] data;
			while ((data = row.readNext()) != null) {
				if (first_line == false) {

					Record_Details record = new Record_Details();
					record.setID(data[0]);
					record.setDate_Time(data[1]);
					record.setYear(data[2]);
					record.setMonth(data[3]);
					record.setMdate(data[4]);
					record.setDay(data[5]);
					record.setTime(data[6]);
					record.setSensor_ID(data[7]);
					record.setSensor_Name(data[8]);
					record.setHourly_Counts(data[9]);
					record.setSTD_NAME(data[7],data[1]);
					record_size = record.getRecord_size();
					
					byte[] nn = new byte[] {};
					
					String val = "1111000011110001";
				    byte[] bval = new BigInteger(val, 6).toByteArray();
				    for(int i = 0 ; i< bval.length; i++)
				    {
				    	System.out.println(bval[i]);
				    }
				

					if (bytes_used < page_size) {
						if (bytes_used + record_size > page_size) {
							ped_records = getByteArray();

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
						record_read += 1;

						
						ped_records = getByteArray();
						

					}

				}
				first_line = false;

			}
		}

		/*
		 * while ((line = reader.readLine()) != null) { if(first_line == false) {
		 * 
		 * 
		 * String[] data = line.split(splitBy);
		 * 
		 * // Store properly
		 * 
		 * 
		 * int ID = Integer.parseInt(data[0]); record_size +=1;
		 * 
		 * 
		 * String Date_Time = String.format("%1$"+23+ "s", data[1]); //fixed record_size
		 * +=23;
		 * 
		 * int Year = Integer.parseInt(data[2]); record_size +=1;
		 * 
		 * String Month = String.format("%1$"+9+ "s", data[3]); //fixed record_size +=9;
		 * 
		 * int MDate = Integer.parseInt(data[4]); record_size +=1;
		 * 
		 * String Day = String.format("%1$"+9+ "s", data[5]); //fixed record_size +=5;
		 * 
		 * int Time = Integer.parseInt(data[6]); record_size +=1;
		 * 
		 * int Sensor_ID = Integer.parseInt(data[7]); record_size +=1;
		 * 
		 * String Sensor_Name = data[8]; //variable get_byte = Sensor_Name.getBytes();
		 * record_size +=get_byte.length;
		 * 
		 * String c = data[9].replace(",", ""); System.out.println(c); int Hourly_Counts
		 * = Integer.parseInt(c); record_size +=1;
		 * 
		 * String STD_NAME = Sensor_ID + Date_Time; //variable get_byte =
		 * Sensor_Name.getBytes(); record_size +=get_byte.length;
		 * 
		 * 
		 * 
		 * if(bytes_used < page_size) { if(bytes_used + record_size > page_size) {
		 * page_no+=1; offset = page_no * bytes_used; RandomAccessFile raf = null; try{
		 * raf = new RandomAccessFile(file, "rw"); raf.seek((long)offset);
		 * raf.write(ped_records,0,bytes_used); } catch (FileNotFoundException e) {
		 * e.printStackTrace(); } finally { raf.close(); }
		 * 
		 * bytes_used = 0; } bytes_used += record_size;
		 * 
		 * //ped_records = new byte[] {(byte) ID , 87, 46, 46, 46};
		 * 
		 * // store in peds_records
		 * 
		 * 
		 * 
		 * 
		 * } } first_line = false; }
		 */

	}
}
