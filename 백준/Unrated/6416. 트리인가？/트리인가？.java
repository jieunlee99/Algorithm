import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// <접점, 진입 간선의 개수>를 담는
		Map<Integer, Integer> map;

		int t = 1; // t번째 테스트 케이스

		while (true) {

			map = new HashMap<>();

			int edge = 0; // 간선의 개수

			while (true) {
				int u = scanner.nextInt();
				int v = scanner.nextInt();

				// 하나의 테스트 케이스 종료
				if (u == 0 && v == 0) {
					break;
				}

				// 프로그램 종료
				if (u == -1 && v == -1) {
					return;
				}

				// u: 출발, v: 도착
				map.put(u, map.getOrDefault(u, 0));
				map.put(v, map.getOrDefault(v, 0) + 1);

				edge++;
			}

			int root = 0; // 루트의 개수 (1개여야 함)

			boolean isTree = true;

			Iterator<Integer> key = map.keySet().iterator();
			while (key.hasNext()) {
				int num = key.next();

				if (map.get(num) == 0) {
					root++;
				} else if (map.get(num) > 1) {
					isTree = false;
					break;
				}
			}

			if (map.size() == 0 || (root == 1 && isTree && map.size() - 1 == edge)) {
				bw.write("Case " + t + " is a tree.\n");
			} else {
				bw.write("Case " + t + " is not a tree.\n");
			}

			t++;

			bw.flush();

		}

	}

}
