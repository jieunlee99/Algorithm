
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 집합,맵 -> 이름(문자열)을 id(숫자)로 만들어야 함
// 유니온 파인드 -> root가 같은 사람이 몇명인지 체크 (networkSize)

public class Main {

	static int[] parent; // union-find
	static int[] networkSize; // 각 루트가 대표하는 집합의 크기
	static int ID; // 사람에게 부여할 고유 번호 카운터
	static Map<String, Integer> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 수

		while (T-- > 0) {
			int F = Integer.parseInt(br.readLine()); // 친구 관계 수

			init(2 * F); // 한 관계 당 두명이 연관되어 있기 때문

			for (int i = 0; i < F; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				String name1 = st.nextToken();
				String name2 = st.nextToken();

				int id1 = getOrCreateId(name1);
				int id2 = getOrCreateId(name2);

				sb.append(union(id1, id2)).append("\n");
			}
		}

		System.out.println(sb);
	}

	static void init(int maxNodes) {
		parent = new int[maxNodes];
		networkSize = new int[maxNodes];

		// 처음에는 자기 자신이 부모이며, 집합의 크기는 1이다.
		for (int i = 0; i < maxNodes; i++) {
			parent[i] = i;
			networkSize[i] = 1;
		}

		// 고유번호 카운터 초기화
		ID = 0;

		// map 초기화
		map = new HashMap<>();
	}

	static int find(int num) {
		if (parent[num] == num) {
			return num;
		}
		return parent[num] = find(parent[num]);
	}

	// union한 뒤의 네트워크 크기를 반환함
	static int union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot != bRoot) {
			parent[aRoot] = bRoot; // Union
			networkSize[bRoot] += networkSize[aRoot]; // 합쳐진 만큼 크기를 더해줌
		}

		return networkSize[bRoot];
	}

	// 이미 map에 있는 사람이라면 ID 반환, 없는 사람이면 ID 생성 후 반환
	static int getOrCreateId(String name) {
		if (map.containsKey(name)) {
			return map.get(name);
		}

		map.put(name, ID);
		return ID++;
	}
}
