class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;

        // 1사분면에서만 개수를 셈
        for (long x = 1; x <= r2; x++) {
            // 외부 원의 최대 y값
            long maxY = (long) Math.floor(Math.sqrt((long) r2 * r2 - x * x));
        
            // 내부 원의 최소 y값
            long minY = 0;
            if (x < r1) { // x가 r1보다 작을 때만 계산
                minY = (long) Math.ceil(Math.sqrt((long) r1 * r1 - x * x));
            }

            answer += (maxY - minY + 1);
        }

        // 4사분면 대칭이므로 4를 곱하여 반환
        return answer * 4;
    }
}