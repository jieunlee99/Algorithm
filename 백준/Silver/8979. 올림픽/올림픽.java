import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 국가 수
		int K = Integer.parseInt(st.nextToken()); // 등수를 알고 싶은 국가 번호

		List<Nation> nations = new ArrayList<>();
		Nation targetNation = null;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int gold = Integer.parseInt(st.nextToken());
			int silver = Integer.parseInt(st.nextToken());
			int bronze = Integer.parseInt(st.nextToken());

			Nation nation = new Nation(num, gold, silver, bronze);
			nations.add(nation);

			if (num == K) { // 등수를 알고 싶은 국가 저장
				targetNation = nation;
			}
		}

		// 국가별 정렬 (금 > 은 > 동 내림차순)
		Collections.sort(nations);

		// 등수 계산
		int rank = 1;
		int sameRankCount = 1; // 같은 등수 국가 개수
		for (int i = 0; i < N; i++) {
			if (i > 0 && nations.get(i).compareTo(nations.get(i - 1)) != 0) {
				rank += sameRankCount;
				sameRankCount = 1;
			} else if (i > 0) {
				sameRankCount++;
			}

			if (nations.get(i).num == K) {
				System.out.println(rank);
				break;
			}
		}
	}
}

class Nation implements Comparable<Nation> {
	int num;
	int gold;
	int silver;
	int bronze;

	public Nation(int num, int gold, int silver, int bronze) {
		this.num = num;
		this.gold = gold;
		this.silver = silver;
		this.bronze = bronze;
	}

	@Override
	public int compareTo(Nation o) {
		if (this.gold != o.gold)
			return o.gold - this.gold; // 금메달 우선순위
		if (this.silver != o.silver)
			return o.silver - this.silver; // 은메달 우선순위
		return o.bronze - this.bronze; // 동메달 우선순위
	}
}