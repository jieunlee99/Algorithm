import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();

        int n = progresses.length;
        int[] arr = new int[n];

        // 각 기능이 완료되기까지 필요한 날짜
        for (int i = 0; i < n; i++) {
            arr[i] = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
        }

        int current = arr[0];
        int cnt = 1;

        for (int i = 1; i < n; i++) {
            int next = arr[i];

            // 앞 기능보다 먼저 또는 같은 날 끝나면 같이 배포
            if (current >= next) {
                cnt++;
            } else {
                list.add(cnt);
                cnt = 1;
                current = next;
            }
        }

        // 마지막 배포 그룹
        list.add(cnt);

        return list.stream().mapToInt(i -> i).toArray();
    }
}