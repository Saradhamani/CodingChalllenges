package hackerRank;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

class Result_final_thread{



	public static List<Integer> predictAnswer(List<Integer> stockData, List<Integer> queries) {
		Map <Integer,Integer> resultsMap=new LinkedHashMap<Integer,Integer>();
		List<Integer>results=new LinkedList<Integer>();
		Map<Integer,Integer>index_result=new HashMap<Integer,Integer>();
		List<Integer>uniqueStock=new LinkedList<Integer>(new HashSet<Integer>(stockData));
		/*class index_result{
			public index_result(int index, int result) {
				super();
				this.index = index;
				this.result = result;
			}
			int index;
			int result;
			
		}
		class mycomp implements Comparator<index_result>{

			@Override
			public int compare(index_result o1, index_result o2) {
				// TODO Auto-generated method stub
				return o1.index-o2.index;
			}
			
		}
		List<index_result>temp=new LinkedList<index_result>();*/
		
		class qproc implements Callable<Void>{
			public qproc(int indexToProc, int query) {
				super();
				this.indexToProc = indexToProc;
				this.query = query;
			}
			int indexToProc;
			int query;
			@Override
			public Void call() throws Exception {
				if(resultsMap.containsKey(query)) {
					index_result.put(indexToProc, resultsMap.get(query));
					return null;
				}
				else {
					if(uniqueStock.size()==1) {
						resultsMap.put(query, -1); 
						index_result.put(indexToProc,0);
						return null;
					}//num of unique stock data ==1
					else if(uniqueStock.size()==2) {
						int smallerData=-1,smallerDataIndex=-1,largerData=-1,largerDataIndex=-1;
						smallerData=uniqueStock.get(0)<uniqueStock.get(1)?uniqueStock.get(0):uniqueStock.get(1);
						smallerDataIndex=stockData.indexOf(smallerData);
						largerData=uniqueStock.get(0)>uniqueStock.get(1)?uniqueStock.get(0):uniqueStock.get(1);
						largerDataIndex=stockData.indexOf(largerData);
						if(smallerDataIndex==0) {
							if(query-1>=0 && query-1<largerDataIndex){
								resultsMap.put(query, -1);
								index_result.put(indexToProc,-1);
								return null;
							}
							if(query-1>=largerDataIndex){
								resultsMap.put(query, largerDataIndex); 
							index_result.put(indexToProc,largerDataIndex);
								return null;
							}

						}
					}// if num of unique stock ==2
					
						int day=query-1;
						if(day>stockData.size())
						{  
							resultsMap.put(query, -1);
							index_result.put(indexToProc,-1);
							return null;
						}
						else {
							if(Collections.min(stockData).compareTo(stockData.get(day))==0) {
								//if the queried day is the mimimum value in the entire list
								resultsMap.put(query, -1);
								index_result.put(indexToProc,-1);
								return null;

							}//if min
							int bef=day-1;
							int aft=day+1;

							while(bef>=0||aft<stockData.size()) {
								if(bef>=0&&stockData.get(bef)<stockData.get(day))
								{
									//bef indicates the index and incrementing it by 1 will give the req day
									resultsMap.put(query, bef+1);
									index_result.put(indexToProc,bef+1);
									
									return null;
									

								}
								else if(aft<stockData.size()&&stockData.get(aft)<stockData.get(day)) {
									//aft indicates the index and incrementing it by 1 will give the req day
									resultsMap.put(query, aft+1);
									index_result.put(indexToProc,aft+1);
									return null;

								}


								bef=bef-1;//move the counter to left by 1
								aft=aft+1;//move the counter to right by 1
							}//while

						}//day<=stock size
					
				}//not already processes
				return null;
			
				
				
			}//call ()
		}//class
		//Get ExecutorService from Executors utility class, thread pool size is 10
		int cores=Runtime.getRuntime().availableProcessors();
		System.out.println("number of processors"+cores);
        ExecutorService executor = Executors.newFixedThreadPool(cores*4);
        //create a list to hold the Future object associated with Callable
        List<Future<Void>> list = new ArrayList<Future<Void>>();
		for(int i=0;i<queries.size();i++) {
			Callable<Void>process_query= new qproc(i, queries.get(i));
			 Future<Void> future = executor.submit(process_query);
			 list.add(future);
		}//queries are submitted for processing
		
		for(Future<Void> fut : list){
            //fut.isDone(); 
            
            try {
				fut.get();
			} catch (InterruptedException | ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
              
            
        }
		//shut down the executor service now
        executor.shutdown();
		
       List<Entry<Integer,Integer>>temp=new LinkedList<Entry<Integer,Integer>>(index_result.entrySet());
       Collections.sort(temp, new Comparator<Entry<Integer,Integer>>() {

		
		@Override
		public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
			// TODO Auto-generated method stub
			return o1.getKey()-o2.getKey();
		}
	});
       for (Entry<Integer, Integer> entry : temp) {
    	   System.out.println(entry.getKey());
    	   results.add(entry.getKey(), entry.getValue());
		
	}
        return results;
	}
}
public class stockThrreads {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		//BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
		BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
		int stockDataCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> stockData = IntStream.range(0, stockDataCount).mapToObj(i -> {
			try {
				return bufferedReader.readLine().replaceAll("\\s+$", "");
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		})
				.map(String::trim)
				.map(Integer::parseInt)
				.collect(toList());

		int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
			try {
				return bufferedReader.readLine().replaceAll("\\s+$", "");
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		})
				.map(String::trim)
				.map(Integer::parseInt)
				.collect(toList());

		List<Integer> result = Result_final_thread.predictAnswer(stockData, queries);

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

