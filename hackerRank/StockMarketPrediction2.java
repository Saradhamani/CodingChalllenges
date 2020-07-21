package hackerRank;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
class Result_stock2 {

	/*
	 * Complete the 'predictAnswer' function below.
	 *
	 * The function is expected to return an INTEGER_ARRAY.
	 * The function accepts following parameters:
	 *  1. INTEGER_ARRAY stockData
	 *  2. INTEGER_ARRAY queries
	 */

	public static List<Integer> predictAnswer(List<Integer> stockData, List<Integer> queries) {
		long startTime=System.nanoTime();
		List<Integer>result= new LinkedList<Integer>();
		int bef=0,aft=0,day=0,short_bef=-1,short_aft=-1;
		for(int i=0;i<queries.size();i++) {
			day=queries.get(i)-1;//query indicates the selected day, decrementing it by 1 will give the index
			bef=day-1;
			aft=day+1;
			short_bef=-1;
			short_aft=-1;
			boolean flag=false;
			while(!flag && (bef>=0||aft<stockData.size())) {
				if(bef>=0&&stockData.get(bef)<stockData.get(day))
				{
					short_bef=bef+1;//bef indicates the index and incrementing it by 1 will give the req day
				}
				if(aft<stockData.size()&&stockData.get(aft)<stockData.get(day)) {
					short_aft=aft+1;//aft indicates the index and incrementing it by 1 will give the req day
				}
				if(short_bef>0) {
					result.add(i, short_bef);
					flag=true;//flag =true indicates sol is found
				}
				else if(short_aft>0) {
					result.add(i, short_aft);flag=true;
				}
				
				bef=bef-1;//move the counter to left by 1
				aft=aft+1;//move the counter to right by 1
			}//while
			if(!flag) {
				result.add(i, -1);
			}
			
		}
		long endTime=System.nanoTime();
		System.out.println("Execution time in nano seconds "+ (endTime-startTime));
		System.out.println("Execution time in milli seconds "+ (endTime-startTime)/1000000);
		System.out.println(result);
		return result;}

}
public class StockMarketPrediction2 {
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

		List<Integer> result = Result_stock2.predictAnswer(stockData, queries);

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
