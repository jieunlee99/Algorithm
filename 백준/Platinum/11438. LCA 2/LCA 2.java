
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int K = 17; // 2 ≤ N ≤ 100,000 (2^17은 약 100,000개의 노드를 다룰 수 있음)
	static int N, M;

	static boolean[] visited; // 방문 여부 저장
	static int[] depth; // 각 노드의 깊이 저장
	static int[][] parent; // 희소 테이블: parent[k][v]는 노드 v의 2^k 번째 부모

	static List<Integer>[] adjList; // 인접리스트로 트리 구조 표현

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		// 배열, 리스트 초기화
		visited = new boolean[N + 1];
		depth = new int[N + 1];
		parent = new int[K + 1][N + 1];
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		// 간선 입력 (무방향)
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b); // a->b
			adjList[b].add(a); // b->a
		}

		bfs(1); // bfs로 각 노드의 깊이와 1단계 부모 설정
		initTree(); // 희소 테이블을 초기화하여 각 노드의 2^k 번째 부모 저장

		M = Integer.parseInt(br.readLine());

		// LCA 쿼리
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(lca(a, b)).append("\n");
		}

		System.out.println(sb);
	}

	// bfs를 통해 각 노드의 깊이와 1단계 부모를 설정
	static void bfs(int root) {
		Queue<Integer> queue = new LinkedList<>();

		queue.add(root);
		depth[root] = 0;
		visited[root] = true;

		while (!queue.isEmpty()) {
			int current = queue.poll();
			for (int next : adjList[current]) {
				if (!visited[next]) {
					visited[next] = true;
					parent[0][next] = current;
					depth[next] = depth[current] + 1;
					queue.offer(next);
				}
			}
		}
	}

	// 희소테이블 초기화
	static void initTree() {
		for (int k = 1; k <= K; k++) {
			for (int v = 1; v <= N; v++) {
				parent[k][v] = parent[k - 1][parent[k - 1][v]];
			}
		}
	}

	// 최소 공통 조상을 return
	static int lca(int a, int b) {

		// 항상 b가 더 깊은 노드가 되도록 한다.
		if (depth[a] > depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}

		// 깊이가 같아질 때까지 b를 상위로 올린다.
		for (int k = K; k >= 0; k--) {
			if ((depth[b] - depth[a]) >= (1 << k)) {
				b = parent[k][b];
			}
		}

		// 두 노드가 같으면 바로 반환한다.
		if (a == b) {
			return a;
		}

		// 두 노드가 같아지기 직전까지 올린다.
		for (int k = K; k >= 0; k--) {
			if (parent[k][a] != parent[k][b]) {
				a = parent[k][a];
				b = parent[k][b];
			}
		}

		// 두 노드가 같아지기 직전 부모가 LCA가 된다.
		return parent[0][a];
	}
}
