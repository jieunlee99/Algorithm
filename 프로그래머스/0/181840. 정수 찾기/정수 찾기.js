function solution(num_list, n) {
    return num_list.filter((x) => x === n).length > 0 ? 1 : 0;
}