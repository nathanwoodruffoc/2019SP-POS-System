package HI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StringOperations {
//	public static LocalDate toLocalDateTest(String date) {
//		return LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/d/yyyy"));
//	}
	
	public static LocalDate toLocalDate(String date) {
		String[] parts = date.split("/");
		return LocalDate.of(Integer.parseInt(parts[2]) + 2000,Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
	}
	
	public static String rightpad(String text, int length) {
	    return String.format("%-" + length + "." + length + "s", text);
	}
	
	public static String formatData(List<String> data, List<Integer> columnWidths) {
		String result = new String();
		for (Integer i = 0; i < columnWidths.size(); i++) {
			result += rightpad(data.get(i), columnWidths.get(i));
		}
		return result;
	}
}
