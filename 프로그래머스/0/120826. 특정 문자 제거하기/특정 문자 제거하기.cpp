#include <string>
#include <vector>

using namespace std;

string solution(string my_string, string letter) {
    while((my_string.find(letter)) != string::npos) { 
        my_string.replace(my_string.find(letter), letter.size(), "");
    }
    
    return my_string;
}