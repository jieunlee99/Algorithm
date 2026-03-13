class Solution {
    public long[] solution(long[] numbers) {
        int n = numbers.length;
        
        long[] answer = new long[n];
        
        for(int i=0; i<n; i++) {
            // 짝수 => 0으로 끝나는걸 1로만 변경해주면 됨 => numbers[i] + 1
            if(numbers[i]%2 == 0) {
                answer[i] = numbers[i] + 1;
            } 
            
            // 홀수 => 가장 오른쪽에 있는 0을 찾아 1로 바꿔줌 => 하나 더 변경 가능하기 때문에 그 0의 옆자리를 1로 바꿔서 비교
            else {
                long lastZero = ~numbers[i] & (numbers[i] + 1);
                answer[i] = (numbers[i] | lastZero) & ~(lastZero >> 1);
            }
        }
        
        return answer;
    }
}