# 数据库建表sql

## 1.1雇员表（t_employee）

```sql
CREATE TABLE t_employee(
   id  INT(12) not null auto_increment,
	 real_name varchar(60) not null,
	 sex int(2) not null COMMIT '1-男 0-女',
	 birthday date not null,
	 mobile varchar(20) not null,
	 email varhchat(60) not null,
	 POSITION VARCHAR(20) not null,
	 note VARCHAR(256),
	 primary key (id)
);
```

## 1.2员工任务（t_employee_task）

```sql
CREATE TABLE t_employee_task(
  id int(12) not null,
	emp_id int (12) not null,
	task_id int(12) not null,
	task_name VARCHAR(60) not null,
	note VARCHAR(256) ,
	PRIMARY key (id)
);
```

## 1.3 男生体检表（t_female_health_form）

```sql
CREATE TABLE t_female_health_form(
  id int(12) not null auto_increment,
	emp_id int(12) not null,
	heart varchar(64) not null,
	liver varchar(64) not null,
	spleen varchar(64) not null,
	lung varchar(64) not null,
	kideny varchar(64) not null,
	uterus varchar(64) not null,
	note VARCHAR(256),
	PRIMARY key (id)
);
```

## 1.4女生体检表（t_male_health_form）

```sql
CREATE TABLE t_male_health_form(
  id int(12) not null auto_increment,
	emp_id int(12) not null,
	heart varchar(64) not null,
	liver varchar(64) not null,
	spleen varchar(64) not null,
	lung varchar(64) not null,
	kideny varchar(64) not null,
	prostate varchar(64) not null,
	note VARCHAR(256),
	PRIMARY key (id)
);
```

## 1.5 员工任务表

```sql
CREATE TABLE t_task(
 id int (12) not null,
 title varchar(60) not null,
 context varchar(256) not null,
 note varchar (256),
 primary key (id)
);

```

## 1.6 工牌表

```sql
CREATE TABLE t_work_card(
 id int(12) not null auto_increment,
 emp_id int(12) not null,
 real_name VARCHAR(60) not null,
 department VARCHAR(20) not null,
 moblie VARCHAR(20)  not null,
 POSITION varchar (30) not null,
 note varchar(256) ,
 primary key (id)
)
```

## 1.7外键创建

```sql
alter table t_employee_task add CONSTRAINT FK_Reference_4 foreign key (emp_id)
   references t_employee(id) on DELETE RESTRICT on UPDATE RESTRICT;

ALTER TABLE t_employee_task add CONSTRAINT FK_Reference_8 FOREIGN key (task_id)
   REFERENCES t_task(id) on DELETE RESTRICT on UPDATe RESTRICT;
	 
ALTER TABLE t_female_health_form add CONSTRAINT FK_Reference_5 FOREIGN key (emp_id)
	REFERENCES t_employee(id) on DELETE RESTRICT on UPDATE RESTRICT;
	 
ALTER TABLE t_male_health_form add CONSTRAINT FK_Reference_6 FOREIGN key (emp_id)
 REFERENCES t_employee(id) on DELETE RESTRICT on UPDATE RESTRICT;
 
ALTER TABLE t_work_card add CONSTRAINT FK_Reference_7 FOREIGN key (emp_id)
REFERENCES t_employee(id) on DELETE RESTRICT on UPDATE RESTRICT;
```

