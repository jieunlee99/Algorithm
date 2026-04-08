
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] top;
	static int[][] gear;
	static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		top = new int[4]; // 12시 방향의 인덱스 저장

		gear = new int[4][8];
		for (int i = 0; i < 4; i++) {
			String input = br.readLine();
			for (int j = 0; j < 8; j++) {
				gear[i][j] = input.charAt(j) - '0'; // N(0), S(1)
			}
		}

		K = Integer.parseInt(br.readLine());

		while (K-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int num = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken()); // 1(시계), -1(반시계)

			int[] rotateDir = new int[4];
			rotateDir[num] = dir;

			// 회전하는 바퀴 기준으로 왼쪽에 있는 바퀴들에 전파
			for (int i = num; i > 0; i--) {
				if (gear[i - 1][(top[i - 1] + 2) % 8] != gear[i][(top[i] + 6) % 8]) {
					rotateDir[i - 1] = -rotateDir[i];
				} else {
					break; // 극이 같으면 전파되지 않음 -> stop
				}
			}

			// 회전하는 바퀴 기준으로 오른쪽에 있는 바퀴들에 전파
			for (int i = num; i < 3; i++) {
				if (gear[i][(top[i] + 2) % 8] != gear[i + 1][(top[i + 1] + 6) % 8]) {
					rotateDir[i + 1] = -rotateDir[i];
				} else {
					break; // 극이 같으면 전파되지 않음 -> stop
				}
			}

			// 결정된 방향대로 모든 바퀴 동시 회전
			for (int i = 0; i < 4; i++) {
				if (rotateDir[i] == 1) {
					top[i] = (top[i] - 1 + 8) % 8;
				} else if (rotateDir[i] == -1) {
					top[i] = (top[i] + 1) % 8;
				}
			}
		}

		System.out.println(calcScore());
	}

	private static int calcScore() {
		int sum = 0;
		sum += (gear[0][top[0]] == 0 ? 0 : 1);
		sum += (gear[1][top[1]] == 0 ? 0 : 2);
		sum += (gear[2][top[2]] == 0 ? 0 : 4);
		sum += (gear[3][top[3]] == 0 ? 0 : 8);
		return sum;
	}
}
