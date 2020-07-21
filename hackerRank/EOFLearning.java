package hackerRank;

import java.util.LinkedList;
import java.util.Scanner;

public class EOFLearning {
	public static void main(String[] args) {
		Scanner stdin= new Scanner(System.in);
		LinkedList<String> input= new LinkedList<String>();
		while(stdin.hasNextLine()) {
			String nextLine = stdin.nextLine();
			if(nextLine.isEmpty()) {
				break;
			}
			input.add(nextLine);
			
		}
		stdin.close();
		for( int i =0;i<input.size();i++) {
		System.out.println((i+1)+" "+ input.get(i));	
		}
	}

}
