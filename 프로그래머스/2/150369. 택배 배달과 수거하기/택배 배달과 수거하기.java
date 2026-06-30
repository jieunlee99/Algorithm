class Solution {
    // cap: 트럭에 실을 수 있는 최대 상자 개수
    // n: 집 개수

    // 각 집마다 배달할 상자 개수(deliveries)와 수거할 상자 개수(pickups)를 알고 있을 때
    // 트럭 하나로 모든 배달과 수거를 마치고 물류창고까지 돌아올 수 있는 최소 이동거리
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        // 현재까지 처리 가능한 배달/수거 개수
        int delivery = 0;
        int pickup = 0;
        
        // 무조건 한 번에 cap만큼 배달하고 + 가장 먼 집부터 처리 
        
        for(int i=n-1; i>=0; i--) {
            
            // i번째 집에 배달/수거할게 남아있으면 ㄱㄱ
            while(deliveries[i] > delivery || pickups[i] > pickup) {
                
                delivery += cap;
                pickup += cap;
                
                answer += (i+1) * 2L; // 왕복
            }
            
            // 현재 집 택배 처리
            delivery -= deliveries[i];
            pickup -= pickups[i];
        }
        
        return answer;
    }
}