import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 입력

        for (int testCase = 0; testCase < T; testCase++) {
            int N = Integer.parseInt(br.readLine()); // 선수 수 입력
            int[] rank = new int[N]; // 선수들의 팀 번호를 저장할 배열
            Map<Integer, Integer> result = new HashMap<>(); // 각 팀의 선수 수를 저장하는 맵

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int data = Integer.parseInt(st.nextToken()); // 현재 선수의 팀 번호
                result.put(data, result.getOrDefault(data, 0) + 1); // 해당 팀의 선수 수 증가
                rank[i] = data; // 선수의 팀 정보 저장
            }

            // 다섯 번째 주자의 골 순위를 저장하는 배열 (팀 번호를 인덱스로 사용)
            int[] fifth = new int[result.size() + 1];

            // 각 팀의 점수를 저장하는 맵
            Map<Integer, Integer> scoreMap = new HashMap<>();

            // 각 팀별로 몇 명의 선수를 확인했는지 저장하는 맵
            Map<Integer, Integer> tempMap = new HashMap<>();

            int score = 1; // 완주 순위 (1부터 시작)

            for (int element : rank) { // 선수들이 완주한 순서대로 순회
                if (result.get(element) >= 6) { // 6명 이상 완주한 팀만 고려
                    tempMap.put(element, tempMap.getOrDefault(element, 0) + 1); // 현재 팀의 완주 선수 수 증가

                    if (tempMap.get(element) <= 4) { // 첫 4명의 선수까지 점수 계산
                        scoreMap.put(element, scoreMap.getOrDefault(element, 0) + score);
                    }

                    if (tempMap.get(element) == 5) { // 다섯 번째 완주자의 골 순위 저장
                        fifth[element] = score;
                    }
                    score++; // 순위 증가
                }
            }

            // 점수를 비교할 팀 리스트 생성
            List<Integer> keyData = new ArrayList<>(scoreMap.keySet());

            // 정렬 기준:
            // 1. 총점이 낮은 팀이 우승
            // 2. 총점이 같다면 다섯 번째 선수의 완주 순위가 더 빠른 팀이 우승
            keyData.sort((x, y) -> {
                if (Objects.equals(scoreMap.get(x), scoreMap.get(y))) {
                    return fifth[x] - fifth[y]; // 다섯 번째 선수의 순위 비교
                } else {
                    return scoreMap.get(x) - scoreMap.get(y); // 총점 비교
                }
            });

            System.out.println(keyData.get(0)); // 우승 팀 출력
        }
    }
}
