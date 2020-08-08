package hackerRank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


class Result_normal{
	public static List<Integer> kthPerson(int k, List<Integer> p, List<Integer> q) {

		List<Integer>kth=new LinkedList<Integer>();
		for(int i=0;i<q.size();i++) {
			long startime=System.nanoTime();
			kth.add(i, findK(k, p, q.get(i)));
			long endTime=System.nanoTime();
			System.out.println("Execution time (ms) "+ (endTime-startime)/1000000);
		}

		return kth;
		// Write your code here

	}
	public static int findK(int k,List<Integer>p,int arr_time) {
		int index=0;
		int num_persons=0;
		if(Collections.min(p).compareTo(arr_time)>=0) {
			//since all the person in the queue has patience greater than arrival time all of them can get it upto k persons.
			if(p.size()<k) {
				index=0;//since there are more seats left;
			}
			else index=k; // it can hold K persons

		}

		else if(Collections.max(p).compareTo(arr_time)<0) {
			//since no person in the queue has patience to wait till bus arrival
			index=0;
		}
		else {
		
			int start=0;
			//if(findFirst.isPresent()) start=p.indexOf(findFirst.get());
			for(int i=start;i<p.size()&&num_persons<k;i++) {
				if(p.get(i)>=arr_time) {                    
					num_persons++;
					index=i+1;
					List<Integer>subList=p.subList(i+1, p.size());
					int max_sublist=Collections.max(subList);
					if(max_sublist<arr_time) {
						break;//no more persons in the queue
					}
					if(Collections.min(subList).compareTo(arr_time)>0) {

						//since all the person in the sub queue has patience greater than arrival time all of them can get it upto k persons.
						num_persons=k;
						index=i+k+1;
						break;
					}
				}
			}//for
			if(num_persons<k)
				index=0;//if bus is not full return 0
		}


		return index;

	}
}

public class BusStandNormal {
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

		List<Integer> result = Result_normal.kthPerson(k, p, q);

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
