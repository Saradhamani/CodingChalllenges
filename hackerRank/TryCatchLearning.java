package hackerRank;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TryCatchLearning {
 
	public static void main(String[] args) {
		Scanner scan= new Scanner(System.in);
		try {
		int x= scan.nextInt();
		int y = scan.nextInt();
		System.out.println(x/y);
		}catch (InputMismatchException e) {
			System.out.println("java.util.InputMismatchException");
		}catch (ArithmeticException e) {
			System.out.println("java.lang.ArithmeticException: " + e.getMessage());
			// TODO: handle exception
		}catch(Exception e) {
		System.out.println("Exception Occurred");
		}finally {
			scan.close();
		}
	}

}
