package hackerRank;

import java.util.Scanner;

public class StaticInitializationBlocks {
	static int B,H;
	static boolean flag=true;
	static {
		Scanner stdin= new Scanner(System.in);
		B=stdin.nextInt();
		H=stdin.nextInt();
		if(B<=0 || H<=0) flag=false;
		stdin.close();
		 if(!flag)
		        System.out.println("java.lang.Exception: Breadth and height must be positive");
	}
	
	public static void main(String[] args) {
		if(flag){
			int area=B*H;
			System.out.print(area);
		}
		//else System.out.println("java.lang.Exception: Breadth and height must be positive");

	}

}
