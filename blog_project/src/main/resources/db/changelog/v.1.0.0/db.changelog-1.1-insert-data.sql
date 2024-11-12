--data

insert into post(user_id, title, content, dt_created, dt_updated)
values('60c08c71-acaa-4014-83aa-e7ec4d820a10', 'Day 1', 'It''s all good!', current_timestamp - interval '2 days', null);

insert into post(user_id, title, content, dt_created, dt_updated)
values('60c08c71-acaa-4014-83aa-e7ec4d820a10', 'Day 2', 'It''s all ok!', current_timestamp - interval '1 days', null);

insert into post(user_id, title, content, dt_created, dt_updated)
values('51f707a4-a402-489c-bde0-fe13c8739457', 'Day 3', 'It''s all bad!', current_timestamp, null);

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
values('60c08c71-acaa-4014-83aa-e7ec4d820a10', 2, 'Nice!', current_timestamp);
insert into comment(user_id, post_id, content, dt_created)
values('51f707a4-a402-489c-bde0-fe13c8739457', 1, 'Awesome!', current_timestamp);
insert into comment(user_id, post_id, content, dt_created)
values('51f707a4-a402-489c-bde0-fe13c8739457', 1, 'Excellent!', current_timestamp);
insert into comment(user_id, post_id, content, dt_created)
values('51f707a4-a402-489c-bde0-fe13c8739457', 1, 'Wonderful!', current_timestamp);
insert into comment(user_id, post_id, content, dt_created)
values('51f707a4-a402-489c-bde0-fe13c8739457', 3, 'Disgusting!', current_timestamp);
insert into comment(user_id, post_id, content, dt_created)
values('51f707a4-a402-489c-bde0-fe13c8739457', 3, 'Atrocious!', current_timestamp);
