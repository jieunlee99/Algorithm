#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

int solution(vector<vector<string>> clothes) {
    int answer = 1;
    
    unordered_map<string, int> map;
    
    for(const auto& item: clothes) {
        map[item[1]]++;
    }
    
    for(const auto& entry:map) {
        answer *= (entry.second+1);
    }
    
    return answer-1;
}