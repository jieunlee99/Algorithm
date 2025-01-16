function solution(arr, delete_list) {
    return arr.filter((x) => !delete_list.includes(x))
}