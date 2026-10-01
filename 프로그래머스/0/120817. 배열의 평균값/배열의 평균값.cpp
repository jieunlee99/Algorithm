#include <string>
#include <vector>

using namespace std;

double solution(vector<int> numbers) {
    double sum = 0;
    
    for(int number:numbers) {
        sum += number;
    }
    
    return sum / numbers.size();
}