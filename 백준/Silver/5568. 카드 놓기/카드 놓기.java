import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static int N, K;
    static String[] cards;
    static boolean[] visited;
    static Set<String> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        cards = new String[N];
        visited = new boolean[N];

        for(int i=0; i<N; i++) {
            cards[i] = br.readLine();
        }

        findCombinations("", 0);

        System.out.println(set.size());
    }

    static void findCombinations(String current, int count) {
        if(count == K) {
            set.add(current);
            return;
        }

        for(int i=0; i<N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                findCombinations(current+cards[i], count+1);
                visited[i] = false;
            }
        }
    }
}
