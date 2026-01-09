import java.util.*;

// 트럭은 1초에 다리 길이 1씩 전진한다.
// 트럭은 올라갈수 있으면 1초에 한 대씩 다리에 올라갈 수 있다.

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        Queue<Integer> bridge = new LinkedList<>();
        
        // 다리 길이만큼 0 삽입
        for(int i=0; i<bridge_length; i++) {
            bridge.offer(0);
        }
        
        int time = 0; 
        int index = 0; // 현재 다리 위에 있는 트럭들의 무게 합
        int curWeight = 0; // 대기 중인 트럭의 인덱스
        
        while(index < truck_weights.length) {
            time++;
            
            // 다리의 맨 앞에서 트럭(or 0)이 나감
            curWeight -= bridge.poll();
            
            // 다음 트럭이 다리에 올라올 수 있는지 확인
            if(curWeight + truck_weights[index] <= weight) {
                bridge.offer(truck_weights[index]);
                curWeight += truck_weights[index];
                index++;
            }
            
            // 무게 초과 시 빈공간(0)을 넣어 트럭을 앞으로 밀어줌
            else {
                bridge.offer(0);
            }
        }
        // 마지막 트럭이 다리에 올라오는 순간 반복문이 종료되기 때문에
        // 마지막 트럭이 빠져나가는 시간(다리 길이)을 더해줘야 함
        return time + bridge_length;
    }
}