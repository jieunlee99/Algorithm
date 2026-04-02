import java.util.*;

class Solution {
    
    public long solution(String expression) {
        long answer = 0;
        
        ArrayList<Long> numbers = new ArrayList<>();
        ArrayList<Character> operators = new ArrayList<>();
        StringBuilder numBuilder = new StringBuilder();

        for (char c : expression.toCharArray()) {
            // 연산자를 만난 경우
            if (c == '+' || c == '-' || c == '*') {
                operators.add(c);
                numbers.add(Long.parseLong(numBuilder.toString())); 
                numBuilder.setLength(0); 
            } 
            // 숫자인 경우
            else {
                numBuilder.append(c); 
            }
        }
        // 마지막 숫자 추가
        numbers.add(Long.parseLong(numBuilder.toString()));
        
        
        char[][] priorities = {
            {'*', '+', '-'}, {'*', '-', '+'},
            {'+', '*', '-'}, {'+', '-', '*'},
            {'-', '+', '*'}, {'-', '*', '+'},
        };
        
        for(char[] priority:priorities) {
            ArrayList<Long> nums = new ArrayList<>(numbers);
            ArrayList<Character> ops = new ArrayList<>(operators);
            
            // 우선순위마다 계산
            for(char op:priority) {
                for(int i=0; i<ops.size(); ) {
                    // 우선순위가 높은 연산자부터 계산
                    if(ops.get(i) == op) { 
                        long temp = calc(nums.get(i), nums.get(i+1), op);
                        nums.set(i, temp);
                        nums.remove(i+1);
                        ops.remove(i);
                    } else {
                        i++;
                    }
                }
            }
            
            answer = Math.max(answer, Math.abs(nums.get(0)));
        }
        
        return answer;
    }
    
    public long calc(long a, long b, char op) {
        if(op == '+') return a+b;
        else if(op == '-') return a-b;
        return a*b;
    }
}