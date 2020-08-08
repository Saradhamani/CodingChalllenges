package hackerRank;

	import java.io.*;
	import java.math.*;
	import java.text.*;
	import java.util.*;
	import java.util.regex.*;

	public class TruckTour {

	    /*
	     * Complete the truckTour function below.
	     */
	    static int truckTour(int[][] petrolpumps) {
	    	int max_petrol=0; int index=-1; 
	    	int min_distance=Integer.MAX_VALUE;
	    	for(int i=0;i<petrolpumps.length;i++) {
	    		if(petrolpumps[i][0] > max_petrol && petrolpumps[i][1]<min_distance) {
	    			max_petrol=petrolpumps[i][0];
	    			min_distance=petrolpumps[i][1];
	    			index=i;
	    		}
	    	}
	    	
	    	
			return index;
	        

	    }

	    private static final Scanner scanner = new Scanner(System.in);

	    public static void main(String[] args) throws IOException {
	        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
	        BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out));

	        int n = Integer.parseInt(scanner.nextLine().trim());

	        int[][] petrolpumps = new int[n][2];

	        for (int petrolpumpsRowItr = 0; petrolpumpsRowItr < n; petrolpumpsRowItr++) {
	            String[] petrolpumpsRowItems = scanner.nextLine().split(" ");

	            for (int petrolpumpsColumnItr = 0; petrolpumpsColumnItr < 2; petrolpumpsColumnItr++) {
	                int petrolpumpsItem = Integer.parseInt(petrolpumpsRowItems[petrolpumpsColumnItr].trim());
	                petrolpumps[petrolpumpsRowItr][petrolpumpsColumnItr] = petrolpumpsItem;
	            }
	        }

	        int result = truckTour(petrolpumps);

	        bufferedWriter.write(String.valueOf(result));
	        bufferedWriter.newLine();

	        bufferedWriter.close();
	    }
	}


