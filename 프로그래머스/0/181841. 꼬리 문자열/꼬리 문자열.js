function solution(str_list, ex) {
    return str_list.filter((x)=> !x.includes(ex)).join('')
}