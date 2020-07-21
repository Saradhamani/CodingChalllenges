package hackerRank;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;

class Result {

    /*
     * Complete the 'findDay' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER month
     *  2. INTEGER day
     *  3. INTEGER year
     */

    public static String findDay(int month, int day, int year) {
    	
    	LocalDate date = LocalDate.of(year, month, day);
       	DayOfWeek dayOrdinal=date.getDayOfWeek();
    	
    	System.out.println(date.getDayOfWeek());
    	switch(dayOrdinal) {
    	case SUNDAY: return "Sunday".toUpperCase();
    	case MONDAY: return "Monday".toUpperCase();
    	case TUESDAY: return "Tuesday".toUpperCase();
    	case WEDNESDAY: return "Wednesday".toUpperCase();
    	case THURSDAY: return "Thursday".toUpperCase();
    	case FRIDAY: return "Friday".toUpperCase();
    	case SATURDAY: return "Saturday".toUpperCase();
    	default: return "";
    	}
    	
    	
    	
    	
		

    }

}

public class CalendarFindDay {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
       BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int month = Integer.parseInt(firstMultipleInput[0]);

        int day = Integer.parseInt(firstMultipleInput[1]);

        int year = Integer.parseInt(firstMultipleInput[2]);

        String res = Result.findDay(month, day, year);

        bufferedWriter.write(res);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

