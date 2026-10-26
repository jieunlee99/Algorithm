import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {

        Map<String, Integer> inMap = new HashMap<>(); // 현재 주차장 상황 저장
        Map<String, Integer> totalMap = new TreeMap<>(); // 누적 시간 저장

        for (String record : records) {
            String[] arr = record.split(" ");

            String car = arr[1];
            int time = convertTimeToMin(arr[0]);

            if (arr[2].equals("IN")) {
                // 입차 시간 저장
                inMap.put(car, time);

            } else if (arr[2].equals("OUT")) {
                // 주차 시간 계산
                int inTime = inMap.get(car);
                int parkingTime = time - inTime;

                // 차량별 누적 시간 저장
                totalMap.put(car,
                    totalMap.getOrDefault(car, 0) + parkingTime);

                // 출차 처리
                inMap.remove(car);
            }
        }

        // 출차 기록이 없는 차량 처리
        int endTime = convertTimeToMin("23:59");

        for (String car : inMap.keySet()) {
            int parkingTime = endTime - inMap.get(car);

            totalMap.put(car,
                totalMap.getOrDefault(car, 0) + parkingTime);
        }

        // 차량번호 순서대로 요금 계산
        int[] answer = new int[totalMap.size()];
        int idx = 0;

        for (int time : totalMap.values()) {
            answer[idx++] = calcFee(fees, time);
        }

        return answer;
    }

    // 시각을 분으로 변환
    public int convertTimeToMin(String time) {
        String[] arr = time.split(":");

        return Integer.parseInt(arr[0]) * 60
             + Integer.parseInt(arr[1]);
    }

    // 주차 요금 계산
    public int calcFee(int[] fees, int time) {

        if (time <= fees[0]) {
            return fees[1];
        }

        return fees[1]
            + (int) Math.ceil((time - fees[0]) / (double) fees[2])
            * fees[3];
    }
}