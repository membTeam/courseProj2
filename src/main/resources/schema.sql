create table examination (
    id varchar(36) not null,
    question varchar(200) not null,
    answer varchar(300) not null,
    exam char(4) default 'java' not null, -- java or math
    primary key (id)
);