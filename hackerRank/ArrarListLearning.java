package hackerRank;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrarListLearning {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int n=sc.nextInt();
		ArrayList<Integer[]>ints= new ArrayList<Integer[]>(); 
		for(int i=0;i<n;i++) {
			int size = sc.nextInt();
				int j=0;
				Integer[] intArray= new Integer[size];
				while (j<size) {
					intArray[j++]=sc.nextInt();
			}//while
				ints.add(intArray);
			
		}//for
		int q= sc.nextInt();
		ArrayList<Integer[]>queries= new ArrayList<Integer[]>();
		for (int i=0;i<q;i++) {
			Integer [] querArray= new Integer[2];
			querArray[0]=sc.nextInt();
			querArray[1]=sc.nextInt();
			queries.add(querArray);
		}//for
		
		for(int i=0;i<q;i++) {
			try {
				Integer [] query=(queries.get(i));
				Integer[]lineNoReqd=ints.get(query[0]-1);
				System.out.println(lineNoReqd[query[1]-1]);
				
			}catch (Exception e) {
				System.out.println("ERROR!");
			}
		}

	}

}
