#include <string>
#include <vector>
#include <cmath>

using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;
    
    int currentDay = ceil((100 - progresses[0]) / (float)speeds[0]);
    int count = 1;
    
    for(int i = 1; i < progresses.size(); i++) {
        
        int day = ceil((100 - progresses[i]) / (float)speeds[i]);
        
        if(day <= currentDay) {
            // 앞 기능을 기다렸다 같이 배포
            count++;
        }
        else {
            // 새로운 배포 시작
            answer.push_back(count);
            
            currentDay = day;
            count = 1;
        }
    }
    
    answer.push_back(count);
    
    return answer;
}