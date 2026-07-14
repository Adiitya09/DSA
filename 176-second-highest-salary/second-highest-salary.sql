# Write your MySQL query statement below
select (select Distinct(salary)   
            from Employee 
            order by salary Desc limit 1 offset 1)  
            As SecondHighestSalary ;