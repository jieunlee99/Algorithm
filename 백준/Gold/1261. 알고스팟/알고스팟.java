import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static int N, M; // N: 열 (가로), M: 행 (세로)
	static int[][] map; // 0 또는 1 (벽) 정보를 담는 지도
	static int[][] dp; // dp[i][j]까지 벽을 부신 최소 횟수

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // N은 가로 (열)
		M = Integer.parseInt(st.nextToken()); // M은 세로 (행)
        
        // 문제에서 M(행) x N(열) 순서로 입력을 받으므로 배열 크기를 맞춥니다.
		map = new int[M + 1][N + 1]; 
		dp = new int[M + 1][N + 1];

		// 지도 입력 (M행 N열)
		for (int i = 1; i <= M; i++) {
			String input = br.readLine();
			for (int j = 1; j <= N; j++) {
				map[i][j] = input.charAt(j - 1) - '0'; // 0: 빈 칸, 1: 벽
			}
            // dp 배열을 Integer.MAX_VALUE로 초기화
            Arrays.fill(dp[i], Integer.MAX_VALUE);
		}

		// 0-1 BFS 시작: 비용이 0 또는 1인 그래프의 최단 경로를 효율적으로 탐색
		// Deque을 사용하여 비용 0은 Deque 앞(addFirst)에, 비용 1은 뒤(addLast)에 넣습니다.
		Deque<int[]> deque = new LinkedList<>(); 

		dp[1][1] = 0; // 시작점은 벽을 부수지 않았으므로 비용 0
		deque.addFirst(new int[] { 1, 1 }); // 시작점은 비용 0이므로 앞쪽에 추가

		while (!deque.isEmpty()) {
			int[] current = deque.pollFirst(); // 항상 비용이 0이거나 가장 작은 노드를 먼저 꺼냄
			int cx = current[0];
			int cy = current[1];
            
            // 만약 현재 노드가 도착지점이라면, 바로 종료 가능 (BFS 특성)
            // if (cx == M && cy == N) break; // (옵션: 출력을 마지막에 하므로 종료는 필수 아님)

			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if (!isInRange(nx, ny)) {
					continue;
				}
                
                // 이동 비용 (0: 빈 칸, 1: 벽)
                int cost = map[nx][ny];

                // 새로운 경로의 비용 = 현재까지의 비용 + 이동 비용
                int newCost = dp[cx][cy] + cost;

                // Relaxation (릴렉세이션): 새로운 경로 비용이 기존 비용보다 작을 경우에만 갱신
                if (dp[nx][ny] > newCost) {
                    dp[nx][ny] = newCost;
                    
                    // 비용에 따라 Deque의 앞/뒤에 추가
                    if (cost == 0) {
                        // 비용 0: Deque의 앞쪽에 추가하여 즉시 탐색 (우선 순위 높음)
                        deque.addFirst(new int[] { nx, ny });
                    } else {
                        // 비용 1: Deque의 뒤쪽에 추가하여 나중에 탐색 (우선 순위 낮음)
                        deque.addLast(new int[] { nx, ny });
                    }
                }
			}
		}

		// (M, N) 지점까지의 최소 비용 출력
		System.out.println(dp[M][N]);
	}

	static boolean isInRange(int x, int y) {
		// x (행)은 1부터 M까지, y (열)은 1부터 N까지
		return 1 <= x && x <= M && 1 <= y && y <= N;
	}

}
