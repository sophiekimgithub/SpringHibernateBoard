# SpringHibernateBoard

1. create MySQL table
=====================================
create table board (
	no		int auto_increment primary key,
	title		varchar(255),
	contents	longtext character set utf8 not null,
	modify_date	datetime,
	PRIMARY KEY (no)
)


2. index
========================================
http://~~/SpringHibernateBoard/