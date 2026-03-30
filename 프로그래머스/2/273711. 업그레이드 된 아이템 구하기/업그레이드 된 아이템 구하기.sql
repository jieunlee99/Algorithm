-- 코드를 작성해주세요
select item_info.item_id as item_id, item_name, rarity 
from item_info inner join item_tree on item_info.item_id = item_tree.item_id
where parent_item_id in (
    select item_id from item_info
    where rarity = 'RARE'
)
order by item_id desc