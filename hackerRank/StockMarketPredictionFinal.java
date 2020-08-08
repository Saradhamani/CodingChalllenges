package hackerRank;


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class Result_final {

    /*
     * Complete the 'predictAnswer' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY stockData
     *  2. INTEGER_ARRAY queries
     */

    public static List<Integer> predictAnswer(List<Integer> stockData, List<Integer> queries) {
  
        List<Integer>result= new LinkedList<Integer>();
        Map <Integer,Integer> resultsMap=new LinkedHashMap<Integer,Integer>();
        int bef=0,aft=0,day=0;//short_bef=-1,short_aft=-1;
        System.out.println(stockData.size() +" no . of stock data");
        System.out.println(queries.size() +" no . of queries");
        List<Integer>uniqueStockData=new LinkedList<Integer>(new HashSet<Integer>(stockData));
        System.out.println("uniqueStockData.size() "+ uniqueStockData.size());
        List<Integer>uniqueQueries=new LinkedList<Integer>(new HashSet<Integer>(queries));
        System.out.println("uniqueQueries.size() "+ uniqueQueries.size());
        int smallerData=-1,smallerDataIndex=-1,largerData=-1,largerDataIndex=-1;
          if(uniqueStockData.size()==2){
                //only two distinct elements are present
			/*
			 * System.out.println("uniqueStockData 0 "+ uniqueStockData.get(0));
			 * System.out.println("index of uniqueStockData.get(0):"+stockData.indexOf(
			 * uniqueStockData.get(0))); System.out.println("uniqueStockData 1 "+
			 * uniqueStockData.get(1));
			 * System.out.println("index of uniqueStockData.get(1):"+stockData.indexOf(
			 * uniqueStockData.get(1)));
			 */
        	  smallerData=uniqueStockData.get(0)<uniqueStockData.get(1)?uniqueStockData.get(0):uniqueStockData.get(1);
              smallerDataIndex=stockData.indexOf(smallerData);
              largerData=uniqueStockData.get(0)>uniqueStockData.get(1)?uniqueStockData.get(0):uniqueStockData.get(1);
             largerDataIndex=stockData.indexOf(largerData);
             }
          
          
        for(int i=0;i<queries.size();i++) {
            if(uniqueStockData.size()==1){
                if(resultsMap.get(queries.get(i))!=null){//this query is already processed
                 result.add(i,resultsMap.get(queries.get(i)));
                continue;
                }
                else{
                     result.add(i,-1);
                resultsMap.put(queries.get(i), -1); continue;
                }
            }
          if(uniqueStockData.size()==2){
             if(smallerDataIndex==0){
                 if(queries.get(i)-1>=0 && queries.get(i)-1<largerDataIndex){
                        if(resultsMap.get(queries.get(i))!=null){//this query is already processed
                        result.add(i,resultsMap.get(queries.get(i)));
                        continue;
                        }
                    else{
                     result.add(i,-1);
                    resultsMap.put(queries.get(i), -1); continue;
                    }
                     
                }
                 if(queries.get(i)-1>=largerDataIndex){
                     if(resultsMap.get(queries.get(i))!=null){//this query is already processed
                     result.add(i,resultsMap.get(queries.get(i)));
                    continue;
                    }
                    else{
                     result.add(i,largerDataIndex);
                     System.out.println(queries.get(i)+":"+largerDataIndex);
                    resultsMap.put(queries.get(i), largerDataIndex); continue;
                    }
                      
                 }
            }
            
             
        }
            day=queries.get(i)-1;//query indicates the selected day, decrementing it by 1 will give the index
            System.out.println("query "+i+":" +queries.get(i));
           if(resultsMap.get(queries.get(i))!=null){//this query is already processed
                 result.add(i,resultsMap.get(queries.get(i)));
                continue;
            }
            if(day>stockData.size())
              {  result.add(i, -1);//index out of bounds
              resultsMap.put(queries.get(i), -1);
                 continue;
              }
           
            
            if(Collections.min(stockData).compareTo(stockData.get(day))==0) {
                //if the queried day is the mimimum value in the entire list
                
                if(resultsMap.get(queries.get(i))!=null){//this query is already processed
                 result.add(i,resultsMap.get(queries.get(i)));
                continue;
                }
                else{
                    result.add(i, -1);
                resultsMap.put(queries.get(i), -1);
                continue;
                }
            }
            bef=day-1;
            aft=day+1;
             
            while(bef>=0||aft<stockData.size()) {
                if(bef>=0&&stockData.get(bef)<stockData.get(day))
                {
                   //bef indicates the index and incrementing it by 1 will give the req day
                     resultsMap.put(queries.get(i), bef+1);
                  
                     break;
                    
                }
                else if(aft<stockData.size()&&stockData.get(aft)<stockData.get(day)) {
                   //aft indicates the index and incrementing it by 1 will give the req day
                     resultsMap.put(queries.get(i), aft+1);
                       break;
                    
                }
                
                
                bef=bef-1;//move the counter to left by 1
                aft=aft+1;//move the counter to right by 1
            }//while
             result.add(i, resultsMap.get(queries.get(i)));
            
               
            
           
        }
        
       // System.out.println(result);
        return result;}

}
public class StockMarketPredictionFinal {
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

	        List<Integer> result = Result_final.predictAnswer(stockData, queries);

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

