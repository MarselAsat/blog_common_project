drop table if exists post_tag;
drop table if exists comment;
drop table if exists post;
drop table if exists tag;

--acid

create table post(
                     post_id bigserial primary key,
                     user_id varchar(50) not null,
                     title varchar(100) not null,
                     content text not null,
                     dt_created timestamp not null,
                     dt_updated timestamp
);

create table tag(
                    tag_id bigserial primary key,
                    name varchar(50) not null
);

create table post_tag(
                         post_id bigint references "post" (post_id) on delete cascade,
                         tag_id bigint references "tag" (tag_id) on delete cascade,
                         primary key(post_id, tag_id)
);

create table comment(
                        comment_id bigserial primary key,
                        user_id varchar(50) not null,
                        post_id bigint references "post" (post_id) on delete cascade,
                        content text,
                        dt_created timestamp not null,
                        dt_updated timestamp
);


--data

insert into post(user_id, title, content, dt_created, dt_updated)
values('7ca1764f-33f0-4737-baa8-22c2226be229', 'Day 1', 'It''s all good!', current_timestamp - interval '2 days', null);

insert into post(user_id, title, content, dt_created, dt_updated)
values('7ca1764f-33f0-4737-baa8-22c2226be229', 'Day 2', 'It''s all ok!', current_timestamp - interval '1 days', null);

insert into post(user_id, title, content, dt_created, dt_updated)
values('4ef2a7f7-03c1-45d7-b1db-55ed060e44e2', 'Day 3', 'It''s all bad!', current_timestamp, null);

insert into tag(name) values('news');
insert into tag(name) values('life');
insert into tag(name) values('day');
insert into tag(name) values('mood');
insert into tag(name) values('ideas');
insert into tag(name) values('thoughts');

insert into post_tag(post_id, tag_id) values(1, 1);
insert into post_tag(post_id, tag_id) values(1, 2);
insert into post_tag(post_id, tag_id) values(2, 3);
insert into post_tag(post_id, tag_id) values(2, 2);
insert into post_tag(post_id, tag_id) values(2, 1);
insert into post_tag(post_id, tag_id) values(2, 5);
insert into post_tag(post_id, tag_id) values(3, 3);
insert into post_tag(post_id, tag_id) values(3, 2);
insert into post_tag(post_id, tag_id) values(3, 6);

insert into comment(user_id, post_id, content, dt_created)
values('7ca1764f-33f0-4737-baa8-22c2226be229', 2, 'Nice!', current_timestamp);
insert into comment(user_id, post_id, content, dt_created)
values('4ef2a7f7-03c1-45d7-b1db-55ed060e44e2', 1, 'Awesome!', current_timestamp);
insert into comment(user_id, post_id, content, dt_created)
values('7ca1764f-33f0-4737-baa8-22c2226be229', 1, 'Excellent!', current_timestamp);
insert into comment(user_id, post_id, content, dt_created)
values('4ef2a7f7-03c1-45d7-b1db-55ed060e44e2', 1, 'Wonderful!', current_timestamp);
insert into comment(user_id, post_id, content, dt_created)
values('7ca1764f-33f0-4737-baa8-22c2226be229', 3, 'Disgusting!', current_timestamp);
insert into comment(user_id, post_id, content, dt_created)
values('4ef2a7f7-03c1-45d7-b1db-55ed060e44e2', 3, 'Atrocious!', current_timestamp);
