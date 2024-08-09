import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int K = 17;
	static int N, M;

	static boolean[] visited;
	static int[] depth;
	static int[][] parent;
	static ArrayList<Integer>[] adjList;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		System.setIn(new FileInputStream("src/DAY08/P11438/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		int a, b;

		// 트리 입력 받기
		N = Integer.parseInt(br.readLine());

		visited = new boolean[N + 1];
		depth = new int[N + 1];
		parent = new int[K + 1][N + 1];

		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int n = 0; n < N - 1; n++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			adjList[a].add(b);
			adjList[b].add(a);
		}

		bfs(1);
		updateParent();

		// 답 확인 - LCA

		StringBuilder sb = new StringBuilder();

		M = Integer.parseInt(br.readLine());
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			sb.append(lca(a, b)).append("\n");
		}

		System.out.println(sb.toString());
	}

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

	static void updateParent() {
		for (int k = 1; k < K; k++) {
			for (int v = 1; v <= N; v++) {
				parent[k][v] = parent[k - 1][parent[k - 1][v]];
			}
		}
	}

	static int lca(int a, int b) {
		
		// b가 무조건 depth 크게 만들기
		if (depth[a]> depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}

//		// depth가 같지 않다면 같은 뎁스가 될때가지 b를 올린다.
//		while(depth[a]!=depth[b]) {
//			b = parent[0][b];
//		}
		for(int k=K; k>=0; k--) {
			if(depth[b]-depth[a] >= (1<<k)) {
				b = parent[k][b];
			}
		}
		
		// 같으면 바로 return
		if(a == b) {
			return a;
		}
		
		
//		// depth가 같다면 둘의 같이 같아질 때까지 올린다.
//		while(a != b) {
//			a = parent[0][a];
//			b = parent[0][b];
//		}
        for(int k=K; k>=0; k--){
            if(parent[k][a] != parent[k][b]){
                a = parent[k][a];
                b = parent[k][b];
            }
        }
		
		return parent[0][a];
	}
}
