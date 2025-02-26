import java.io.*;
import java.util.*;

public class Main {

	static int T, n, k, t, m;
	static int[][] logs;
	static List<Info> teams;
	static StringBuilder sb = new StringBuilder();

	static class Info implements Comparable<Info> {
		int id, score, cnt, time;

		public Info(int id, int score, int cnt, int time) {
			this.id = id; // 팀 id
			this.score = score; // 팀 점수
			this.cnt = cnt; // 제출 횟수
			this.time = time; // 최종 제출 시간
		}

		@Override
		public int compareTo(Info i) {
			// 우선 순위
			// 1. 점수
			// 2. 제출 횟수
			// 3. 시간
			if (this.score == i.score) {
				if (this.cnt == i.cnt) {
					return this.time - i.time;
				}
				return this.cnt - i.cnt;
			}
			return i.score - this.score;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken()); // 팀 개수
			k = Integer.parseInt(st.nextToken()); // 문제 개수
			t = Integer.parseInt(st.nextToken()); // 내 팀 id
			m = Integer.parseInt(st.nextToken()); // 로그 엔트리 개수

			logs = new int[m][3];

			for (int x = 0; x < m; x++) {
				st = new StringTokenizer(br.readLine());
				logs[x][0] = Integer.parseInt(st.nextToken()); // 팀 id
				logs[x][1] = Integer.parseInt(st.nextToken()); // 문제 번호
				logs[x][2] = Integer.parseInt(st.nextToken()); // 획득 점수
			}

			int[][] saveScore = new int[n + 1][k + 1]; // 각 팀의 문제별 점수 저장
			int[] cntSubmit = new int[n + 1]; // 제출 횟수
			int[] time = new int[n + 1]; // 최종제출 시간 저장

			for (int i = 0; i < m; i++) {
				int currentId = logs[i][0];
				int currentNum = logs[i][1];
				int currentScore = logs[i][2];

				cntSubmit[currentId]++;
				time[currentId] = i;

				if (saveScore[currentId][currentNum] < currentScore) {
					saveScore[currentId][currentNum] = currentScore;
				}
			}

			teams = new ArrayList<>();
			for (int i = 1; i <= n; i++) {
				int sum = 0;
				for (int j = 1; j <= k; j++) {
					sum += saveScore[i][j];
				}

				teams.add(new Info(i, sum, cntSubmit[i], time[i]));
			}

			Collections.sort(teams);

			for (int i = 0; i < n; i++) {
				if (teams.get(i).id == t) {
					sb.append(i + 1).append("\n");
				}
			}
		}

		System.out.print(sb);
	}
}
