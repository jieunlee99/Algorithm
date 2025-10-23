import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// N의 최대값 10만, log2(100000) = 약 16.6이므로 K=17이 적절합니다.
	static final int K = 17; 

	static int N, M;

	static int[] depth;
	static int[][] parent;
	// costUp[k][v]: 노드 v에서 2^k번째 부모로 올라가는 경로의 총 비용
	static long[][] costUp; 
	static List<Edge>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		depth = new int[N + 1];
		parent = new int[K + 1][N + 1]; 
		// 비용 합은 int 범위를 초과할 수 있으므로 long 사용
		costUp = new long[K + 1][N + 1]; 
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		// 간선 정보 입력
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList[u].add(new Edge(v, w));
			adjList[v].add(new Edge(u, w));
		}

		// BFS를 통해 트리 기본 정보 초기화 (루트 노드 1 기준)
		bfs(1); 
		// 희소 배열(Sparse Table) 전처리
		initSparseTable();

		M = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			if (type == 1) {
				// Type 1: u에서 v까지의 경로 비용 합
				long totalCost = getPathCost(u, v);
				sb.append(totalCost).append("\n");
			} else if (type == 2) {
				// Type 2: u에서 v까지의 경로에서 k번째 노드 찾기
				int k = Integer.parseInt(st.nextToken());
				int kthNode = getKthNode(u, v, k);
				sb.append(kthNode).append("\n");
			}
		}
		System.out.print(sb);
	}

	/**
	 * Type 1 쿼리: u와 v 사이의 경로 비용 합을 계산합니다.
	 * (u->LCA) 비용 + (v->LCA) 비용
	 */
	static long getPathCost(int u, int v) {
		int ancestor = lca(u, v);
		// u에서 LCA까지 올라가는 비용 + v에서 LCA까지 올라가는 비용
		return getCostToAncestor(u, ancestor) + getCostToAncestor(v, ancestor);
	}

	/**
	 * 노드 start에서 ancestor(start의 조상)까지 올라가는 비용을 계산합니다.
	 */
	static long getCostToAncestor(int start, int ancestor) {
		long cost = 0;
		int diff = depth[start] - depth[ancestor]; // 올려야 할 깊이 차이

		// diff를 이진수로 분해하여 필요한 2^k 점프만 수행하며 비용 누적
		for (int k = K; k >= 0; k--) {
			if ((diff & (1 << k)) != 0) { 
				cost += costUp[k][start];
				start = parent[k][start];
			}
		}
		return cost;
	}

	/**
	 * Type 2 쿼리: u에서 v까지의 경로에서 k번째 노드를 찾습니다.
	 */
	static int getKthNode(int u, int v, int k) {
		int ancestor = lca(u, v);
		int depthU = depth[u];
		int depthV = depth[v];
		int depthLCA = depth[ancestor];
		
		// 경로의 총 길이 (간선 수)
		int totalLength = (depthU - depthLCA) + (depthV - depthLCA);
		
		// k번째 노드는 1부터 시작 (u가 k=1)
		if (k > totalLength + 1) return -1; // 존재하지 않는 노드

		int targetNode;
		int jump;

		if (k <= depthU - depthLCA + 1) {
			// k번째 노드가 u에서 LCA로 올라가는 경로 상에 있는 경우
			// u에서 k-1칸 올라가야 한다.
			targetNode = u;
			jump = k - 1;
		} else {
			// k번째 노드가 LCA를 지나 v로 내려가는 경로 상에 있는 경우
			// v에서 LCA로 올라오는 경로 상의 노드를 찾아야 함.
			// v에서 구하려는 노드까지의 거리 = 총 노드 수 - k번째 노드의 위치 = (totalLength + 1) - k
			int distance = totalLength - (k - 1); // k-1은 u에서부터의 간선 수
			
			targetNode = v;
			jump = distance; // v에서 distance만큼 올라가면 k번째 노드가 된다.
		}
		
		// jump 횟수만큼 targetNode를 위로 올린다.
		return climbUp(targetNode, jump);
	}
	
	/**
	 * 노드 start를 count만큼 위로 올리는 메서드 (Binary Lifting)
	 */
	static int climbUp(int start, int count) {
		// count를 이진수로 분해하여 점프
		for (int k = K; k >= 0; k--) {
			if ((count & (1 << k)) != 0) { 
				start = parent[k][start];
			}
		}
		return start;
	}

	/**
	 * LCA를 찾는 메서드 (깊이 맞추기 로직 강화)
	 */
	static int lca(int a, int b) {
		// 1. 깊이가 깊은 쪽(b)을 a와 동일한 깊이로 올린다.
		if (depth[a] < depth[b]) { 
			int temp = a;
			a = b;
			b = temp;
		}

		int diff = depth[a] - depth[b]; // 깊이 차이 (a가 더 깊거나 같음)
		
		// 깊이 차이를 Binary Lifting으로 해소
		for (int k = K; k >= 0; k--) {
			if ((diff & (1 << k)) != 0) { 
				a = parent[k][a];
			}
		}

		if (a == b) {
			return a;
		}

		// 2. LCA 바로 아래까지 동시에 올린다.
		for (int k = K; k >= 0; k--) {
			if (parent[k][a] != parent[k][b]) {
				a = parent[k][a];
				b = parent[k][b];
			}
		}

		// LCA는 현재 a, b의 직계 부모이다.
		return parent[0][a];
	}

	/**
	 * 희소 배열(Sparse Table)을 초기화하여 2^k 조상 및 비용을 계산한다.
	 */
	static void initSparseTable() {
		for (int k = 1; k <= K; k++) {
			for (int v = 1; v <= N; v++) {
				// 부모 갱신: parent[k][v] = 2^(k-1) 부모의 2^(k-1) 부모
				parent[k][v] = parent[k - 1][parent[k - 1][v]];
				
				// 비용 갱신: costUp[k][v] = 
				//           (v -> 2^(k-1) 부모 비용) + (2^(k-1) 부모 -> 2^k 부모 비용)
				costUp[k][v] = costUp[k - 1][v] + costUp[k - 1][parent[k - 1][v]];
			}
		}
	}

	/**
	 * BFS를 통해 트리의 깊이(depth), 1차 부모(parent[0]), 1차 비용(costUp[0])을 설정합니다.
	 */
	static void bfs(int root) {
		Queue<Integer> queue = new LinkedList<>();

		depth[root] = 1; // 깊이는 1부터 시작 (루트)
		parent[0][root] = 0; // 루트의 부모는 0 (가상의 노드)
		queue.offer(root);

		while (!queue.isEmpty()) {
			int current = queue.poll();

			for (Edge edge : adjList[current]) {
				int next = edge.to;
				int cost = edge.cost;

				// 아직 깊이가 설정되지 않은 노드만 처리 (== 미방문)
				if (depth[next] == 0) { 
					depth[next] = depth[current] + 1;
					parent[0][next] = current;
					costUp[0][next] = cost; // next에서 current(부모)로 올라가는 비용 설정
					queue.offer(next);
				}
			}
		}
	}

	static class Edge {
		int to, cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
}
