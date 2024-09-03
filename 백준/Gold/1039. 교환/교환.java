
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("src/P1039/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String num = st.nextToken(); // N
        int K = Integer.parseInt(st.nextToken());

        System.out.println(bfs(num, K));
    }

    public static String bfs(String start, int K) {
        Queue<State> queue = new LinkedList<>();

        Set<String>[] visited = new HashSet[K + 1]; // 각 교환 횟수마다 방문 상태 저장
        for (int i = 0; i <= K; i++) {
            visited[i] = new HashSet<>();
        }

        queue.add(new State(start, 0));
        visited[0].add(start);

        int max = -1;

        while (!queue.isEmpty()) {
            State current = queue.poll();

            if (current.depth == K) {
                max = Math.max(max, Integer.parseInt(current.num));
                continue;
            }

            int len = current.num.length();
            for (int i = 0; i < len - 1; i++) {
                for (int j = i + 1; j < len; j++) {
                    // 자리를 바꿀 때 첫 자리가 0이 되면 안 됨
                    if (i == 0 && current.num.charAt(j) == '0') {
                        continue;
                    }

                    // 자리 바꾸기
                    String swapped = swap(current.num, i, j);
                    if (!visited[current.depth + 1].contains(swapped)) {
                        visited[current.depth + 1].add(swapped);
                        queue.add(new State(swapped, current.depth + 1));
                    }
                }
            }
        }

        return max == -1 ? "-1" : Integer.toString(max);
    }

    public static String swap(String num, int i, int j) {
        char[] chars = num.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }

    static class State {
        String num;
        int depth;

        public State(String num, int depth) {
            this.num = num;
            this.depth = depth;
        }
    }
}
