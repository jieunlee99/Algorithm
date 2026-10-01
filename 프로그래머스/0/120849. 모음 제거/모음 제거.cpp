#include <string>
#include <vector>

using namespace std;

string solution(string my_string) {
    string answer = "";
    for(int i=0; i<my_string.size(); i++) {
        char c = my_string.at(i);
        if(c != 'a' && c != 'e' && 
           c != 'i' && c != 'o' && 
           c != 'u') {
            answer.push_back(c);
        }
    }
    return answer;
}