-- 코드를 입력하세요
SELECT user_id, nickname, 
concat(city,' ', street_address1,' ', street_address2) as 전체주소, 
concat(substring(tlno, 1, 3), '-', substring(tlno, 4, 4), '-', substring(tlno, 8, 4)) as 전화번호
from used_goods_board left join used_goods_user on writer_id = user_id
group by user_id
having count(user_id) >= 3
order by user_id desc