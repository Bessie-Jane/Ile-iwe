set Foreign_key_checks = 0;

truncate table learning_party;
truncate table authority;
truncate table instructor;

insert into learning_party(`id`, `email`, `password`, `enabled`)
values (123, 'tomi@gmail.com', '123pass321', false),
    (124, 'tobi@gmail.com', '123pass321', false),
    (125, 'bomi@gmail.com', '123pass321', false),
    (126, 'bunmi@gmail.com', '123pass321', false),
    (127, 'craig@gmail.com', '123pass321', false);


set foreign_key_checks = 1;