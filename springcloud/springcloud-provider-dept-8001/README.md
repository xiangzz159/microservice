create database db01;

create table dept (
    `deptno` bigint primary key auto_increment,
    `dname` varchar(60) not null,
    `db_source` varchar(60) not null
);

insert into dept values (1, '研发部', 'db01');
insert into dept values (2, '市场部', 'db01');
insert into dept values (3, '人事部', 'db01');
insert into dept values (4, '运营部', 'db01');
insert into dept values (5, '营销部', 'db01');