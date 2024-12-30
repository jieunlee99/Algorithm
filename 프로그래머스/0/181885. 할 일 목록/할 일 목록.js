function solution(todo_list, finished) {
    return todo_list.filter((x, i)=> !finished[i])
}