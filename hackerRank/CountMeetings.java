package hackerRank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.sql.Struct;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result_meetings {

    /*
     * Complete the 'countMeetings' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY firstDay
     *  2. INTEGER_ARRAY lastDay
     */

    public static int countMeetings(List<Integer> firstDay, List<Integer> lastDay) {
    	System.out.println(firstDay.size());
        System.out.println("*****************************");
        System.out.println(lastDay.size());
    class investors {
        public investors(int first_day, int last_day) {
            super();
            this.first_day = first_day;
            this.last_day = last_day;
        }
        int first_day;
        int last_day;
    };
    class lastDayCompare implements Comparator<investors>{

        @Override
        public int compare(investors o1, investors o2) {
            // TODO Auto-generated method stub
            return o1.last_day-o2.last_day;
            
        }
        
    }
    Set<Integer>schedule=new LinkedHashSet<Integer>();
    List<investors>unscheduled_meetings= new LinkedList<investors>();
    
    for(int i=0;i<firstDay.size();i++) {
        if(firstDay.get(i)==lastDay.get(i)) 
        {//persons who can only be met on a single day
            if(!schedule.contains(firstDay.get(i))) 
                schedule.add(firstDay.get(i));
        }
        else {
            unscheduled_meetings.add(new investors(firstDay.get(i), lastDay.get(i)));
        }
    }
    unscheduled_meetings.sort(new lastDayCompare());
    
    Iterator<investors>itr=unscheduled_meetings.iterator();
    while (itr.hasNext()) {
        investors inv = itr.next();
        if(!schedule.contains(inv.first_day)) 
            schedule.add(inv.first_day);
        else {
            int j=inv.first_day+1;
            while(j<=inv.last_day) {
                if(!schedule.contains(j) )
                    {schedule.add(j);break;}
                j++;
            }
        }
        
    }
    
    
    return schedule.size();

    }



}

public class CountMeetings {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out));

        int firstDayCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> firstDay = IntStream.range(0, firstDayCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int lastDayCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> lastDay = IntStream.range(0, lastDayCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result_meetings.countMeetings(firstDay, lastDay);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
