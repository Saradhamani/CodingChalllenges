package hackerRank;

import java.util.Scanner;

public class LexicoSortWOList {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		int k = scan.nextInt();
		scan.close();

		System.out.println(getSmallestAndLargest(s, k));

	}

	public static String getSmallestAndLargest(String s, int k) {
		String smallest = "";
		String largest = "";

		String[] subString=new String[(s.length()-k)+1];
		for(int i=0;i<subString.length;i++) {
			if( i+k>s.length())break;
			subString[i]=s.substring(i, i+k);
		}
		/*
		 * for (String string : subString) { System.out.print(string+","); }
		 * System.out.println("\n****************\n");
		 */
		String[] temp= new String[2];
		for(int i=0;i<subString.length;i++) {
			for(int j=i+1;j<subString.length;j++) {
				temp=compareTwoStrings(subString[i], subString[j], k);
				subString[i]=temp[0];
				subString[j]=temp[1];
			}
			
		}
		/*
		 * for (String string : subString) { System.out.print(string+",");
		 * 
		 * } System.out.println("\n***********\n");
		 */
		smallest=subString[0];
		largest= subString[subString.length-1];
		return smallest + "\n" + largest;
	}
	public static String[] compareTwoStrings(String s1, String s2,int k) {
		String[] res= new String[2];
		if(s1.charAt(0)<s2.charAt(0)) {
			res[0]=s1;
			res[1]=s2;
		}
		else if(s1.charAt(0)==s2.charAt(0)){
			if(s1.length()==1&&s2.length()==1) {
				res[0]=s1;
				res[1]=s2;
			}
			for(int i=1;i<k;i++) {
				if(s1.charAt(i)<s2.charAt(i)) {
					res[0]=s1;
					res[1]=s2;
					break;
				}//if
				else if(s1.charAt(i)>s2.charAt(i)) {
					res[0]=s2;
					res[1]=s1;
					break;
				}

			}
		}
		else if(s1.charAt(0)>s2.charAt(0))
		{
			res[0]=s2;
			res[1]=s1;
		}
		//System.out.println(res[0]+" "+ res[1]);
		return res;
	}
}
