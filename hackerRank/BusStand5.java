package hackerRank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Result5 {

	public static List<Integer> kthPerson(int k, List<Integer> p, List<Integer> q) {
		class ppl{
			int index;
			Integer tLimit;

			public ppl(int index, Integer tLimit) {
				this.index=index;
				this.tLimit=tLimit;
			}
		}
		
		List<Integer>result=new LinkedList<Integer>();
		List<ppl>pplList=new LinkedList<ppl>();
		for(int i=0;i<p.size();i++) {
			ppl obj= new ppl(i,p.get(i));
			pplList.add(obj);
		}
		Map<Integer,Integer>resultsMap= new LinkedHashMap<Integer,Integer>();
		for(int i=0;i<q.size();i++) {
			int busTime=q.get(i);
			Integer res=resultsMap.get(busTime);

			if(res==null) {

				if(Collections.min(p)>=busTime) {
					//all the person in the queue have more patience, kth person will be last person in the queue
					result.add(i,k);
					resultsMap.put(busTime,k);
				}
				else if(Collections.max(p)<busTime) {
					//nobody has the patience till bus arrives
					resultsMap.put(busTime,0);
					result.add(i,0);
				}
				else {

					List<ppl> subP = pplList.stream().filter(num->num.tLimit>=busTime).collect(Collectors.toList());
					if(subP.size()<k)
					{
						resultsMap.put(busTime,0); 
						result.add(i,0); 
					}
					else
					{
						resultsMap.put(busTime,subP.get(k-1).index+1)	;
						result.add(i,subP.get(k-1).index+1);
					}

				}
			}//if query is not already asked
			else { // if the query is already answered
				result.add(i,resultsMap.get(q.get(i)));
			}//else 

		}//for loop for query
		return result;
	}
}

public class BusStand5 {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		//BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		BufferedWriter bufferedWriter= new  BufferedWriter(new PrintWriter(System.out));
		int k = Integer.parseInt(bufferedReader.readLine().trim());

		int pCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> p = new ArrayList<>();

		for (int i = 0; i < pCount; i++) {
			int pItem = Integer.parseInt(bufferedReader.readLine().trim());
			p.add(pItem);
		}

		int qCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> q = new ArrayList<>();

		for (int i = 0; i < qCount; i++) {
			int qItem = Integer.parseInt(bufferedReader.readLine().trim());
			q.add(qItem);
		}

		List<Integer> result = Result5.kthPerson(k, p, q);

		for (int i = 0; i < result.size(); i++) {
			bufferedWriter.write(String.valueOf(result.get(i)));

			if (i != result.size() - 1) {
				bufferedWriter.write("\n");
			}
		}

		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
