package hackerRank;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
class Result_FinalTry{

	public static List<Integer> kthPerson(int k, List<Integer> p, List<Integer> q) {
		
		//step 1: convert the list p to ppl class
		class ppl {
			int index;
			int pLimit;
			public ppl(int index,int pLimit) {
				this.index=index;
				this.pLimit=pLimit;
			}//constructor
		}//class ends
		//declare a list to get all elements of p as list of ppl
		long start=System.nanoTime();
		List<ppl>pList=new LinkedList<ppl>();
		for(int i=0;i<p.size();i++) {
			pList.add(new ppl(i, p.get(i)));
		}
		//step 2 : similarly convert the q to query class
		class query {
			int index;
			int busTime;
			public query(int index,int busTime) {
				this.index=index;
				this.busTime=busTime;
			}
		}
		//declare a list to get all the elements of q as query
		List<query>qList=new LinkedList<query>();
	
		for(int i=0;i<q.size();i++) {
			qList.add(new query(i, q.get(i)));
		}
		long end =System.nanoTime();
		System.out.println("Time taken:(s)"+(end-start)/1000000000);
		class qComp implements Comparator<query>{

			@Override
			public int compare(query o1, query o2) {
				// TODO Auto-generated method stub
				return o1.busTime-o2.busTime;
			}
			
		}
		qList.sort(new qComp());//sorting the query list
		//declare a varriable to collect the results;
		List<Integer>res=new LinkedList<Integer>();
		Map<Integer,Integer>bTime_res=new HashMap<Integer, Integer>();//maps bustime to res
		Map<Integer,Integer>index_res=new HashMap<Integer,Integer>();//maps index to res
		
		for(int i=0;i<qList.size();i++) {
			//check if btime is already processed
			int bTime=qList.get(i).busTime;
			if(bTime_res.containsKey(bTime)) {
				//simply add the known result to map
				index_res.put(qList.get(i).index,bTime_res.get(bTime));
			}
			else {
				int min=Collections.min(p);
				int max=Collections.max(p);
				if(min>bTime) {
					if(pList.size()>k)
	                   { index_res.put(qList.get(i).index,pList.get(k-1).index+1);//map the index with the value
	                    bTime_res.put(bTime, pList.get(k-1).index+1);//map the bustime with the value
	                    continue;}
	                    else {
	                        index_res.put(qList.get(i).index,0);//map the index with the value
	                    bTime_res.put(bTime, 0);//map the bustime with the value
	                    continue;
	                    }
				}
				else if(max<bTime) {
					index_res.put(qList.get(i).index,0);//map the index with the value
					bTime_res.put(bTime, 0);//map the bustime with the value
					continue;
				}
				Predicate<ppl>condition=per->per.pLimit<bTime;
				pList.removeIf(condition);//removing all ppl whose pLimit is less than the bus arrival time
				if(pList.size()<k) {
					//number of ppl to board is less than the capacity
					index_res.put(qList.get(i).index,0);//map the index with the value
					bTime_res.put(bTime, 0);//map the bustime with the value
				}
				else {
					index_res.put(qList.get(i).index,pList.get(k-1).index+1);//map the index with the value
					bTime_res.put(bTime, pList.get(k-1).index+1);//map the bustime with the value
					
				}//nested else
			}//if query not already processed
			
		}//for
		//sort the result map according the keys( here index) and collect it as a map
		LinkedHashMap<Integer, Integer> sorted = index_res.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect( Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
		res.addAll(sorted.values());
		return res;
		
	}
	
}

public class BusStand_FinalTry {

	
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

			List<Integer> result = Result_FinalTry.kthPerson(k, p, q);

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


