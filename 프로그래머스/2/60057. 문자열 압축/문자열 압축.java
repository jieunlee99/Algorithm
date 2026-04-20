class Solution {
    public int solution(String s) {
        if (s.length() == 1) return 1; // 길이가 1인 경우 예외 처리

        int minLen = s.length();

        for (int i = 1; i <= s.length() / 2; i++) { // 자르는 길이 설정
            minLen = Math.min(minLen, getCompressedLength(s, i));
        }

        return minLen;
    }

    private int getCompressedLength(String s, int size) {
        StringBuilder sb = new StringBuilder();
        
        String target = s.substring(0, size);
        int count = 1;

        for (int j = size; j <= s.length(); j += size) {
            // 비교할 다음 블록의 끝 인덱스 계산 (범위 초과 방지)
            int end = Math.min(j + size, s.length());
            String next = s.substring(j, end);

            if (target.equals(next)) {
                count++;
            } else {
                // 압축 문자열 형성
                sb.append(count > 1 ? count : "").append(target);
                
                // 다음 타겟 교체
                target = next; 
                count = 1;
            }
        }

        // 마지막에 남은 문자열 처리
        sb.append(count > 1 ? count : "").append(target);
        return sb.length();
    }
}