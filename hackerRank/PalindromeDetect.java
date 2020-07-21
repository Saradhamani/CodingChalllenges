package hackerRank;

import java.io.*;
import java.util.Scanner;

public class PalindromeDetect {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String A=sc.next();
		StringBuilder B= new StringBuilder(A).reverse();
		//System.out.println("A:"+ A+ "B:"+B);
		System.out.println(A.equals(B.toString())?"Yes":"No");
		
	}
}
