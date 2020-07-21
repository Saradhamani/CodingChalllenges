package hackerRank;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;
class Result_4 {

	/*
	 * Complete the 'kthPerson' function below.
	 *
	 * The function is expected to return an INTEGER_ARRAY.
	 * The function accepts following parameters:
	 *  1. INTEGER k
	 *  2. INTEGER_ARRAY p
	 *  3. INTEGER_ARRAY q
	 */


	/*
	 * public static List<Integer> kthPerson(int k, List<Integer> p, List<Integer>
	 * q) {
	 * 
	 * List<Integer>result=new LinkedList<Integer>(); for(int i=0;i<q.size();i++) {
	 * Queue<Integer> ppl=new LinkedList<Integer>(p); Queue<Integer> inBus=new
	 * LinkedList<Integer>(); int last_person=0,count=0; Iterator<Integer>
	 * itr=ppl.iterator(); while(itr.hasNext()) { if(ppl.peek()<q.get(i)) {
	 * ppl.remove(); count++;//person moves out of the queue }
	 * 
	 * else if(ppl.peek()>=q.get(i)) { inBus.add(ppl.remove()); last_person=++count;
	 * 
	 * if(inBus.size()==k) break; } } if(inBus.size()<k) { result.add(i,0); } else
	 * result.add(i,last_person); }
	 * 
	 * return result;
	 * 
	 * }
	 */

public static List<Integer> kthPerson(int k, List<Integer> p, List<Integer> q) {
		
		List<Integer>result=new LinkedList<Integer>();
		for(int i=0;i<q.size();i++) {
			Queue<Integer> ppl=new LinkedList<Integer>(p);
			Queue<Integer> inBus=new LinkedList<Integer>();
			int last_person=0,count=0;
			
			while(!ppl.isEmpty()) {
				if(ppl.peek()<q.get(i)) {
					ppl.remove(); count++;//person moves out of the queue
					}
				
					else if(ppl.peek()>=q.get(i)) {
						inBus.add(ppl.remove());
						last_person=++count; 
						
						if(inBus.size()==k) break;
					}
				}
			if(inBus.size()<k) {
				result.add(i,0);
			}
			else
			result.add(i,last_person);
			}
		
		return result;

	}

}
public class BusStand4 {


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

		List<Integer> result = Result_4.kthPerson(k, p, q);

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