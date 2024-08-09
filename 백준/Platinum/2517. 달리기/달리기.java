import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

	static int N, S; // N은 3 이상 500,000 이하
	static int rank; // rank는 1 이상 1,000,000,000 이하
	static int MAX;
	static Player[] players;
	static int[] tree;
	static int[] results;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		// System.setIn(new FileInputStream("src/DAY04/P2517/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		S = 1;
		while (S < N) {
			S *= 2;
		}

		tree = new int[2 * S];

		players = new Player[N];
		results = new int[N+1];
		
		for (int i = 0; i < N; i++) {
			players[i] = new Player(i + 1, Integer.parseInt(br.readLine()));
		}

		Arrays.sort(players, Comparator.comparing(Player::getStat));
//		System.out.println(Arrays.toString(players));

		for (int i = 0; i < N; i++) {
			Player currentPlayer = players[i];

			// query (1~num-1 까지의 합)
			results[currentPlayer.rank] = currentPlayer.rank
					-query(1, S, 1, 1, currentPlayer.rank - 1);
			
			// update
			update(1, S, 1, currentPlayer.rank, 1);

		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			sb.append(results[i]).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	static void update(int left, int right, int node, int target, long diff) {
		if (target < left || right < target) { // 상관 X
			return;
		} else {
			tree[node] += diff;
			if (left != right) { // 내부 노드일 때
				int mid = (left + right) / 2;
				update(left, mid, node * 2, target, diff);
				update(mid + 1, right, node * 2 + 1, target, diff);
			}
		}
	}

	static int query(int left, int right, int node, int queryLeft, int queryRight) {
		if (queryRight < left || right < queryLeft) { // 영역 밖 -> 무시
			return 0; // 영향가지 않는 값 return (합 0, 곱 1)
		} else if (queryLeft <= left && right <= queryRight) { // 영역 안
			return tree[node];
		} else { // 걸쳐있음
			int mid = (left + right) / 2;
			return query(left, mid, node * 2, queryLeft, queryRight)
					+ query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
		}
	}

}

class Player {
	int rank;
	int stat;

	public int getRank() {
		return rank;
	}

	public int getStat() {
		return stat;
	}

	public Player(int num, int skill) {
		this.rank = num;
		this.stat = skill;
	}

	@Override
	public String toString() {
		return "Player [num=" + rank + ", skill=" + stat + "]";
	}

}
