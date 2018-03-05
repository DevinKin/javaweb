CREATE TABLE emp (
	empno INT PRIMARY KEY,
	ename VARCHAR(50)
);

INSERT INTO emp VALUES(1, 'zhangsan');

INSERT INTO emp VALUES(1, 'lisi');

SELECT * FROM emp;

DROP TABLE emp;


CREATE TABLE emp (
	empno INT,
	ename VARCHAR(50)
);

INSERT INTO emp VALUES(1,'lisi');

ALTER TABLE emp ADD PRIMARY KEY(empno);

DESC emp;

ALTER TABLE emp DROP PRIMARY KEY;


CREATE TABLE t_stu (
	sid INT PRIMARY KEY AUTO_INCREMENT,
	sname VARCHAR(20),
	age INT,
	gender VARCHAR(10)
);

INSERT INTO t_stu VALUES(null, 'zhangsan', 20, 'male');

SELECT * FROM t_stu;

DELETE FROM t_stu;


SELECT * FROM emp;

SELECT * FROM dept;

SELECT * FROM emp;

drop table emp;

CREATE TABLE emp (
	empno INT PRIMARY KEY AUTO_INCREMENT,
	ename VARCHAR(50)
);

INSERT INTO emp VALUES(2,'lisi');

CREATE TABLE dept (
	deptno INT PRIMARY KEY AUTO_INCREMENT,
	dname VARCHAR(50)
);

DESC dept;
SELECT * FROM dept;
INSERT INTO dept VALUES(10,'研发部');
INSERT INTO dept VALUES(20,'有爱部');
INSERT INTO dept VALUES(30,'真理部');


DROP TABLE emp;

SHOW TABLES;

CREATE TABLE emp (
	empno INT PRIMARY KEY AUTO_INCREMENT,
	ename VARCHAR(50),
	dno INT,
	CONSTRAINT fk_emp_dept FOREIGN KEY(dno) REFERENCES dept(deptno)
);

INSERT INTO emp(empno,ename) VALUES(1,'zhangsan');
INSERT INTO emp(empno,ename,dno) VALUES(NULL,'lisi',10);
INSERT INTO emp(empno,ename,dno) VALUES(NULL,'wangwu',20);
INSERT INTO emp(empno,ename,dno) VALUES(NULL,'zhaoliu',30);

SELECT * FROM emp;

DESC emp;


CREATE TABLE hasband(
	hid INT PRIMARY KEY AUTO_INCREMENT,
	hname VARCHAR(50)
);

ALTER TABLE hasband RENAME TO husband;

SHOW TABLES;

INSERT INTO husband VALUES(1, '关羽');
INSERT INTO husband VALUES(NULL, '刘备');
INSERT INTO husband VALUES(NULL, '张飞');

SELECT * FROM husband;

DELETE FROM husband;

CREATE TABLE wife (
	wid INT PRIMARY KEY AUTO_INCREMENT,
	wname VARCHAR(50),
	CONSTRAINT fk_wife_husband FOREIGN KEY(wid) REFERENCES husband(hid)
);

DESC wife;

/**
 * wid的要求
 * 1. 非空
 * 2. 唯一
 * 3. 引用hid
 */
INSERT INTO wife VALUES(1,'貂蝉');
INSERT INTO wife VALUES(8,'妲己');
INSERT INTO wife VALUES(9,'闫玉环');

SELECT * FROM wife;


/**
 * 多对多关系
 */

CREATE TABLE student 
(
	sid INT PRIMARY KEY AUTO_INCREMENT,
	sname VARCHAR(50)
);

CREATE TABLE teacher(
	tid INT PRIMARY KEY AUTO_INCREMENT,
	tname VARCHAR(50)
);

CREATE TABLE stu_tea (
	sid INT,
	tid INT,
	CONSTRAINT fk_student FOREIGN KEY(sid) REFERENCES student(sid),
	CONSTRAINT fk_teacher FOREIGN KEY(tid) REFERENCES teacher(tid)
);

INSERT INTO student VALUES(null, '刘德华');
INSERT INTO student VALUES(null, '梁朝伟');
INSERT INTO student VALUES(null, '王日华');
INSERT INTO student VALUES(null, '苗伟桥');
INSERT INTO student VALUES(null, '唐镇');

INSERT INTO teacher VALUES(NULL, '渣渣辉');
INSERT INTO teacher VALUES(NULL, '古天乐');
INSERT INTO teacher VALUES(NULL, '崔老师');


SELECT * FROM student;

SELECT * FROM teacher;

INSERT INTO stu_tea VALUES(1,1);
INSERT INTO stu_tea VALUES(2,1);
INSERT INTO stu_tea VALUES(3,1);
INSERT INTO stu_tea VALUES(4,1);
INSERT INTO stu_tea VALUES(5,1);

INSERT INTO stu_tea VALUES(2,2);
INSERT INTO stu_tea VALUES(3,2);
INSERT INTO stu_tea VALUES(4,2);

INSERT INTO stu_tea VALUES(3,3);
INSERT INTO stu_tea VALUES(4,3);
INSERT INTO stu_tea VALUES(5,3);

SELECT * FROM stu_tea;



/**
 * 合并结果查询
 */

CREATE TABLE ab(a INT, b VARCHAR(50));
INSERT INTO ab VALUES(1, '1');
INSERT INTO ab VALUES(2, '2');
INSERT INTO ab VALUES(3, '3');

CREATE TABLE cd(c INT, d VARCHAR(50));
INSERT INTO cd VALUES(3, '3');
INSERT INTO cd VALUES(4, '4');
INSERT INTO cd VALUES(5, '5');

SELECT * FROM ab
UNION ALL
SELECT * FROM cd;


/**
 * 连接查询
 */

SELECT * FROM emp, dept
WHERE emp.deptno = dept.deptno;

/**
 * 打印所有员工姓名、工资，以及部门名称
 */
SELECT emp.ename 姓名,emp.sal 工资, dept.dname 部门名称 
FROM emp, dept
WHERE emp.deptno = dept.deptno;

SELECT e.ename 姓名,e.sal 工资, d.dname 部门名称 
FROM emp e, dept d 
WHERE e.deptno = d.deptno;

SELECT e.ename 姓名,e.sal 工资, d.dname 部门名称 
FROM emp e INNER JOIN dept d
ON e.deptno = d.deptno;

SELECT e.ename 姓名,e.sal 工资, d.dname 部门名称 
FROM emp e NATURAL JOIN dept d

SELECT * FROM emp;
INSERT INTO emp VALUES(1015, 'zhangsan',null,1006,'2011-12-12',80000,null,null);
/**
 * 外链接
 */
SELECT e.ename, e.sal,IFNULL(d.dname, '无部门') AS dname
FROM emp e LEFT OUTER JOIN dept d
ON e.deptno = d.deptno;

SELECT e.ename,e.sal, d.dname
FROM emp e RIGHT OUTER JOIN dept d
ON e.deptno=d.deptno;

SELECT e.ename,e.sal, d.dname
FROM emp e RIGHT OUTER JOIN dept d
ON e.deptno=d.deptno
UNION
SELECT e.ename, e.sal,IFNULL(d.dname, '无部门') AS dname
FROM emp e LEFT OUTER JOIN dept d
ON e.deptno = d.deptno;


/**
 * 子查询
 */

/*查询本公司工资最高的人的详细信息*/

SELECT * FROM emp WHERE sal=(SELECT MAX(sal) FROM emp);


SELECT e.empno, e.ename
FROM (SELECT * FROM emp WHERE deptno=30) e;

/*打印高于平均工资的所有人*/
SELECT * FROM emp WHERE sal > (SELECT AVG(sal) FROM emp);

/*多行单列*/
/*打印大于30部门所有人工资的员工信息*/
SELECT * FROM emp WHERE sal > ALL(SELECT sal FROM emp WHERE deptno=30);

SELECT * FROM emp WHERE sal > ANY(SELECT sal FROM emp WHERE job='经理');


/*单行多列*/
/*查询工资和部门与*/
SELECT * FROM emp WHERE (job, deptno,sal) IN (SELECT job,deptno,sal FROM emp WHERE ename='殷天正');

SHOW TABLES;
SELECT * FROM stu;
DESC stu;

CREATE TABLE t_user(
	username VARCHAR(50),
	`password` VARCHAR(50)
);

SHOW TABLES;

INSERT INTO t_user VALUES('zhangsan', '123');
INSERT INTO t_user VALUES('lisi', '123');
INSERT INTO t_user VALUES('wangwu', '123');