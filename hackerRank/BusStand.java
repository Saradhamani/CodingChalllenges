package hackerRank;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



class Result_1 {

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
		class people{
			public people(int time, int queuenum) {
				super();
				this.time = time;
				this.queuenum = queuenum;
			}
			int time;
			int queuenum;
		}
		List<people> pplList= new LinkedList<people>();
		List<Integer>kthPersontoBoard= new ArrayList<Integer>();
		for(int i=0;i<p.size();i++) {
			pplList.add(new people(p.get(i),i+1));
		}
		
		q.parallelStream().sequential().forEachOrdered((querry)->{int num_boarded=0;
		int last_person=0;
		List<people>filtered=pplList.stream().filter(ppl->ppl.time>=querry).collect(Collectors.toList());
		
		if(filtered.size()<k)
			kthPersontoBoard.add(0);
		else {
			int i=0;
		while(num_boarded<k) {
			last_person=filtered.get(i++).queuenum;
			num_boarded++;
		}

		
			kthPersontoBoard.add(last_person);
		}
		});

		long endTime=System.nanoTime();
		System.out.println("Execution time in nano seconds "+ (endTime-startime));
		System.out.println("Execution time in milli seconds "+ (endTime-startime)/1000000);
		return kthPersontoBoard;
	}

}

public class BusStand {
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

		List<Integer> result = Result_1.kthPerson(k, p, q);

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
