-- 查询选课的学生
SELECT
	t3.student_no,
	t3.`name`,
	t3.age,
	t4.`name`
FROM
	(
		SELECT
			t2.student_no,
			t2.`name`,
			t2.age,
			t1.course_id
		FROM
			(
				SELECT
					student_id,
					course_id
				FROM
					t_student_course
				WHERE
					course_id IN (
						SELECT
							id
						FROM
							t_course
						WHERE
							teacher_id = '2'
					)
			) t1
		JOIN t_student t2 ON t1.student_id = t2.id
	) t3
JOIN t_course t4 ON t3.course_id = t4.id