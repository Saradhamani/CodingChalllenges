package codility;

import java.util.LinkedHashMap;
import java.util.Map;

class Solution {
    public int solution(String S, int[] X, int[] Y) {
        int length_ofMap=0;
        Map<Character,Integer[]> points=new LinkedHashMap<Character,Integer[]>();
        char[] tags=S.toCharArray();
        for(int i=0;i<X.length;i++) {
        	if(points.containsKey(tags[i])) {
        		Integer[] values=points.get(tags[i]);
        		int prevIndex=values[0];
        		if(Math.abs(X[prevIndex])==Math.abs(X[i])) {
        			points.remove(tags[i]);
        			
        			
        		}
        	}
        	else {
        		Integer[] values=new Integer[3];
        		values[0]=i;
        		values[1]=X[i];
        		values[2]=Y[i];
        		points.put(tags[i],values);
        	}
        }
        
        return points.size();
    }
}