import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 과목 수
		int m = Integer.parseInt(st.nextToken()); // 주어진 마일리지

		int[] need = new int[n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			if (p >= l) {
				int[] mile = new int[p];
				for (int j = 0; j < p; j++) {
					mile[j] = Integer.parseInt(st.nextToken());
				}
				Arrays.sort(mile);
				need[i] = mile[p - l];
			} else {
				need[i] = 1;
			}
		}
		
		Arrays.sort(need);
		
		int cnt = 0;
		for(int i=0; i<n; i++) {
			if(need[i] > m) {
				break;
			}
			m -= need[i];
			cnt++;
		}
		
		System.out.println(cnt);
	}

}
