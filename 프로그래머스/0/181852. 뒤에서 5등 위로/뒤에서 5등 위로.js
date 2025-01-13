function solution(num_list) {
    const len = num_list.length;
    return num_list.sort((a, b)=> a-b).slice(5, len);
}