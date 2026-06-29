class Solution {
    
    int n;
    int[] info;
    
    int maxDiff = 0;
    int[] best = {-1}; // 라이언이 절대 이기지 못할 때는 {-1} return
    
    public int[] solution(int n, int[] info) {
        
        this.n = n;
        this.info = info;
        
        int[] lion = new int[11];
        
        dfs(0, n, lion);
        
        return best;
    }
    
    public void dfs(int idx, int remain, int[] lion) {
        
        if(idx == 11) {
            if(remain == 0) {
                int diff = calcScore(info, lion);
                
                if(diff > 0) {
                    if(diff > maxDiff) {
                        maxDiff = diff;
                        best = lion.clone();
                    } else if(diff == maxDiff && hasMoreSmallScore(lion, best)) {
                        best = lion.clone();
                    }
                }
            }
            
            return;
        }
        
        // 현재 점수에 0~remain개의 화살 쏘기
        for(int i=0; i<=remain; i++) {
            lion[idx] = i;
            dfs(idx+1, remain-i, lion);
            lion[idx] = 0;
        }
    }
    
    public boolean hasMoreSmallScore(int[] current, int[] best) {
        if(best.length == 1 && best[0] == -1) {
            return true;
        }
        
        for(int i=10; i>=0; i--) {
            if(current[i] > best[i]) {
                return true;
            } else if(current[i] < best[i]) {
                return false;
            }
        }
        
        return false;
    }
    
    
    public int calcScore(int[] a, int[] b) {
        int apeach = 0;
        int lion = 0;
        
        for(int i=0; i<=10; i++) {
            
            if(a[i] == 0 && b[i]==0) {
                continue;
            }
            
            if(a[i] < b[i]) {
                lion += (10-i);
            } else {
                apeach += (10-i);
            }
        }
        
        return lion - apeach;
    }
}