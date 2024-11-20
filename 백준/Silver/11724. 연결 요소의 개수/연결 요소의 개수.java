import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	
	static List<Integer>[] adjList;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점 개수
		M = Integer.parseInt(st.nextToken()); // 간선 개수
		
		adjList = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		visited = new boolean[N+1];
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			// 방향 없는 그래프
			adjList[u].add(v);
			adjList[v].add(u);
		}
		
		int cnt = 0;
		
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				bfs(i);
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	
	static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			for(int next:adjList[current]) {
				if(!visited[next]) {
					queue.offer(next);
					visited[next] = true;
				}
			}
		}
	}
}
