package hackerRank;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;



class Result_2 {

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
		long startime=System.nanoTime();
		List<Integer>kthPersontoBoard= new ArrayList<Integer>();

		for (Integer querry : q) {
			int last_person=0;int num_boarded=0;
			Stream<Integer> filterStream = p.stream().filter(num->num>=querry);
			Optional<Integer> collect = filterStream.findFirst();
			if(!collect.isPresent()) {
				kthPersontoBoard.add(0);

			}
			else {
				//System.out.println("first element greater than "+querry+"is "+collect.get());
				int i=p.indexOf(collect.get());
				for(;i<p.size()&&num_boarded<k;i++) {
					if(p.get(i)>=querry) {
						last_person=i+1;
						num_boarded++;
					}
				}//for
			
			if(num_boarded<k)
				kthPersontoBoard.add(0);
			else
				kthPersontoBoard.add(last_person);
			}
			filterStream.close();
		}//for each
		long endTime=System.nanoTime();
		System.out.println("Execution time in nano seconds "+ (endTime-startime));
		System.out.println("Execution time in milli seconds "+ (endTime-startime)/1000000);
		return kthPersontoBoard;
	}//method
}//result_2
public class BusStand2 {

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

		List<Integer> result = Result_2.kthPerson(k, p, q);

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

