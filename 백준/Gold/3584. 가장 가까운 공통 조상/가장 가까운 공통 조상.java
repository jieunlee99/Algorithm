
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int T, N;

	// 간단한 LCA에서는 희소 배열 parent[K+1][N+1] 대신 1차원 배열 parent[N+1]만 사용합니다.
	// parent[v]: 노드 v의 직계 부모 노드
	static int[] depth;
	static boolean[] isChild; // 트리의 루트를 찾기 위해 사용
	static int[] parent; 
	static List<Integer>[] adjList; // 부모 -> 자식 관계만 저장하는 단방향 리스트

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());

			// 초기화
			depth = new int[N + 1];
			isChild = new boolean[N + 1]; 
			parent = new int[N + 1]; 
			adjList = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				adjList[i] = new ArrayList<>();
			}

			// 1. 간선 입력 및 isChild 배열 업데이트 (트리의 방향성 확립)
			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()); // 부모 노드
				int b = Integer.parseInt(st.nextToken()); // 자식 노드
                
				// 문제 입력은 (부모 자식) 순서이므로 단방향으로 저장
				adjList[a].add(b); 
                isChild[b] = true; // B는 자식으로 등장했으므로 루트가 될 수 없음
			}

			// 2. 실제 루트 찾기 및 BFS 시작
			int root = -1;
			for (int i = 1; i <= N; i++) {
				if (!isChild[i]) {
					root = i; // isChild가 false인 유일한 노드가 루트
					break;
				}
			}
            
			// 3. 트리 구축 (깊이 및 1차 부모 설정)
			bfs(root); 

			// 4. LCA 계산
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(lca(a, b)).append("\n");
		}

		System.out.print(sb);
	}

    /**
     * 간단한 LCA 계산 메서드 (Depth Leveling & Simultaneous Step-up)
     */
	private static int lca(int a, int b) {
        // 1. 깊이를 맞춘다: 무조건 a의 깊이가 작거나 같도록 설정
		if (depth[a] > depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}

        // 2. 두 노드의 깊이를 동일하게 만든다. (b를 a 높이까지 끌어올린다)
        // b 노드가 a 노드의 깊이가 될 때까지 한 칸씩 부모를 따라 올라간다.
		while (depth[a] != depth[b]) {
			b = parent[b];
		}

		// 3. 깊이가 같아진 후 a와 b가 같다면, 그 노드가 LCA이다.
		if (a == b) {
			return a;
		}

        // 4. LCA를 찾을 때까지 동시에 한 칸씩 부모를 따라 올라간다.
		while (parent[a] != parent[b]) {
			a = parent[a];
			b = parent[b];
		}

        // 반복문이 끝나면 parent[a] == parent[b]가 LCA이다.
		return parent[a];
	}

    /**
     * 트리의 깊이(depth)와 1차 부모(parent)를 설정하는 BFS 메서드.
     */
	private static void bfs(int root) {
		Queue<Integer> queue = new LinkedList<>();
		
        // BFS를 위해 visited 배열 대신 depth[]를 초기화하여 사용
        Arrays.fill(depth, 0); 

		// 루트 노드 설정
        depth[root] = 1; // 깊이 1부터 시작
        parent[root] = 0; // 루트의 부모는 0 (가상의 노드)
		queue.offer(root);

		while (!queue.isEmpty()) {
			int current = queue.poll();

            // next는 항상 자식 노드이다.
			for (int next : adjList[current]) {
                // 아직 깊이가 설정되지 않은 노드만 처리 (== 미방문)
                // next가 0이면 안 되지만, adjList에 0이 들어갈 일은 없음
				if (depth[next] == 0) { 
					depth[next] = depth[current] + 1;
					parent[next] = current;
					queue.offer(next);
				}
			}
		}
	}

}
