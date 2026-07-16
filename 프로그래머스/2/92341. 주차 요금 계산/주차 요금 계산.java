import java.util.*;

class Solution {

    // fees = [기본 시간, 기본 요금, 단위 시간, 단위 요금]
    // records[i] = "시각 차량번호 내역"
    public int[] solution(int[] fees, String[] records) {

        // 현재 입차 중인 차량의 입차 시간
        Map<Integer, Integer> inTimeMap = new HashMap<>();

        // 차량별 누적 주차 시간
        // TreeMap을 사용하여 차량 번호 오름차순 유지
        Map<Integer, Integer> totalTimeMap = new TreeMap<>();

        for (String record : records) {
            String[] arr = record.split(" ");

            int time = hourToMin(arr[0]);
            int carNum = Integer.parseInt(arr[1]);
            String status = arr[2];

            if (status.equals("IN")) {
                // 입차 시간 저장
                inTimeMap.put(carNum, time);

                // 처음 등장한 차량이라면 누적 시간을 0으로 등록
                totalTimeMap.putIfAbsent(carNum, 0);

            } else {
                // 입차 시간 가져오기
                int inTime = inTimeMap.get(carNum);

                // 기존 누적 시간에 이번 주차 시간 추가
                totalTimeMap.put(
                    carNum,
                    totalTimeMap.get(carNum) + time - inTime
                );

                // 출차했으므로 입차 목록에서 제거
                inTimeMap.remove(carNum);
            }
        }

        // 23:59까지 출차하지 않은 차량 처리
        int lastTime = hourToMin("23:59");

        for (int carNum : inTimeMap.keySet()) {
            int parkingTime = lastTime - inTimeMap.get(carNum);

            totalTimeMap.put(
                carNum,
                totalTimeMap.get(carNum) + parkingTime
            );
        }

        // 차량 번호 오름차순으로 요금 계산
        int[] answer = new int[totalTimeMap.size()];
        int index = 0;

        for (int totalTime : totalTimeMap.values()) {
            answer[index++] = calcFee(fees, totalTime);
        }

        return answer;
    }

    // "HH:MM" 형식의 시간을 분으로 변환
    private int hourToMin(String time) {
        String[] temp = time.split(":");

        int hour = Integer.parseInt(temp[0]);
        int minute = Integer.parseInt(temp[1]);

        return hour * 60 + minute;
    }

    private int calcFee(int[] fees, int totalTime) {
        int baseTime = fees[0];
        int baseFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        // 기본 시간 이하라면 기본 요금
        if (totalTime <= baseTime) {
            return baseFee;
        }

        int extraTime = totalTime - baseTime;

        // 올림 처리: ceil(extraTime / unitTime)
        int unitCount = (extraTime + unitTime - 1) / unitTime;

        return baseFee + unitCount * unitFee;
    }
}