import java.io.*;
import java.util.*;

public class Main {

	static final int MAX_COST = 100_000 * 4;
	static int[][][] dp; // dp[left][right][idx] : 왼발이 left, 오른발이 right에 있을 때 idx까지의 최소 비용
	static List<Integer> commands;
	static int commandSize;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		commands = new ArrayList<>();
		commands.add(0); // 시작 위치를 0으로 초기화

		// 입력 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		int command;
		while ((command = Integer.parseInt(st.nextToken())) != 0) {
			commands.add(command);
		}
		commandSize = commands.size();

		// dp 초기화
		dp = new int[5][5][commandSize];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < commandSize; k++) {
					dp[i][j][k] = MAX_COST;
				}
			}
		}
		dp[0][0][0] = 0;

		// dp 실행
		for (int idx = 1; idx < commandSize; idx++) {
			int currentCommand = commands.get(idx);
			for (int left = 0; left < 5; left++) {
				for (int right = 0; right < 5; right++) {
					// 왼발을 이동시키는 경우
					dp[currentCommand][right][idx] = Math.min(dp[currentCommand][right][idx],
							dp[left][right][idx - 1] + moveCost(left, currentCommand));
					// 오른발을 이동시키는 경우
					dp[left][currentCommand][idx] = Math.min(dp[left][currentCommand][idx],
							dp[left][right][idx - 1] + moveCost(right, currentCommand));

				}
			}
		}

		// 최소 비용 찾기
		int answer = MAX_COST;
		int lastCommand = commands.get(commandSize - 1);
		for (int i = 0; i < 5; i++) {
			answer = Math.min(answer,
					Math.min(dp[lastCommand][i][commandSize - 1], dp[i][lastCommand][commandSize - 1]));
		}
		System.out.println(answer);
	}

	static int moveCost(int from, int to) {
		if (from == 0) // 시작 위치에서 이동
			return 2;
		if (from == to) // 같은 위치로 이동
			return 1;
		if ((from + to) % 2 == 0) // 반대 위치로 이동
			return 4;
		return 3; // 인접한 위치로 이동
	}
}
