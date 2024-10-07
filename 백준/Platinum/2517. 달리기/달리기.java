

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    static int N, S;
    static Player[] players;
    static int[] tree, results;


    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/P2517/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        S = 1;
        while (S < N) {
            S *= 2;
        }
        tree = new int[2 * S]; // 트리는 리프 노드 개수의 두 배 만큼 공간 필요함

        players = new Player[N];
        results = new int[N + 1];

        for (int i = 0; i < N; i++) {
            // new Player(rank, stat)
            players[i] = new Player(i + 1, Integer.parseInt(br.readLine()));
        }
        Arrays.sort(players, Comparator.comparing(Player::getStat));

        for (int i = 0; i < N; i++) {
            Player current = players[i];
            results[current.rank] = current.rank - query(1, S, 1, 1, current.rank - 1);
            update(1, S, 1, current.rank, 1);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(results[i]).append("\n");
        }
        System.out.println(sb);
    }

    static void update(int left, int right, int node, int target, long diff) {
        // 범위 밖일 때
        if (target < left || right < target) {
            return;
        }

        tree[node] += diff;
        if (left != right) { // 내부 노드라면
            int mid = (left + right) / 2;
            update(left, mid, node * 2, target, diff);
            update(mid + 1, right, node * 2 + 1, target, diff);
        }
    }

    static int query(int left, int right, int node, int queryLeft, int queryRight) {
        // 범위 밖일 때
        if (queryRight < left || right < queryLeft) {
            return 0;
        } else if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        } else {
            int mid = (left + right) / 2;
            return query(left, mid, node * 2, queryLeft, queryRight) + query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
        }
    }
}

class Player {
    int rank, stat;

    public Player(int rank, int stat) {
        this.rank = rank;
        this.stat = stat;
    }

    public int getRank() {
        return rank;
    }

    public int getStat() {
        return stat;
    }

    @Override
    public String toString() {
        return "Player{" +
                "rank=" + rank +
                ", stat=" + stat +
                '}';
    }
}