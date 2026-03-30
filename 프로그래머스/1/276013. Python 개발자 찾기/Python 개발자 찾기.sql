-- 코드를 작성해주세요
select id, email, first_name, last_name from developer_infos
where skill_1 = "Python" OR skill_2 = "Python" OR skill_3 = "Python"
order by id asc;