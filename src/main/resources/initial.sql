create database cats;

create table cat_user
(
    id       varchar(64) not null,
    username varchar(255) default 'noname cat',
    nickname varchar(255)
);

-- индекс, чтобы базе данных было легче искать пользователей
create unique index user_id_uindex on cat_user (id);

insert into cat_user (id, username, nickname)
values ('tom', 'Thomas C. Andersun', 'neoc@t'),
       ('grumpy', 'Альфред Хичкот', 'sca_a_a_ry'),
       ('puss_in_boots', 'Basileus Felis F.', 'under_wood');

create table cat_post
(
    id            serial primary key,
    author_id     varchar(64) references cat_user (id) not null,
    description   varchar(500),
    photo_url     varchar(1000),
    creation_date timestamp
);


-- Добавление тестовых данных для пользователя 'tom'
insert into cat_post (author_id, description, photo_url, creation_date)
values
    ('tom', 'Летний отдых на пляже. #sun #sea', 'http://example.com/photo1.jpg', '2024-08-01 10:00:00'),
    ('tom', 'Новый аксессуар для кошек. #fashion #catlife', 'http://example.com/photo2.jpg', '2024-08-02 15:30:00'),
    ('tom', 'Кот в коробке. #fun #catbox', 'http://example.com/photo3.jpg', '2024-08-03 08:45:00');

-- Добавление тестовых данных для пользователя 'grumpy'
insert into cat_post (author_id, description, photo_url, creation_date)
values
    ('grumpy', 'Сегодня был не лучший день. #grumpy #mood', 'http://example.com/photo4.jpg', '2024-08-04 11:00:00'),
    ('grumpy', 'Чашка молока — это всё, что мне нужно. #milk #simplepleasures', 'http://example.com/photo5.jpg', '2024-08-05 14:15:00'),
    ('grumpy', 'Где мой обед? #hungry #catproblems', 'http://example.com/photo6.jpg', '2024-08-06 09:30:00');

-- Добавление тестовых данных для пользователя 'puss_in_boots'
insert into cat_post (author_id, description, photo_url, creation_date)
values
    ('puss_in_boots', 'Новая игрушка. #playtime #catfun', 'http://example.com/photo7.jpg', '2024-08-07 12:00:00'),
    ('puss_in_boots', 'Спокойный вечер у камина. #relax #cozy', 'http://example.com/photo8.jpg', '2024-08-08 18:00:00'),
    ('puss_in_boots', 'Дружеская встреча с другими котами. #catfriends #social', 'http://example.com/photo9.jpg', '2024-08-09 16:30:00');