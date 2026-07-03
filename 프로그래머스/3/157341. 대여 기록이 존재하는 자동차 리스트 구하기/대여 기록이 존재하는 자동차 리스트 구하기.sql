-- 코드를 입력하세요
SELECT distinct car_rental_company_car.car_id as car_id from car_rental_company_rental_history
inner join car_rental_company_car on car_rental_company_rental_history.car_id = car_rental_company_car.car_id
where car_type = '세단' && month(start_date) ='10'
order by car_id desc