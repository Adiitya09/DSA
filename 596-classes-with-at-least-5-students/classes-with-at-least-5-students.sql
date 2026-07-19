# Write your MySQL query statement below
with Course as (
     select class , count(class) as count 
    from Courses group by class
)
select class from Course
Where count >=5;