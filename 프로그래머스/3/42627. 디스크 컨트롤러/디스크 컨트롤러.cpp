#include <string>
#include <vector>
#include <queue> 
#include <algorithm>

using namespace std;

// <요청시간, 소요시간> 
bool cmp(vector<int> a, vector<int> b) {
    return a[0] < b[0];
}

int solution(vector<vector<int>> jobs) {
    int answer = 0;
    
    priority_queue<vector<int>, 
    vector<vector<int>>, 
    greater<vector<int>>> pq;
    
    // 요청시간이 빠른 순으로 정렬
    sort(jobs.begin(), jobs.end(), cmp);
    
    int time = 0;
    int index = 0;
    int total = 0;
    
    while(index < jobs.size() || !pq.empty()) {
        
        while(index < jobs.size() && jobs[index][0] <= time) {
            pq.push({jobs[index][1], jobs[index][0]});  
            index++;
        }
        
        if(!pq.empty()) {
            vector<int> cur = pq.top();
            pq.pop();
            
            int workTime = cur[0];
            int requestTime = cur[1];
            
            time += workTime;
            
            total += time - requestTime;
        } 
        
        else {
            time = jobs[index][0];
        }
    }
    
    // return 평균 시간
    return total / jobs.size();
}