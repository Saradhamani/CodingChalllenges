package hackerRank;

import java.util.Scanner;

public class AnagramsDetect {

	static boolean isAnagram(String a, String b) {
		boolean flag=true;
		if(a.length()!=b.length()) flag=false;
		else {
			for(int i=0;i<a.length();i++) {
              if(getFrequency(a.charAt(i), a.toLowerCase())!=getFrequency(a.charAt(i), b.toLowerCase())) {
            	  flag=false;
            	  break;
              }
			}
		}
		return flag;
	}

	public static int getFrequency(char ch,String s) {
		int freq=0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)==ch) freq++;
		}
		return freq;
	}
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String a = scan.next();
		String b = scan.next();
		scan.close();
		boolean ret = isAnagram(a, b);
		System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
	}

	
}
