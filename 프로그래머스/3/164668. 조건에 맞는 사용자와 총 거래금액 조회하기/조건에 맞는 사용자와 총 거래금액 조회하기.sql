select user_id, nickname, sum(price) as total_sales
from used_goods_board left join used_goods_user on writer_id = user_id
where status = 'DONE'
group by user_id
having sum(price) >= 700000
order by total_sales