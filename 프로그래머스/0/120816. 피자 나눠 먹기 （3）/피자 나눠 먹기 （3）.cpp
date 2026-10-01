#include <string>
#include <vector>
#include <math.h>

using namespace std;

int solution(int slice, int n) {
    return (int) ceil((float)n/slice);
}