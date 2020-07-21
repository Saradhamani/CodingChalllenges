package codility;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StringCompression {
	public int solution(String S, int K) {
		Map<String,Integer>aftercomp=new LinkedHashMap<String,Integer>();
       for( int i =0;i<(S.length()-K+1);i++) {
    	   char[] chars=S.toCharArray();
    	   String remove_str="";
    	   remove_str=S.substring(0,i)+S.substring(i+K);
      	   String after_re=new String(remove_str);
    	   String out=StringCompression.compressString(after_re);
    	   System.out.println(out);
    	   aftercomp.put(out, out.length());
       }
       return Collections.min(aftercomp.values());
    }
	
	public static String compressString(String S) {
		 String output="";
	        int sum=1;int last=0; boolean flag=false;
	        for(int i=0;i<S.length()-1;i++) {
	        	if(Character.isDigit(S.charAt(i+1))) { last=i;flag=true;
	        	continue;
	        	}
	        	if(flag)
	        	{
	        		if(S.charAt(last)==S.charAt(i+1)) {
		        		sum++;
		        		last=i;
		        	}
	        	}
	        	if(S.charAt(i)==S.charAt(i+1)) {
	        		sum++;
	        		last=i;
	        	}
	        	else {
	        		if(sum>1)
	        		output =output+sum+S.charAt(i);
	        		else 
	        			output=output+S.charAt(i);
	        		sum=1;
	        	}
	        }
	        
	        output=output+sum+S.charAt(S.length()-1);
	        return output.length()<S.length()? output :S;
	}
	
	public static void main(String[] args) {
		String S="AAAAAAAAAAABXXAAAAAAAAAA";
		System.out.println(new StringCompression().solution(S,3));
		
	
	}
}
