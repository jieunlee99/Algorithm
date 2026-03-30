-- 코드를 입력하세요
SELECT count(user_id) as users 
from user_info
where YEAR(joined)=2021 and age >= 20 and age <=29;