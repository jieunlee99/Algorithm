import java.util.*;

class Solution {
    public String solution(String s) {
        int min = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).min().getAsInt();
        int max = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).max().getAsInt();
        
        return min + " " + max;
    }
}