import java.util.*;
import java.io.*;

public class Main {

	static int N;
	
	static int[] time;
	static int[] indegree;
	static int[] answer;
	static List<Integer>[] graph;
	static Queue<Integer> queue;
	
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		time = new int[N+1];
		indegree = new int[N+1];
		answer = new int[N+1];
		graph = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		queue = new LinkedList<>();
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			
			while(st.hasMoreTokens()) {
				int num = Integer.parseInt(st.nextToken());
				
				if(num == -1) {
					break;
				}
				
				graph[num].add(i); // num -> i
				indegree[i]++;
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(indegree[i] == 0) {
				queue.offer(i);
				answer[i] = time[i];
			}
		}
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			for(int next:graph[current]) {
				indegree[next]--;
				answer[next] = Math.max(answer[next], answer[current]+time[next]);
				
				if(indegree[next] == 0) {
					queue.offer(next);
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			bw.write(answer[i] + "\n");
		}
		
		bw.flush();
		
		br.close();
		bw.close();
	}

}
