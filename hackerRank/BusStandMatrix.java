package hackerRank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
class ResultMatrix {

	public static List<Integer> kthPerson(int k, List<Integer> p, List<Integer> q) {
        //List<Integer> result= new LinkedList<Integer>();
		
        ArrayList<Integer>result_arr =new ArrayList<Integer>(Collections.nCopies(q.size(), -1));
     
        //int[] result_arr=new int[q.size()];
 Map<Integer,Integer>resultsMap= new LinkedHashMap<Integer,Integer>();
 //Map<Integer,Integer>queryIndex_result=new LinkedHashMap<>();

 class ppl{ int index; Integer tLimit;

 public ppl(int index, Integer tLimit) { 
     this.index=index; 
     this.tLimit=tLimit;
 } 
 } 
 List<ppl>pplList=new LinkedList<ppl>(); 
 
 for(int i=0;i<p.size();i++) { 
     ppl obj= new ppl(i,p.get(i)); 
     pplList.add(obj); }

 class processQuery implements Runnable{

     public processQuery(int startIndex, int endIndex, List<ppl> pplList, List<Integer> q, int k) {
         super();
         this.startIndex = startIndex;
         this.endIndex = endIndex;
         this.pplList = pplList;
         this.q = q;
         this.k = k;
         this.pplListBackup=pplList;
     }
     int startIndex;
     int endIndex;
     List<ppl>pplList;
     List<ppl>pplListBackup;
     List<Integer>q;
     int k;
     private Thread t;
     public void start () {
          
           if (t == null) {
              t = new Thread (this);
              t.start ();
           }
        }
     @Override
     public void run() {
    	 
         for(int i=startIndex;i<endIndex;i++) {
        	 this.pplList=this.pplListBackup;
             int busTime=q.get(i);
             Integer res=resultsMap.get(busTime);
             if(res==null) {

                 if(Collections.min(p).compareTo(busTime)>0) {
                     //all the person in the queue have more patience, kth person will be last person in the queue
                     //result.add(i,k);
                     //queryIndex_result.put(i,k);
                	 result_arr.set(i,k);
                	
                     resultsMap.put(busTime,k);
                 }
                 else if(Collections.max(p).compareTo(busTime)<=0) {
                     //nobody has the patience till bus arrives
                     resultsMap.put(busTime,0);
                     //result.add(i,0);
                     //queryIndex_result.put(i,0);
                     result_arr.set(i,0);
                 }
                 else {

        // Optional <ppl> firstMatch=pplList.stream().filter(num->num.tLimit>=busTime).findFirst();
                	 Predicate<ppl>condition= person->person.tLimit<busTime;
                	 pplList.removeIf(condition);
             //System.out.println(firstMatch.get().index+": is the index of firstMatch");
                     int num_persons=0; int last_index=-1;
                     for(int j=0;j<pplList.size();j++) {
                         if(pplList.get(j).tLimit>=busTime) {
                             if(num_persons<k) {
                                 num_persons++;
                                 last_index=pplList.get(j).index+1;
                                 if(num_persons==k) break;
                             }

                         }
                     }
                     if(num_persons<k)
                     {
                         //result.add(i, 0);
                        // queryIndex_result.put(i,0);
                    	 result_arr.set(i,0);
                         resultsMap.put(busTime,0);
                     }
                     else
                     {
                         //result.add(i, last_index);
                        // queryIndex_result.put(i,last_index);
                    	 result_arr.set(i,last_index);
                         resultsMap.put(busTime,last_index);

                     }




                 }
             }//if query is not already asked
             
              else { // if the query is already answered
            	  result_arr.set(i,resultsMap.get(busTime));
               //queryIndex_result.put(i,resultsMap.get(busTime));
               //result.add(i,resultsMap.get(busTime));
                }
              ///else 

         }//for loop
     }//run method

 }

 int chunkSize = (q.size() + 32 - 1) / 32; // divide by threads rounded up.
 
 for (int thread = 0; thread < 32; thread++) {
     int start = thread * chunkSize;
     int end = Math.min(start + chunkSize, q.size());
     processQuery query=new processQuery(start,end, pplList, q, k);
     query.start();
     try{
     query.t.join();
     }catch(InterruptedException e){
         System.out.println("Interrupted Exception occured");
     }

 }
// Map<Integer, Integer> sorted = queryIndex_result.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));


 
 return result_arr;
} //function  
}//class
public class BusStandMatrix {

	public static void main(String[] args) throws IOException, InterruptedException{
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

		List<Integer> result = ResultMatrix.kthPerson(k, p, q);

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


