function solution(my_string, n) {
    let len = my_string.length;
    return my_string.slice(len-n, len)
}