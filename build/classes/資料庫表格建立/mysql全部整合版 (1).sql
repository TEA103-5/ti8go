create database if not exists tea05 ;
use tea05 ;

-- 將place_collect , trip_collect, product_collect , note_collect改成複合PK

-- 如果寫入圖片時出現database error, max_allowed_packet過小 , 可執行下一行
-- SET GLOBAL max_allowed_packet=1073741824;


drop table if exists reminder_a;
drop table if exists admins;
drop table if exists reminder_s;

drop table if exists msg_pro_c;
drop table if exists msg_pro;
drop table if exists order_detail;
drop table if exists order1;
drop table if exists product_collect;
drop table if exists product;
drop table if exists sale;

drop table if exists msg_n_c;
drop table if exists msg_n;
drop table if exists note_c;
drop table if exists note_collect;
drop table if exists note;

drop table if EXISTS msg_t_c;
drop table if EXISTS msg_t;
drop table if exists applicant;
drop table if exists group_activities;
drop table if exists team;
drop table if exists trip_detail;
drop table if exists trip_collect;
drop table if exists trip;

drop table if exists msg_p_c;
drop table if exists msg_p;
drop table if exists place_collect;
drop table if exists place;

drop table if exists reminder_u;
drop table if exists card;
drop table if exists users;



create table users (
users_id 			int not null primary key auto_increment,
users_mail 			varchar(30) not null,
users_pwd 			varchar(30) not null,
users_identi_status tinyint not null,
users_nickname 		varchar(10),
users_name 			varchar(30),
users_sex 			tinyint,
users_birthday 		char(10),
users_id_number 	char(10),
-- users_humanities 	int, --
-- users_religion 		int, --
-- users_leisure 		int, --
users_pic 			blob,
users_phone 		char(10),
create_time 		timestamp default current_timestamp,
update_time 		timestamp default current_timestamp on update current_timestamp
);

-- insert into users (users_mail,users_pwd,users_identi_status,users_pic)
-- values('abcdefg@aa.com','12345678','4',load_file('natalie_portman_1.jpg'));

insert into users (users_mail,users_pwd,users_identi_status,users_pic)
values
('aa@aa.com','12345',0,load_file('natalie_portman_1.jpg')),
('ab@aa.com','123456',1,load_file('natalie_portman_2.jpg')),
('ac@aa.com','123457',2,load_file('natalie_portman_3.jpg')),
('ad@aa.com','123458',3,load_file('natalie_portman_4.jpg')),
('ae@aa.com','123459',4,load_file('natalie_portman_5.jpg')),
('af@aa.com','123450',5,load_file('natalie_portman_6.jpg')),
('ag@aa.com','123451',6,load_file('natalie_portman_7.jpg')),
('ah@aa.com','123452',7,load_file('natalie_portman_8.jpg')),
('ai@aa.com','123453',8,load_file('natalie_portman_9.jpg')),
('aj@aa.com','123454',9,load_file('natalie_portman_10.jpg')),
('ak@aa.com','123455',0,load_file('natalie_portman_11.jpg'));



create table card (
card_id 		int not null primary key auto_increment,
users_id 		int not null,
card_number 	char(16) not null,
card_date 		char(4) not null,
card_last 		char(3) not null,
card_default 	tinyint not null,
create_time 	timestamp default current_timestamp,
update_time 	timestamp default current_timestamp on update current_timestamp,
constraint fk_card_users
foreign key (users_id) references users (users_id)
);

-- create table test (
-- test_blob blob
-- );
-- insert into test values(load_file('C:\ProgramData\MySQL\MySQL Server 8.0\Uploads\natalie_portman_1.jpg'));


insert into card (users_id,card_number,card_date,card_last,card_default)
values

(1,1234567899990000,0199,888,1),
(2,1234567899990000,0199,888,1),
(3,1234567899990000,0199,888,1),
(4,1234567899990000,0199,888,1),
(5,1234567899990000,0199,888,1),
(6,1234567899990000,0199,888,1),
(7,1234567899990000,0199,888,1),
(8,1234567899990000,0199,888,1),
(9,1234567899990000,0199,888,1);

-- alter table card add constraint fk_card_users
-- foreign key (users_id) references users (users_id);


create table reminder_u(
reminder_u_id int AUTO_INCREMENT not null,
reminder_u_time timestamp default current_timestamp,
-- reminder_u_time_last timestamp default current_timestamp on update current_timestamp,
reminder_u_text varchar(2000) not null,
reminder_u_status tinyint default 0 not null,
users_id int not null,
primary key(reminder_u_id),
constraint fk_reminder_u_users
foreign key(users_id) references users(users_id)
);

insert into reminder_u (reminder_u_text,users_id) 
values ('54656',1);
-- select * from reminder_u;
--  UPDATE reminder_u SET reminder_u_text = "131" WHERE reminder_u_id = 1;


create table place (
	place_id 		int not null auto_increment ,
    place_name 		varchar(100) not null ,
    place_address	varchar(50) not null,
    place_longitude	decimal(9,6) not null,
    place_latitude 	decimal(8,6) not null,
    place_tel		varchar(50),
    place_region	varchar(20) not null,
    place_type		varchar(20) not null,
    place_index		text,
    place_pic1		longblob,
    place_pic2		longblob,
    place_pic3		longblob,
    place_state		tinyint not null,
    users_id		int,
    business_time 	tinyint,
    place_like      int default 0 not null,
    foreign key (users_id) references users (users_id), 
	primary key (place_id));
   insert into place (place_name, place_address, place_longitude, place_latitude, place_tel,place_region,place_type,place_index,place_state , users_id) values ("圓山捷運站","台北市大同區承德路1段1號",121.297187,24.943325,"886-1234-5678","台北市","景點","這裡是圓山捷運站",0 , 1);
   insert into place (place_name, place_address, place_longitude, place_latitude, place_tel,place_region,place_type,place_index,place_state) values ("士林捷運站","台北市大同區承德路1段2號",121.298187,24.944325,"886-2234-5678","台北市","景點","這裡是士林捷運站",0);
   insert into place (place_name, place_address, place_longitude, place_latitude, place_tel,place_region,place_type,place_index,place_state) values ("雙連捷運站","台北市大同區承德路1段3號",121.299187,24.95325,"886-3234-5678","台北市","景點","這裡是雙連捷運站",0); 
   insert into place (place_name, place_address, place_longitude, place_latitude, place_tel,place_region,place_type,place_index,place_state) values ("中山捷運站","台北市大同區承德路1段4號",121.299287,24.95425,"886-4234-5678","台北市","景點","這裡是中山捷運站",0); 
   insert into place (place_name, place_address, place_longitude, place_latitude, place_tel,place_region,place_type,place_index,place_state) values ("劍潭捷運站","台北市大同區承德路1段5號",121.299387,24.95525,"886-5234-5678","台北市","景點","這裡是劍潭捷運站",0); 
   insert into place (place_name, place_address, place_longitude, place_latitude, place_tel,place_region,place_type,place_index,place_state) values ("圓山","台北市大同區承德路1段6號",121.297187,24.943325,"886-1234-5678","台北市","景點","這裡是圓山",0); 
   insert into place (place_name, place_address, place_longitude, place_latitude, place_tel,place_region,place_type,place_index,place_state) values ("士林","台北市大同區承德路1段7號",121.298187,24.944325,"886-2234-5678","台北市","景點","這裡是士林",0);
   insert into place (place_name, place_address, place_longitude, place_latitude, place_tel,place_region,place_type,place_index,place_state) values ("雙連","台北市大同區承德路1段8號",121.298187,24.944325,"886-2234-5678","台北市","景點","這裡是雙連",0); 
   insert into place (place_name, place_address, place_longitude, place_latitude, place_tel,place_region,place_type,place_index,place_state) values ("三德大飯店","台北市大同區承德路1段9號",121.299287,24.95425,"886-4234-5678","台北市","旅館民宿","這裡是三德大飯店",0); 
   insert into place (place_name, place_address, place_longitude, place_latitude, place_tel,place_region,place_type,place_index,place_state) values ("喜來登飯店","台北市大同區承德路1段10號",121.299387,24.95525,"886-5234-5678","台北市","旅館民宿","這裡是喜來登飯店",0); 


create table place_collect(
    place_id				int not null ,
	users_id				int not null,
    place_collect_time		timestamp default current_timestamp,
    foreign key (users_id) references users (users_id),
    foreign key (place_id) references place (place_id),
	primary key (place_id,users_id)
    );
    
	insert into place_collect (place_id, users_id ) values (1 , 1 );
    insert into place_collect (place_id, users_id ) values (2 , 1 );
    insert into place_collect (place_id, users_id ) values (3 , 3 );
    insert into place_collect (place_id, users_id ) values (4 , 1 );
    insert into place_collect (place_id, users_id ) values (5 , 2 );
    insert into place_collect (place_id, users_id ) values (6 , 3 );
    insert into place_collect (place_id, users_id ) values (7 , 1 );
    insert into place_collect (place_id, users_id ) values (8 , 2 );
    insert into place_collect (place_id, users_id ) values (9 , 3 );
    insert into place_collect (place_id, users_id ) values (10 , 1 );
    
    
    
    create table msg_p(
msg_p_id int AUTO_INCREMENT not null,
msg_p_time timestamp default current_timestamp,
msg_p_time_last timestamp default current_timestamp on update current_timestamp,
msg_p_status tinyint default 0,
msg_p_ip char(20),
msg_p_text varchar(2000) not null,
users_id int not null,
place_id int not null,
primary key(msg_p_id),
constraint fk_msg_p_users
foreign key(users_id) references users(users_id),
constraint fk_msg_p_place
foreign key(place_id) references place(place_id)
);

insert into msg_p (msg_p_text,users_id,place_id,msg_p_ip) 
values ('這是msg_p_text測試文字',1,1,'255.0.0.1');


create table msg_p_c(
msg_p_c_id int AUTO_INCREMENT not null,
msg_p_c_time timestamp default current_timestamp,
msg_p_c_time_last timestamp default current_timestamp on update current_timestamp,
msg_p_c_status tinyint default 0,
msg_p_c_ip char(20),
msg_p_c_text varchar(2000) not null,
msg_p_id int not null,
users_id int not null,
primary key(msg_p_c_id),
constraint fk_msg_p_c_msg_p
foreign key(msg_p_id) references msg_p(msg_p_id),
constraint fk_msg_p_c_users
foreign key(users_id) references users(users_id)
);

insert into msg_p_c (msg_p_c_text,users_id,msg_p_id,msg_p_c_ip) 
values ('54656',1,1,'255.0.0.1');


    create table trip(
	trip_id int	not null auto_increment,
    users_id	int	not null,
	trip_create_time	timestamp default current_timestamp,
	last_edit_time	timestamp default current_timestamp on update current_timestamp,
	last_editor	int	not null,
	trip_state	tinyint	not null ,
	trip_like	int	default 0,
    trip_look	int	default 0,
	read_authority	tinyint	not null,
	edit_authority	tinyint	not null,
	trip_area	varchar(20)	not null,
	trip_start	date	not null,
	trip_end	date	not null,
	trip_name	varchar(30)	not null,
	trip_description	text,
	trip_type	varchar(20)	,
	trip_tot_cost	int	 default 0,
	place_weather	varchar(20)	,
    foreign key (users_id) references users (users_id),
	foreign key (last_editor) references users (users_id), 
    primary key (trip_id))
    charset=utf8 collate=utf8_unicode_ci;

-- insert into trip (users_id,last_editor,trip_state,trip_like,read_authority,edit_authority,trip_area,trip_start,trip_end,trip_name,trip_description,trip_type,trip_tot_cost,place_weather) values ();
insert into trip (users_id,last_editor,trip_state,read_authority,edit_authority,trip_area,trip_start,trip_end,trip_name) values (1,1,1,1,1,"台北","2020-12-01","2020-12-03","tibame");
insert into trip (users_id,last_editor,trip_state,trip_like,read_authority,edit_authority,trip_area,trip_tot_cost,trip_start,trip_end,trip_name) values (1,1,1,1,1,1,"台北",200,"2020-12-01","2020-12-03","tibame");



create table trip_collect(
    trip_id				int not null,
    users_id			int not null,
    trip_collect_time	timestamp default current_timestamp,
    foreign key (users_id) references users (users_id),
	foreign key (trip_id) references trip (trip_id),
    primary key (trip_id , users_id)
    );
    
    insert into trip_collect (trip_id, users_id ) values (1 , 1 );
    insert into trip_collect (trip_id, users_id ) values (2 , 2 );
    insert into trip_collect (trip_id, users_id ) values (2 , 3 );
    insert into trip_collect (trip_id, users_id ) values (2 , 4 );
    insert into trip_collect (trip_id, users_id ) values (2 , 5 );
    insert into trip_collect (trip_id, users_id ) values (1 , 6 );
    insert into trip_collect (trip_id, users_id ) values (2 , 7 );
    insert into trip_collect (trip_id, users_id ) values (2 , 8 );
    insert into trip_collect (trip_id, users_id ) values (2 , 9 );
    insert into trip_collect (trip_id, users_id ) values (1 , 10 );


create table trip_detail (
    trip_detail_id	int	not null auto_increment,
	trip_id	int	not null,
	trip_day	smallint	not null,
	trip_sort	int	not null,
	trip_detail_type	varchar(20)	not null,
	trip_content	varchar(30)	not null,
	place_id	int,
	trip_start_time	time,
	trip_end_time	time,	
	trip_remarks	text,
	trip_cost	int,
    foreign key (trip_id) references trip (trip_id), 
	foreign key (place_id) references place (place_id), 
	primary key (trip_detail_id))
    charset=utf8 collate=utf8_unicode_ci;
    
  --  insert into trip_detail (trip_id,trip_day,trip_sort,trip_detail_type,trip_content,place_id,trip_start_time,trip_end_time,trip_remarks,trip_cost ) values ();
  
insert into trip_detail (trip_id,trip_day,trip_sort,trip_detail_type,trip_content , place_id) values (1,1,1,"其他","集合" , 1);
insert into trip_detail (trip_id,trip_day,trip_sort,trip_detail_type,trip_content) values (1,2,1,"交通","火車");
insert into trip_detail (trip_id,trip_day,trip_sort,trip_detail_type,trip_content) values (1,3,1,"飯店","兄弟飯店");
insert into trip_detail (trip_id,trip_day,trip_sort,trip_detail_type,trip_content) values (2,1,1,"景點","台北101");
insert into trip_detail (trip_id,trip_day,trip_sort,trip_detail_type,trip_content) values (2,2,1,"餐廳","麥當勞");
insert into trip_detail (trip_id,trip_day,trip_sort,trip_detail_type,trip_content) values (2,3,1,"交通","步行");
insert into trip_detail (trip_id,trip_day,trip_sort,trip_detail_type,trip_content,place_id,trip_cost) values (2,3,2,"飯店","三德大飯店",9,200);
insert into trip_detail (trip_id,trip_day,trip_sort,trip_detail_type,trip_content,place_id) values (1,2,2,"飯店","喜來登大飯店",10);
insert into trip_detail (trip_id,trip_day,trip_sort,trip_detail_type,trip_content,place_id) values (1,2,1,"景點","中山捷運站",4);  



create table team(
    trip_id	int	not null,
	users_id	int	not null,

    foreign key (trip_id) references trip (trip_id),
    foreign key (users_id) references users (users_id),
	primary key (trip_id,users_id))
	charset=utf8 collate=utf8_unicode_ci;
    
    insert into team (trip_id,users_id ) values (1 , 1 );
    insert into team (trip_id,users_id ) values (1 , 2 );
    insert into team (trip_id,users_id ) values (2 , 2 );
    insert into team (trip_id,users_id ) values (2 , 3 );
    insert into team (trip_id,users_id ) values (2 , 4 );


    create table group_activities(
    activities_id	int	not null auto_increment,
    trip_id	int	not null,
	users_id	int	not null,
	activities_create_time	timestamp default current_timestamp,
	activities_state	tinyint	not null,
	activities_deadline	date   not null,
	activities_max_users	int	not null,
	activities_users_count	int	default 0,
	activities_start	date	not null,
	activities_end	date	not null,
	activities_name	varchar(30)	not null,
	activities_description	text	not null,
    foreign key (trip_id) references trip (trip_id),
    foreign key (users_id) references users (users_id),
	primary key (activities_id))
	charset=utf8 collate=utf8_unicode_ci;
    
    -- insert into group_activities(trip_id,users_id,activities_state,activities_deadline,activities_max_users,activities_users_count,activities_start,activities_end,activities_name,activities_description) values (1 , 1 );
    insert into group_activities(trip_id,users_id,activities_state,activities_deadline,activities_max_users,activities_users_count,activities_start,activities_end,activities_name,activities_description) 
    values (1 , 1 ,1,"2021-05-01",50,2,"2021-05-03","2021-05-05","Tivago","我們去郊遊吧!");
    insert into group_activities(trip_id,users_id,activities_state,activities_deadline,activities_max_users,activities_users_count,activities_start,activities_end,activities_name,activities_description) 
    values (2 , 2 ,1,"2021-03-20",50,2,"2021-04-03","2021-04-05","Trivago","郊遊趣!");
    
    
    
     create table applicant(
    activities_id	int	not null,
	users_id	int	not null,
	registration_time	timestamp	default current_timestamp,
    foreign key (activities_id) references group_activities (activities_id),
    foreign key (users_id) references users (users_id),
	primary key (activities_id,users_id))
	charset=utf8 collate=utf8_unicode_ci;
    
    insert into applicant (activities_id,users_id ) values (1 , 1 );
    insert into applicant (activities_id,users_id ) values (1 , 2 );
    insert into applicant (activities_id,users_id ) values (2 , 2 );
    insert into applicant (activities_id,users_id ) values (2 , 3 );
    insert into applicant (activities_id,users_id ) values (2 , 4 );
	



create table msg_t(
msg_t_id int AUTO_INCREMENT not null,
msg_t_time timestamp default current_timestamp,
msg_t_time_last timestamp default current_timestamp on update current_timestamp,
msg_t_status tinyint default 0,
msg_t_ip char(20),
msg_t_text varchar(2000) not null,
trip_id int not null,
users_id int not null,
primary key(msg_t_id),
constraint fk_msg_t_trip
foreign key(trip_id) references trip(trip_id),
constraint fk_msg_t_users
foreign key(users_id) references users(users_id)
);

insert into msg_t (msg_t_text,users_id,trip_id,msg_t_ip) 
values ('54656',1,1,'255.0.0.1');

create table msg_t_c(
msg_t_c_id int AUTO_INCREMENT not null,
msg_t_c_time timestamp default current_timestamp,
msg_t_c_time_last timestamp default current_timestamp on update current_timestamp,
msg_t_c_status tinyint default 0,
msg_t_c_ip char(20),
msg_t_c_text varchar(2000) not null,
msg_t_id int not null,
users_id int not null,
primary key(msg_t_c_id),
constraint fk_msg_t_c_msg_t
foreign key(msg_t_id) references msg_t(msg_t_id),
constraint fk_msg_t_c_users
foreign key(users_id) references users(users_id)
);

insert into msg_t_c (msg_t_c_text,users_id,msg_t_id,msg_t_c_ip) 
values ('54656',1,1,'255.0.0.1');


create table note (
  note_id int auto_increment not null,
  note_classid int not null,
  note_date timestamp default current_timestamp on update current_timestamp,
  travel_start date,
  note_title varchar(30) not null,
  note_description varchar(50) not null,
  note_update timestamp default current_timestamp on update current_timestamp,
  users_id int not null,
  trip_id int,
  note_like int default 0 ,
  foreign key (users_id) references users (users_id),
  foreign key (trip_id) references trip (trip_id),
  primary key (note_id)
);


insert into note(note_classid, note_title, travel_start, note_description, users_id, trip_id) values(  1, '今天天氣晴', '2021-03-06','台東好美好玩',1, 2);
insert note(note_classid, note_title, travel_start, note_description, users_id, trip_id) values(  1, '今天天氣晴', '2021-03-06','宜蘭好吃好玩好開心',1, 2);
insert into note(note_classid, note_title, travel_start, note_description, users_id, trip_id) values(  2, '今天天氣好','2021-02-26', '空氣清新',1, 1);



create table note_collect(
    note_id				int not null,
    users_id			int not null,
    note_collect_time	timestamp default current_timestamp,
    foreign key (users_id) references users (users_id),
	foreign key (note_id) references note (note_id),
    primary key (note_id , users_id)
    );
    
	insert into note_collect (note_id, users_id ) values (1 , 1 );
    insert into note_collect (note_id, users_id ) values (1 , 2 );
    insert into note_collect (note_id, users_id ) values (1 , 3 );
    insert into note_collect (note_id, users_id ) values (1 , 4 );
    insert into note_collect (note_id, users_id ) values (1 , 5 );
    insert into note_collect (note_id, users_id ) values (1 , 6 );
    insert into note_collect (note_id, users_id ) values (1 , 7 );
    insert into note_collect (note_id, users_id ) values (1 , 8 );
    insert into note_collect (note_id, users_id ) values (1 , 9 );
    insert into note_collect (note_id, users_id ) values (1 , 10 );


create table note_c(
note_c_id int auto_increment primary key not null,
note_c_title varchar(20) not null,
note_c_content varchar(2000) not null,
note_c_img varchar(20),
note_id int,
constraint fk_note_id
foreign key(note_id) references note(note_id)
)auto_increment = 1;

insert into note_c(note_c_title, note_c_content, note_id) values ('哈哈哈哈哈','星期一天氣晴',1);



create table msg_n(
msg_n_id int AUTO_INCREMENT not null,
msg_n_time timestamp default current_timestamp,
msg_n_time_last timestamp default current_timestamp on update current_timestamp,
msg_n_status tinyint default 0,
msg_n_ip char(20),
msg_n_text varchar(2000) not null,
note_id int not null,
users_id int not null,
primary key(msg_n_id),
constraint fk_msg_n_note
foreign key(note_id) references note(note_id),
constraint fk_msg_n_users
foreign key(users_id) references users(users_id)
);

insert into msg_n (msg_n_text,users_id,note_id,msg_n_ip) 
values ('54656',1,1,'255.0.0.1');


create table msg_n_c(
msg_n_c_id int AUTO_INCREMENT not null,
msg_n_c_time timestamp default current_timestamp,
msg_n_c_time_last timestamp default current_timestamp on update current_timestamp,
msg_n_c_status tinyint default 0,
msg_n_c_ip char(20),
msg_n_c_text varchar(2000) not null,
msg_n_id int not null,
users_id int not null,
primary key(msg_n_c_id),
constraint fk_msg_n_c_msg_n
foreign key(msg_n_id) references msg_n(msg_n_id),
constraint fk_msg_n_c_users
foreign key(users_id) references users(users_id)
);

insert into msg_n_c (msg_n_c_text,users_id,msg_n_id,msg_n_c_ip) 
values ('54656',1,1,'255.0.0.1');


create table sale(
sale_id int AUTO_INCREMENT not null,
sale_email varchar(30) not null,
sale_pwd varchar(30) not null,
sale_name varchar(30),
sale_audit_status tinyint default 0,
sale_audit_pic blob,
sale_status tinyint,
sale_phone char(20),
sale_nickname varchar(30),
sale_rate float(2,2),
sale_time_create timestamp default current_timestamp,
primary key(sale_id)
);

insert into sale(sale_email,sale_pwd)values("123@gmail.com","123");


create table product (
product_id      	 int auto_increment not null,
product_name         varchar(50) not null,
product_status       tinyint not null,
product_update_time  timestamp,
product_content	     varchar(50),
product_description  varchar(50),
product_categories   varchar(50),
product_price        int,
product_stock        int,
product_pic          blob,
sale_id              int,
foreign key(sale_id) references sale(sale_id),
primary key(product_id)
);
insert into product (product_name , product_status , sale_id) values("ivan",0,1);
insert into product (product_name , product_status , sale_id) values("帳篷",0,1);
insert into product (product_name , product_status , sale_id) values( "特大帳篷",0,1);


create table product_collect(
    product_id				int not null,
    users_id				int not null,
    product_collect_time	timestamp default current_timestamp,
    foreign key (users_id) references users (users_id),
	foreign key (product_id) references product (product_id),
    primary key (product_id,users_id)
    );
    
	insert into product_collect (product_id, users_id ) values (1 , 1 );
    insert into product_collect (product_id, users_id ) values (2 , 2 );
    insert into product_collect (product_id, users_id ) values (3 , 3 );
    insert into product_collect (product_id, users_id ) values (2 , 4 );
    insert into product_collect (product_id, users_id ) values (1 , 5 );
    insert into product_collect (product_id, users_id ) values (2 , 6 );
    insert into product_collect (product_id, users_id ) values (3 , 7 );
    insert into product_collect (product_id, users_id ) values (2 , 8 );
    insert into product_collect (product_id, users_id ) values (1 , 9 );
    insert into product_collect (product_id, users_id ) values (2 , 10 );


create table order1(
order_id        int auto_increment not null, 
order_date      timestamp default current_timestamp,
order_status    tinyint not null,
order_rate      int,
card_number     char(16) not null,
users_id         int,
foreign key (users_id) references users (users_id),
primary key (order_id)
);
insert into order1(order_id,order_status,card_number,users_id)values(1,0,1234567890123456,1);


create table order_detail(
order_detail_id      int auto_increment not null,
product_id      	 int not null,
order_detail_count   int not null,-- 購買數量
order_id             int not null,
primary key (order_detail_id),
constraint fk_order_id
foreign key (order_id) references order1(order_id),

constraint fk_product_id
foreign key (product_id) references product (product_id)
);
insert into order_detail(order_detail_id, order_detail_count, product_id ,order_id )values(1,1,1,1);


create table msg_pro(
msg_pro_id int AUTO_INCREMENT not null,
msg_pro_time timestamp default current_timestamp,
msg_pro_time_last timestamp default current_timestamp on update current_timestamp,
msg_pro_status tinyint default 0,
msg_pro_ip char(20),
msg_pro_text varchar(2000) not null,
product_id int not null,
users_id int not null,
primary key(msg_pro_id),
constraint fk_msg_pro_product
foreign key(product_id) references product(product_id),
constraint fk_msg_pro_users
foreign key(users_id) references users(users_id)
);

insert into msg_pro (msg_pro_text,users_id,product_id,msg_pro_ip) 
values ('54656',1,1,'255.0.0.1');


create table msg_pro_c(
msg_pro_c_id int AUTO_INCREMENT not null,
msg_pro_c_time timestamp default current_timestamp,
msg_pro_c_time_last timestamp default current_timestamp on update current_timestamp,
msg_pro_c_status tinyint default 0,
msg_pro_c_ip char(20),
msg_pro_c_text varchar(2000) not null,
msg_pro_id int not null,
sale_id int,
users_id int,
primary key(msg_pro_c_id),
constraint fk_msg_pro_child_sale
foreign key(sale_id) references sale(sale_id),
constraint fk_msg_pro_child_users
foreign key(users_id) references users(users_id)
);

insert into msg_pro_c (msg_pro_c_text , msg_pro_id , sale_id , users_id ) 
values ('這裡是msg_pro_c_text測試內容',1,1,1);


create table reminder_s(
reminder_s_id int AUTO_INCREMENT not null,
reminder_s_time timestamp default current_timestamp on update current_timestamp,
reminder_s_text varchar(2000) not null,
reminder_s_status tinyint default 0 not null,
sale_id int not null,
primary key(reminder_s_id),
constraint fk_reminder_s_sale
foreign key(sale_id) references sale(sale_id)
);
insert into reminder_s( reminder_s_text , reminder_s_status , sale_id) values("這是reminder_s測試文字", 0 , 1);

create table admins (
admins_id 			int not null primary key auto_increment ,
admins_email 		varchar(30) not null ,
admins_name 		varchar(20) not null ,
admins_password 	varchar(20) not null ,
admins_sex			tinyint not null ,
admins_authority	varchar(20) not null , 
admins_position		varchar(20) ,
admins_create_time	timestamp not null default current_timestamp,
admins_edit_time	timestamp not null default current_timestamp on update current_timestamp
);

insert into admins (admins_email,admins_name,admins_password,admins_sex,admins_authority,admins_position)
values

('aa.@aa.com','aa','12345','1','root',null),
('ab.@aa.com','ab','12345','1','root',null),
('ac.@aa.com','ac','12345','1','root',null),
('ad.@aa.com','ad','12345','2','root',null),
('ae.@aa.com','ae','12345','2','root',null),
('af.@aa.com','af','12345','3','root',null),
('ag.@aa.com','ag','12345','2','root',null),
('ah.@aa.com','ah','12345','1','root',null),
('ai.@aa.com','ai','12345','2','root',null),
('aj.@aa.com','aj','12345','1','root',null),
('ak.@aa.com','ak','12345','2','root',null);


create table reminder_a(
reminder_a_id int AUTO_INCREMENT not null,
reminder_a_time timestamp default current_timestamp on update current_timestamp,
reminder_a_text varchar(2000) not null,
reminder_a_status tinyint default 0 not null,
admins_id int not null,
primary key(reminder_a_id),
constraint fk_reminder_a_admins
foreign key(admins_id) references admins(admins_id)
);
insert into reminder_a( reminder_a_text , reminder_a_status , admins_id) values("這是reminder_a測試文字", 0 , 1);













commit;