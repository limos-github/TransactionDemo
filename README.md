# TransactionDemo
SPRING BOOT + ORACLE + MYBATIS PLUS 


`-- auto-generated definition
create table TEST_TRANSACTION
(
ID     NUMBER(20) not null
constraint TEST_TRANSACTION_PK
primary key,
LENGTH NUMBER(20),
WIDTH  NUMBER(20),
HEIGHT NUMBER(20),
WEIGHT NUMBER(20)
);
comment on table TEST_TRANSACTION is '测试事务';
comment on column TEST_TRANSACTION.LENGTH is '长';
comment on column TEST_TRANSACTION.WIDTH is '宽';
comment on column TEST_TRANSACTION.HEIGHT is '高';
comment on column TEST_TRANSACTION.WEIGHT is '重量';
索引
CREATE SEQUENCE TEST_TRANSACTION_SEQ
START WITH 1
INCREMENT BY 1
MINVALUE 1 NOMAXVALUE
cache 30;
`