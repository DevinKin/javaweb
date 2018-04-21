CREATE TABLE tb_files (
	   fid CHAR(20) UNIQUE,
	   filepath VARCHAR(100),
	   framename VARCHAR(30),
	   cnt INT,
	   filesize DOUBLE
);
