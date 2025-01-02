function solution(my_string, index_list) {
    return index_list.map((x)=>my_string.charAt(x)).join('')
}