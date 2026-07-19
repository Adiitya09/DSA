# Write your MySQL query statement below
# Write your MySQL query statement below
with Course as (
     select num , count(num) as count 
    from MyNumbers group by num
)
select Max(num) as num from Course
Where count <=1;