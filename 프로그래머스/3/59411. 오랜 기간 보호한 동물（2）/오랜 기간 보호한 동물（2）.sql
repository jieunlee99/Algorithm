-- 코드를 입력하세요
SELECT o.animal_id as animal_id, o.name as name from animal_outs as o
left join animal_ins as i
on o.animal_id = i.animal_id
order by o.datetime - i.datetime desc
limit 2