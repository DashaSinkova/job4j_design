SELECT * FROM students;

SELECT name, course, speciality FROM students WHERE university_id = 1;

SELECT * FROM students WHERE enroll_date < '2019-09-02' ORDER BY enroll_date ASC; 

select distinct name from students WHERE name LIKE 'A%';

SELECT * FROM STUDENTS WHERE COURSE = 3 AND enroll_date >'2016-09-01';
SELECT * FROM STUDENTS WHERE (COURSE = 3 OR enroll_date >'2016-09-01') AND budget = true;

select name, course, speciality from students limit 5; 