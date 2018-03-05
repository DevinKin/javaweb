/*
1. 查出至少有一个员工的部门。显示部门编号、部门名称、部门位置、部门人数。
3. 列出所有员工的姓名及其直接上级的姓名。
4. 列出受雇日期早于直接上级的所有员工的编号、姓名、部门名称。
5. 列出部门名称和这些部门的员工信息，同时列出那些没有员工的部门。
7. 列出最低薪金大于15000的各种工作及从事此工作的员工人数。
8. 列出在销售部工作的员工的姓名，假定不知道销售部的部门编号。
9. 列出薪金高于公司平均薪金的所有员工信息，所在部门名称，上级领导，工资等级。
10.列出与庞统从事相同工作的所有员工及部门名称。
11.列出薪金高于在部门30工作的所有员工的薪金的员工姓名和薪金、部门名称。
13.查出年份、利润、年度增长比

2. 列出薪金比关羽高的所有员工。
6. 列出所有文员的姓名及其部门名称，部门的人数。
12.列出每个部门的员工数量、平均工资。
 */

/**
 * 1. 查出至少有一个员工的部门。显示部门编号、部门名称、部门位置、部门人数。
 */
SELECT d.deptno, d.dname, d.loc, n1.人数 部门人数
FROM dept d,(SELECT deptno,COUNT(*) 人数 FROM emp GROUP BY deptno) n1
WHERE d.deptno = n1.deptno;


/**
 * 2. 列出薪金比关羽高的所有员工。
 */
SELECT * 
FROM emp 
WHERE sal > (SELECT sal FROM emp WHERE ename='关羽');


/**
 * 3. 列出所有员工的姓名及其直接上级的姓名。
 */
SELECT e.ename employee, IFNULL(m.ename, 'BOSS') manager
FROM emp e LEFT JOIN emp m
ON e.mgr = m.empno;


/**
 * 4. 列出受雇日期早于直接上级的所有员工的编号、姓名、部门名称。
 */

SELECT * FROM emp;
SELECT e.empno, e.ename, d.dname
FROM emp e, dept d
WHERE e.deptno = d.deptno AND e.empno IN (SELECT e1.empno FROM emp e1, emp e2
WHERE e1.mgr = e2.empno AND e1.hiredate < e2.hiredate);


SELECT e.empno, e.ename, d.dname
FROM emp e LEFT JOIN emp m 
ON e.mgr=m.empno 
LEFT JOIN dept d ON e.deptno=d.deptno
WHERE e.hiredate<m.hiredate;

/**
 * 5. 列出部门名称和这些部门的员工信息，同时列出那些没有员工的部门。
 */

SELECT d.dname,e.ename
FROM dept d RIGHT OUTER JOIN emp e
ON e.deptno=d.deptno;

/**
 * 6. 列出所有文员的姓名及其部门名称，部门的人数。
 */
SELECT  e.ename,d.dname,d2.nums
FROM emp e LEFT JOIN  
dept d ON e.deptno = d.deptno
LEFT JOIN (SELECT deptno,COUNT(*) nums FROM emp GROUP BY deptno) AS d2
ON d.deptno = d2.deptno
WHERE e.job = '文员';


/**
 * 7. 列出最低薪金大于15000的各种工作及从事此工作的员工人数。
 */

SELECT job, COUNT(*)
FROM emp 
GROUP BY job
HAVING MIN(sal) > 15000;


/**
 * 8. 列出在销售部工作的员工的姓名，假定不知道销售部的部门编号。
 */

SELECT e.ename
FROM emp e
WHERE e.deptno = (SELECT deptno FROM dept WHERE dname='销售部');


/**
 * 9. 列出薪金高于公司平均薪金的所有员工信息，所在部门名称，上级领导，工资等级。
 */

SELECT e.*,d.dname, IFNULL(m.ename, 'BOSS') manager,s.grade
FROM emp e LEFT JOIN dept d ON d.deptno = e.deptno
LEFT JOIN emp m ON e.mgr = m.empno
LEFT JOIN salgrade s ON e.sal BETWEEN s.losal AND s.hisal
WHERE e.sal > (SELECT AVG(sal) AS avg FROM emp);

SELECT * FROM salgrade;


/**
 * 10.列出与庞统从事相同工作的所有员工及部门名称。
 */

SELECT e.*,d.dname
FROM emp e LEFT JOIN dept d
ON e.deptno = d.deptno
WHERE e.job = (SELECT job FROM emp WHERE ename='庞统');


/**
 * 11.列出薪金高于在部门30工作的所有员工的薪金的员工姓名和薪金、部门名称。
 */
SELECT e.ename, e.sal, IFNULL(d.dname,'无部门') department
FROM emp e LEFT JOIN dept d
ON e.deptno = d.deptno
WHERE sal > ALL (SELECT sal FROM emp WHERE deptno = 30);


/**
 * 12.列出每个部门的员工数量、平均工资。
 */

SELECT d.dname,e.cnt, e.avgsal
FROM (SELECT deptno,COUNT(*) cnt,AVG(sal) avgsal FROM emp GROUP BY deptno) e,dept d
WHERE e.deptno=d.deptno;

