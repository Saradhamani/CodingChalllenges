package hackerRank;

import java.util.Scanner;

public class SubStringLearnings {

	public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
	        String S = in.next();
	        int start = in.nextInt();
	        int end = in.nextInt();
	        System.out.println(S.substring(start, end));

	}

}
