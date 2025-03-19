import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int K = Integer.parseInt(br.readLine());

		int hMax = 0, wMax = 0;
		int hMaxIdx = -1, wMaxIdx = -1;

		int[] dirs = new int[6];
		int[] dist = new int[6];

		for (int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine());
			dirs[i] = Integer.parseInt(st.nextToken());
			dist[i] = Integer.parseInt(st.nextToken());

			if (dirs[i] == 1 || dirs[i] == 2) {
				if (hMax < dist[i]) {
					hMax = dist[i];
					hMaxIdx = i;
				}
			} else {
				if (wMax < dist[i]) {
					wMax = dist[i];
					wMaxIdx = i;
				}
			}
		}

		int maxArea = wMax * hMax;
		int minArea = dist[(wMaxIdx + 3) % 6] * dist[(hMaxIdx + 3) % 6];

		System.out.println((maxArea - minArea) * K);

	}

}
