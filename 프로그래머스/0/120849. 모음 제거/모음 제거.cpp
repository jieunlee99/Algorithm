#include <string>
#include <vector>

using namespace std;

string solution(string my_string) {
    while(my_string.find("a") != string::npos) {
        my_string.replace(my_string.find("a"), 1, "");
    }

    while(my_string.find("e") != string::npos) {
        my_string.replace(my_string.find("e"), 1, "");
    }

    while(my_string.find("i") != string::npos) {
        my_string.replace(my_string.find("i"), 1, "");
    }

    while(my_string.find("o") != string::npos) {
        my_string.replace(my_string.find("o"), 1, "");
    }

    while(my_string.find("u") != string::npos) {
        my_string.replace(my_string.find("u"), 1, "");
    }
    
    return my_string;
}