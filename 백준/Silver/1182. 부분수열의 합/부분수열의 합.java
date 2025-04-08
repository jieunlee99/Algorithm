
import java.io.*;
import java.util.*;

public class Main {

	static int N, S;
	static int cnt;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0, 0);

		// 0일 때 공집합 제외
		System.out.println(S == 0 ? cnt - 1 : cnt);
	}

	static void dfs(int index, int sum) {
		if (index == N) {
			if (sum == S) {
				cnt++;
			}
			return;
		}

		dfs(index + 1, sum + arr[index]); // 원소를 선택했을 때
		dfs(index + 1, sum); // 원소를 선택하지 않았을 때
	}

}
