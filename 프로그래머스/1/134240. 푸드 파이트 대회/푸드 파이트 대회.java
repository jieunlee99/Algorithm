import java.util.*;

class Solution {
    public String solution(int[] food) {
        StringBuilder front = new StringBuilder();
        
        for(int i=0; i<food.length; i++) {
            if(food[i] <= 1) {
                continue;
            }
            
            int half = food[i]/2;
            while(half-->0) {
                front.append(i);
            }
        }
        
        StringBuilder back = new StringBuilder(front.toString());
        back.reverse();
        
        front.append(0);
        front.append(back.toString());
        
        return front.toString();
    }
}