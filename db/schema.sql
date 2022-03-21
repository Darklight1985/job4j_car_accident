create table acc_type (
id serial primary key,
name text
);

create table rule (
id serial primary key,
name text
);

create table accident (
id serial primary key,
name text,
description text,
address text,
acc_type_id int references acc_type(id)
);

create table accident_rule (
id serial primary key,
accident_id int not null references accident(id),
rule_id int not null references rule(id)
);