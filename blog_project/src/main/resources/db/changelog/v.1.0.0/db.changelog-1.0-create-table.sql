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