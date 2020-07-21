package hackerRank;

import java.io.StringWriter;
import java.util.Scanner;

public class StringManipulation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
        String A=sc.next();
        String B=sc.next();
        int sum_lengt=A.length()+B.length();
        //lexicography testing
        boolean flag=true,otherchars=true;//otherchars is set to true if all characters being compared are equal
        if(A.charAt(0)==B.charAt(0)) {
        	flag=false;
        	int lastIndex=A.length()<B.length()?A.length():B.length();
			for(int i=1;i<lastIndex;i++) {
				if(A.charAt(i)>B.charAt(i)) {
					flag=true;
					otherchars=false;
					break;
				}//if
				else if(A.charAt(i)<B.charAt(i)) {flag=false;otherchars=false;break;}
			}//for
			if((B.length()>A.length())&& flag==true&&otherchars) flag=false;// if both A and B have value like Java and Javac
			//this will make sure to return No for lexicography test
			if((A.length()>B.length())&& flag==false&&otherchars) flag=true;
        }//if first character is same for both the strings
        else if(A.charAt(0)<B.charAt(0)) flag=false;
        
        StringBuffer bA=new StringBuffer(A);
        StringBuffer bB= new StringBuffer(B);
        bA.setCharAt(0, (char) (bA.charAt(0)-32));
        bB.setCharAt(0, (char) (bB.charAt(0)-32));
        System.out.println(sum_lengt);
       System.out.println(flag==true? "Yes":"No");
        System.out.println(bA+ " "+ bB);
	}

}
