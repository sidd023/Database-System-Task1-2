package DataBase.Assignment;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class dbquery {

	public static void main(String args[]) {

		int page_size = 4096;
		int pos = 0;
		int start_rec = 0;
		int end_rec = 0;
		int rec_no = 0;
		String heapfile = String.format("heapfile.%s", page_size);
		Record_Details record = new Record_Details();
		String input_search = "40 11/02/2019 04:00:00 AM";

		int pageNo = 0;
		boolean check = true;
		String endOfFile = record.byte_to_string(record.getEndofRecord());

		while(check == true)
		{
			
		int offset = page_size * pageNo;

		byte[] data = new byte[page_size];
		System.out.println(data.length);
		
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(heapfile, "r");

			raf.seek((long) offset);
			raf.read(data, 0, page_size);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

		if(data == null)
		{
			check = false;
			break;
		}
		
		
		byte[] header = null;
		try {

			DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));

			header = new byte[data.length];

			for (int i = 0; i < header.length; i++) {
				header[i] = dis.readByte();

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		int i = 0;

		
		
		for (i = 0; i < header.length; i++) {
			
			
			String rec_det = record.byte_to_string(header[i]);
			//System.out.println(rec_det);
		
			if(rec_det.equals(endOfFile)) {
				end_rec = i;
				byte[] row = Arrays.copyOfRange(header, start_rec, end_rec);
				for (int j = 0; j < row.length; j++) {
					if (row[j] == record.getDelimiter()) {
						pos = j;
					}
				}
				start_rec = end_rec + 1;

				byte[] fields = Arrays.copyOfRange(header, pos + 1, end_rec);
				String STD_NAME = record.byte_array_to_string(fields);

				//System.out.println("DDDDD " + STD_NAME);

				if (STD_NAME.equals(input_search)) {
					System.out.println("Found " + input_search + " at record " + rec_no);
				}

				rec_no++;
			}

		}
		pageNo+=1;

		}
	}

}
