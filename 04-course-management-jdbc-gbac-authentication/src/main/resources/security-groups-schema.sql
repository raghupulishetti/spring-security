DROP TABLE IF EXISTS group_authorities;
DROP TABLE IF EXISTS group_members;
DROP TABLE IF EXISTS groups;
create table groups (
id bigint NOT NULL AUTO_INCREMENT primary key,
group_name varchar(256) not null
);
create table group_authorities (
group_id bigint not null,
authority varchar(256) not null,
constraint fk_group_authorities_group
foreign key(group_id) references groups(id)
);
create table group_members (
id bigint NOT NULL AUTO_INCREMENT primary key,
username varchar(256) not null,
group_id bigint not null,
constraint fk_group_members_group
foreign key(group_id) references groups(id)
);