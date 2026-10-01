#include <string>
#include <vector>

using namespace std;

int solution(string my_string) {
    int answer = 0;
    
    for(int i=0; i<my_string.size(); i++) {
        char c = my_string.at(i);
        if(c >= '0' && c <= '9') {
            answer += c - '0';
        }
    }
    
    return answer;
}