
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int TC;
	static int N, M, W;
	static int[] dist;
	static List<Edge> edges;
	// 웜홀은 10,000 * 2,500 = 25,000,000 이므로 int 범위로 충분하지만,
	// 안전하게 Integer.MAX_VALUE 대신 적당히 큰 값(예: 10억)을 사용합니다.
	static final int INF = 1_000_000_000;

	static class Edge {
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		TC = Integer.parseInt(br.readLine());

		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			dist = new int[N + 1];
			edges = new ArrayList<>();

			// 1. 도로 입력 (양방향)
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				edges.add(new Edge(S, E, T));
				edges.add(new Edge(E, S, T));
			}

			// 2. 웜홀 입력 (단방향, 음수 가중치)
			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				edges.add(new Edge(S, E, (T * -1)));
			}

			if (bellmanFord()) {
				sb.append("YES").append("\n");
			} else {
				sb.append("NO").append("\n");
			}
		}

		System.out.print(sb);
	}

	/**
	 * TLE를 피하기 위해 벨만-포드를 1회만 실행하여 음수 사이클 존재 여부를 확인합니다.
	 * 
	 * @return 음수 사이클이 존재하면 true
	 */
	static boolean bellmanFord() {
		// N-1번의 릴렉세이션(Relaxation) 수행
		for (int i = 1; i < N; i++) {
			for (Edge edge : edges) {
				// (dist[from] + cost)가 INF보다 작을 때만 갱신 (오버플로우 방지)
				// dist[from] == INF 체크를 제거하여 모든 노드 동시 탐색
				if (dist[edge.to] > dist[edge.from] + edge.cost) {
					dist[edge.to] = dist[edge.from] + edge.cost;
				}
			}
		}

		// N번째 릴렉세이션: 이때 갱신이 발생하면 음수 사이클이 존재
		for (Edge edge : edges) {
			if (dist[edge.to] > dist[edge.from] + edge.cost) {
				return true; // 음수 사이클 발견
			}
		}

		return false;
	}
}
