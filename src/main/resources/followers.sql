create table cat_follow
(
    id          serial primary key,
    author_id   varchar(64) references cat_user (id),
    follower_id varchar(64) references cat_user (id)
);

insert into cat_follow (author_id, follower_id)
values ('grumpy', 'tom');

insert into cat_post (author_id, description, photo_url, creation_date)
values ('grumpy', 'Мой новый пост!!111', 'file:///storage/catsgram/grumpy/2/image.png', '2022-03-08 15:24:03.000000');