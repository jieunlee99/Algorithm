import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 첫 번째 줄 입력: 플레이어 수(p), 방 최대 인원(m)
		StringTokenizer st = new StringTokenizer(br.readLine());
		int p = Integer.parseInt(st.nextToken()); // 플레이어 수
		int m = Integer.parseInt(st.nextToken()); // 방 최대 인원

		Player[] players = new Player[p]; // 플레이어 정보를 저장할 배열

		// 플레이어 입력 받기
		for (int i = 0; i < p; i++) {
			st = new StringTokenizer(br.readLine());
			int level = Integer.parseInt(st.nextToken()); // 플레이어 레벨
			String nickname = st.nextToken(); // 플레이어 닉네임
			players[i] = new Player(level, nickname);
		}

		// 방을 생성하고 플레이어들을 배정하는 과정
		for (int i = 0; i < p; i++) {
			ArrayList<Player> room = new ArrayList<>(); // 새로운 방 생성

			// 아직 방에 배정되지 않은 플레이어만 처리
			if (!players[i].check) {
				// i번째 플레이어를 기준으로 적절한 방 찾기
				for (int j = i; j < p; j++) {
					// 방이 가득 차면 추가 중단
					if (room.size() == m) {
						break;
					}

					int level = players[j].level;
					String nickname = players[j].nickname;

					// 아직 배정되지 않았고, 레벨 차이가 10 이내인 경우 추가
					if (!players[j].check && players[i].level - 10 <= level && level <= players[i].level + 10) {
						players[j].check = true; // 배정 완료 표시
						room.add(new Player(level, nickname)); // 방에 추가
					}
				}

				// 닉네임 기준으로 정렬 (사전순)
				Collections.sort(room);

				// 방의 상태 출력
				if (room.size() == m) {
					sb.append("Started!").append("\n"); // 방이 꽉 찼다면 "Started" 출력
				} else {
					sb.append("Waiting!").append("\n"); // 대기 중이라면 "Waiting" 출력
				}

				// 방에 있는 플레이어 정보 출력
				for (Player player : room) {
					sb.append(player.level).append(" ").append(player.nickname).append("\n");
				}
			}
		}

		// 최종 결과 출력
		System.out.print(sb);
	}
}

// 플레이어 정보를 저장하는 클래스
class Player implements Comparable<Player> {
	int level; // 플레이어 레벨
	String nickname; // 플레이어 닉네임
	boolean check; // 방에 배정되었는지 여부 (기본값 false)

	public Player(int level, String nickname) {
		this.level = level;
		this.nickname = nickname;
	}

	// 닉네임 기준으로 사전순 정렬을 위한 compareTo 메서드
	@Override
	public int compareTo(Player o) {
		return nickname.compareTo(o.nickname);
	}
}
