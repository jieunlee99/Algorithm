import java.util.*;

// 택배는 1~n까지 순서대로 들어오는데 order 순서로 택배를 실어야 함
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int idx = 0;
        
        Stack<Integer> stack = new Stack<>();
        
        for(int i=1; i<=order.length; i++) {
            stack.push(i);
            
            while(!stack.isEmpty()) {
                if(stack.peek() == order[idx]) {
                    stack.pop();
                    idx++;
                    answer++;
                } else {
                    break;
                }
            }
        }
        
        return answer;
    }
}