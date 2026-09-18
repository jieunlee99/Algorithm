import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int n = numbers.length;
        
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        
        // stack에 인덱스를 넣어줌
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {

            while (!stack.isEmpty()
                    && numbers[stack.peek()] < numbers[i]) {

                int idx = stack.pop();
                answer[idx] = numbers[i];
            }

            stack.push(i);
        }
        
        return answer;
    }
}