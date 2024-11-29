import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] heights;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        heights = new int[N];

        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }

        long answer = getMaxArea();
        System.out.println(answer);
    }

    static long getMaxArea() {
        Stack<Integer> stack = new Stack<>();

        long maxArea = 0;

        for (int i = 0; i <= N; i++) {

            int h = (i == N) ? 0 : heights[i];

            while (!stack.isEmpty() && heights[stack.peek()] >= h) {
                long height = heights[stack.pop()];
                long weight = stack.isEmpty() ? i : (i - stack.peek() - 1);
                maxArea = Math.max(maxArea, height * weight);
            }
            stack.push(i);
        }

        return maxArea;
    }
}

