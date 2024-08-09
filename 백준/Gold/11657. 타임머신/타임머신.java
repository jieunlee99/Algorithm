import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static long[] dist;
	static ArrayList<Edge> adjList;
	
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("src/DAY09/P11657/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new long[N+1];
		adjList = new ArrayList<>();
		
		int a, b, c;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			adjList.add(new Edge(a,b,c));
		}
		
		boolean hasNegativeCycle = bellman_ford_moore();
		
		StringBuilder sb = new StringBuilder();
		if(hasNegativeCycle) {
			sb.append(-1).append("\n");
		} else {
			for(int i=2; i<=N; i++) {
	            if (dist[i] == Long.MAX_VALUE) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(dist[i]).append("\n");
                }
			}
		}
		
		System.out.println(sb);
	}

	static boolean bellman_ford_moore() {
		
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[1] = 0; // start = 1;
		
		for(int i=0; i<N-1; i++) {
			for(Edge edge: adjList) {
				if(dist[edge.from] == Long.MAX_VALUE) {
					continue;
				}
				
				if(dist[edge.to] > dist[edge.from]+edge.cost) {
					dist[edge.to] = dist[edge.from]+edge.cost;
				}
			}
		}

		// 음의 사이클이 있는지 확인
		boolean hasNegativeCycle = false;
		for(Edge edge: adjList) {
			if(dist[edge.from] == Long.MAX_VALUE) {
				continue;
			}
			
			if(dist[edge.to] > dist[edge.from]+edge.cost) {
				hasNegativeCycle = true;
				break;
			}
		}
		
		return hasNegativeCycle;
	}
}

class Edge{
	int from, to, cost;

	public Edge(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}
}