package hackerRank;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**Given a string, S,and an integer, K,complete the function 
 * so that it finds the lexicographically smallest and largest 
 * substrings of length K.
Input Format:
The first line contains a string denoting .
The second line contains an integer denoting .
Constraints:
S consists of English alphabetic letters only (i.e., [a-zA-Z]).
Output Format
Return the respective lexicographically smallest and largest substrings as a single newline-separated string.
 **/

public class LexicographicallySorted {

	 public static String getSmallestAndLargest(String s, int k) {
	        String smallest = "";
	        String largest = "";
	        List<String>sub=new LinkedList<String>();
	        // Complete the function
	        // 'smallest' must be the lexicographically smallest substring of length 'k'
	        // 'largest' must be the lexicographically largest substring of length 'k'
	        for(int i=0;i<s.length();i++) {
	        	if(i+k>s.length()) break;
	        	sub.add(s.substring(i, i+k));
	        }
	        //System.out.println(sub);
	        Collections.sort(sub);
	        //System.out.println(sub);
	        smallest=sub.get(0);
	        largest=sub.get(sub.size()-1);
	        return smallest + "\n" + largest;
	    }


	    public static void main(String[] args) {
	        Scanner scan = new Scanner(System.in);
	        String s = scan.next();
	        int k = scan.nextInt();
	        scan.close();
	      
	        System.out.println(getSmallestAndLargest(s, k));
	    }

}
