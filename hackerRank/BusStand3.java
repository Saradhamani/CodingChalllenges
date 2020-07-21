package hackerRank;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.IntStream;



class Result_3 {

	/*
	 * Complete the 'kthPerson' function below.
	 *
	 * The function is expected to return an INTEGER_ARRAY.
	 * The function accepts following parameters:
	 *  1. INTEGER k
	 *  2. INTEGER_ARRAY p
	 *  3. INTEGER_ARRAY q
	 */


	public static List<Integer> kthPerson(int k, List<Integer> p, List<Integer> q) {
		// Write your code here
			//Timestamp starttime=new Timestamp(new java.util.Date().getTime() );
			long startime=System.nanoTime();
				List<Integer>kthPersontoBoard= new ArrayList<Integer>();
				Stack<Integer>p_stack=new Stack<Integer>();
				p_stack.addAll(p);
				
				for (Integer querry : q) {
					int num_boarded=0;
					List<Integer> stillPatient = p.stream().filter(num->num>=querry).collect(toList());
					if(stillPatient.isEmpty()) {
						kthPersontoBoard.add(0);

					}
					else {
						//System.out.println("first element greater than "+querry+"is "+collect.get());
						
						int key=0;
						Map<Integer,Integer>boarded_Pass=new LinkedHashMap<Integer,Integer>();
						for(int i=0;i<stillPatient.size()&&num_boarded<k;i++) {
							key=p.indexOf(stillPatient.get(i));
							if(boarded_Pass.containsKey(key)) {							
							key=p_stack.indexOf(stillPatient.get(i), key);
						
							}
							
							boarded_Pass.put(key,stillPatient.get(i));
							num_boarded++;
												/*
					 * if(p.get(i)>=querry) { last_person=i+1; num_boarded++; }
					 */
						}//for
					
					if(num_boarded<k)
						kthPersontoBoard.add(0);
					else
						kthPersontoBoard.add(key+1);//key denotes the indices , adding 1 to it will denote the person's position in the queue
					}
					System.out.println(kthPersontoBoard);
					
				}//for each
		/*
		 * Timestamp endTime=new Timestamp(new java.util.Date().getTime()); int
		 * seconds=(int)(endTime.getTime()-starttime.getTime())/1000;
		 * System.out.println("Time taken:"+seconds);
		 */
				long endTime=System.nanoTime();
				System.out.println("Execution time in nano seconds "+ (endTime-startime));
				System.out.println("Execution time in milli seconds "+ (endTime-startime)/1000000);
				return kthPersontoBoard;
	}

}

public class BusStand3 {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter= new BufferedWriter(new PrintWriter(System.out));
		//BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));


		int k = Integer.parseInt(bufferedReader.readLine().trim());

		int pCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> p = IntStream.range(0, pCount).mapToObj(i -> {
			try {
				return bufferedReader.readLine().replaceAll("\\s+$", "");
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		})
				.map(String::trim)
				.map(Integer::parseInt)
				.collect(toList());

		int qCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> q = IntStream.range(0, qCount).mapToObj(i -> {
			try {
				return bufferedReader.readLine().replaceAll("\\s+$", "");
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		})
				.map(String::trim)
				.map(Integer::parseInt)
				.collect(toList());

		List<Integer> result = Result_3.kthPerson(k, p, q);

		bufferedWriter.write(
				result.stream()
				.map(Object::toString)
				.collect(joining("\n"))
				+ "\n"
				);

		bufferedReader.close();
		bufferedWriter.close();
	}


}
