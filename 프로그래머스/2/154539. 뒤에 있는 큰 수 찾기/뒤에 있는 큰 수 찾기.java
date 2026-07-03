import java.util.Stack;

class Solution {
    public int[] solution(int[] numbers) {

         int n = numbers.length;
        int[] answer = new int[n];

        Stack<Integer> stack = new Stack<>();

        for(int i=n-1; i>=0; i--) {
            while(!stack.empty()) {
                if(stack.peek() <= numbers[i]) {
                    stack.pop();
                } else {
                    answer[i] = stack.peek();
                    break;
                }
            }

            // 큰 수가 뒤에 없을 때
            if(stack.empty()) {
                answer[i] = -1;
            }
            stack.push(numbers[i]);
        }

        return answer;
    }
}