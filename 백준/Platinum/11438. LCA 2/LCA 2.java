import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int K = 17; // 2^17 = 131072로, 약 100,000개의 노드를 다룰 수 있음
	static int N, M; // N은 노드 개수, M은 쿼리 개수
	static boolean[] visited; // 노드 방문 여부 체크
	static int[] depth; // 각 노드의 깊이 저장
	static int[][] parent; // 희소 테이블: parent[k][v]는 노드 v의 2^k 번째 부모
	static ArrayList<Integer>[] adjList; // 인접 리스트로 트리 구조 표현

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		// 배열 및 리스트 초기화
		visited = new boolean[N + 1];
		depth = new int[N + 1];
		parent = new int[K + 1][N + 1];
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		// 트리의 간선 입력 받기
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b); // a와 b가 연결된 간선 추가
			adjList[b].add(a); // 무방향 그래프
		}

		// BFS로 각 노드의 깊이와 1단계 부모를 설정
		bfs(1); // 루트 노드는 1번 노드로 설정
		initializeSparseTable(); // 희소 테이블 초기화하여 각 노드의 2^k 번째 부모 저장

		// LCA 쿼리 처리
		StringBuilder sb = new StringBuilder();
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(lca(a, b)).append("\n"); // 각 쿼리의 결과를 StringBuilder에 추가
		}

		// 결과 출력
		System.out.print(sb);
	}

	// BFS를 통해 각 노드의 깊이와 1단계 부모를 설정
	static void bfs(int root) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(root); // 루트 노드를 큐에 추가
		depth[root] = 0; // 루트의 깊이는 0
		visited[root] = true; // 루트 노드 방문 표시

		while (!queue.isEmpty()) {
			int current = queue.poll(); // 현재 노드 가져오기
			for (int next : adjList[current]) { // 현재 노드의 인접 노드 확인
				if (!visited[next]) { // 인접 노드가 미방문 상태라면
					visited[next] = true; // 방문 처리
					parent[0][next] = current; // next의 1단계 부모는 current
					depth[next] = depth[current] + 1; // next의 깊이는 current의 깊이 + 1
					queue.offer(next); // 큐에 추가하여 다음 탐색
				}
			}
		}
	}

	// 희소 테이블 초기화
	static void initializeSparseTable() {
		for (int k = 1; k <= K; k++) { // 2^k 번째 부모를 설정
			for (int v = 1; v <= N; v++) { // 모든 노드에 대해
				parent[k][v] = parent[k - 1][parent[k - 1][v]]; // parent[k][v]를 2^(k-1) 두 번 거슬러 올라가서 설정
			}
		}
	}

	// 최소 공통 조상(LCA)을 찾는 함수
	static int lca(int a, int b) {
		// 항상 b가 더 깊은 노드가 되도록 설정
		if (depth[a] > depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}

		// 깊이가 같아질 때까지 b를 상위로 올리기
		for (int k = K; k >= 0; k--) {
			if ((depth[b] - depth[a]) >= (1 << k)) { // 깊이 차이가 2^k 이상이면
				b = parent[k][b]; // b를 위로 올림
			}
		}

		// 두 노드가 같으면 바로 반환 (LCA가 이미 같음)
		if (a == b) {
			return a;
		}

		// 두 노드가 같아지기 직전까지 올림
		for (int k = K; k >= 0; k--) {
			if (parent[k][a] != parent[k][b]) { // 부모가 다르면
				a = parent[k][a]; // a와 b 모두를 같은 레벨 위로 올림
				b = parent[k][b];
			}
		}

		return parent[0][a]; // LCA 반환 (두 노드가 같아지기 직전 부모가 LCA)
	}
}
