package hackerRank;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
class Result_stock {

	/*
	 * Complete the 'predictAnswer' function below.
	 *
	 * The function is expected to return an INTEGER_ARRAY.
	 * The function accepts following parameters:
	 *  1. INTEGER_ARRAY stockData
	 *  2. INTEGER_ARRAY queries
	 */

	public static List<Integer> predictAnswer(List<Integer> stockData, List<Integer> queries) {
		
		long starttime=System.nanoTime();
		List<Integer>result= new LinkedList<Integer>();
		for(int i=0; i < queries.size();i++) {
			if(queries.get(i)>stockData.size())
				result.add(i, -1);//index out of bounds
			if(Collections.min(stockData)==stockData.get(i)) {
				result.add(i, -1);
				break;
			}
			else {
				int mid=queries.get(i)-1;//reducing 1 to get the index ryt
				System.out.println("day selected"+queries.get(i)+"mid:"+mid);
				if(mid>0&&stockData.get(mid-1)<stockData.get(mid)) {
					System.out.println("short day is :"+ (mid));//not subtracting 1 to get the actual day
					result.add(i, (mid));

				}
				else if(mid+1<=stockData.size()&&stockData.get(mid+1)<stockData.get(mid)) {
					System.out.println("short day is :"+ (mid+1));
					result.add(i, (mid+2));

				}
				else {
					int short_day_before=-1,short_day_after=-1;
					for(int j=mid-2;j>=0;j--) {
						if(stockData.get(j)<stockData.get(mid)) {
							short_day_before=j+1;break;//adding 1 to j to indicate the actual day
						}
					}


					if(mid+1<stockData.size())
						for(int k=mid+1;k<stockData.size();k++) {
							if(stockData.get(k)<stockData.get(mid)) {
								short_day_after=k+1;break;//adding 1 to k to indicate the actual day
							}

						}
					System.out.println(queries.get(i)+": "+short_day_before+"->"+stockData.get(mid)+"->"+short_day_after);
					if(short_day_before<0)
						result.add(i, short_day_after);
					else if(short_day_after<0) 
						result.add(i, short_day_before);
					else 
						result.add(i, ((mid+1)-short_day_before<=short_day_after-(mid+1))?short_day_before:short_day_after);
				}

			}//else
		}


		long endTime=System.nanoTime();
		System.out.println("Execution time in nano seconds "+ (endTime-starttime));
		System.out.println("Execution time in milli seconds "+ (endTime-starttime)/1000000);
		return result;}

}
public class StockMarketPrediction {
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

		List<Integer> result = Result_stock.predictAnswer(stockData, queries);

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
