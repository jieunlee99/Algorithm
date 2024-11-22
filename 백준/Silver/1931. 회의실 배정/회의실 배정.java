import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N][2];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());

		}

		// 종료시간을 기준으로 정렬
		Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// 종료시간이 같다면 시작시간이 빠른순으로 정렬
				if (o1[1] == o2[1]) {
					return o1[0] - o2[0];
				}
				return o1[1] - o2[1];
			}

		});

		int cnt = 0;
		int lastEnd = 0;

		for (int i = 0; i < N; i++) {
			if(lastEnd <= arr[i][0]) {
				lastEnd = arr[i][1];
				cnt++;
			}
		}

		System.out.println(cnt);

	}

}
