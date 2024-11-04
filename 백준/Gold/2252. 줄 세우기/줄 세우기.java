
import java.util.*;
import java.io.*;

public class Main {

	static int N, M;
	static int[] inDegree;
	static List<Integer>[] graph;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 학생 수
		M = Integer.parseInt(st.nextToken()); // 키를 비교한 횟수

		graph = new ArrayList[N + 1];
		inDegree = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b); // a->b
			inDegree[b]++;
		}
		
		topologicalSort();
		
		System.out.println(sb.toString());
	}
	
	static void topologicalSort() {
		Queue<Integer> queue = new LinkedList<>();
		
		// 진입 차수가 0인 노드를 큐에 삽입
		for(int i=1; i<=N; i++) {
			if(inDegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			sb.append(current).append(" ");
			
			// 현재 노드와 연결된 노드들의 진입 차수를 감소시킴
			for(int next : graph[current]) {
				inDegree[next]--;
				
				// 진입 차수가 0이 되면 큐에 추가함
				if(inDegree[next] == 0) {
					queue.offer(next);
				}
			}
		}
	}
}