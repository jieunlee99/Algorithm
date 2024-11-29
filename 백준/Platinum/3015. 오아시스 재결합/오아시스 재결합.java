import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Stack<int[]> stack = new Stack<>(); // [키, 같은 키 개수]
                                    
        long count = 0L; // 서로 볼 수 있는 쌍의 수

        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(br.readLine()); // 현재 키
            int sameHeightCount = 1; // 같은 키를 가진 사람의 수

            // A와 B가 서로 불 수 있으려면, 그 사이에 A 또는 B보다 큰 사람이 없어야 함 -> 현재 키와 작거나 같아야 함.
            while (!stack.isEmpty() && stack.peek()[0] <= height) {
                int[] top = stack.pop();
                count += top[1]; // top과 같은 키를 가진 사람의 수만큼 더해줌

                // 만약 top의 키가 현재 키와 동일하다면 중복 처리함
                if(top[0] == height) {
                    sameHeightCount += top[1];
                }
            }

            // 현재 사람과 스택의 맨 위 사람은 서로 볼 수 있음
            if(!stack.isEmpty()) {
                count++;
            }

            stack.push(new int[]{height, sameHeightCount});
        }

        System.out.println(count);
    }
}
