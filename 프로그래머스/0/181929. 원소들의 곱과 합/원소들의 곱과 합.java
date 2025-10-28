import java.util.*;

class Solution {
    public int solution(int[] num_list) {
        int sum = Arrays.stream(num_list).reduce(0, Integer::sum);
        int mul = Arrays.stream(num_list).reduce(1, (a,b)->a*b);
        return sum*sum > mul ? 1 : 0;
    }
}