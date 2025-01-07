function solution(my_string) {
    return my_string.split('').map((x) => x.toLowerCase() === x ? x.toUpperCase() : x.toLowerCase()).join('')
}