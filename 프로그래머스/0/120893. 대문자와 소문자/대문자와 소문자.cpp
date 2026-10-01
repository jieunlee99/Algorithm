#include <string>
#include <vector>

using namespace std;

string solution(string my_string) {
    string answer = "";
    
    for(int i=0; i<my_string.size(); i++) {
        char c = my_string.at(i);
        
        if(c >= 'a'&& c <= 'z') {
            answer.push_back(c + 'A'-'a');
        } else if(c >= 'A' && c <= 'Z') {
            answer.push_back(c + 'a'-'A');
        }
    }
    
    return answer;
}