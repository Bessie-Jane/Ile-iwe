create database if not exists ileiwe_setup;

create user if not exists 'ileiwe_user'@'localhost' identified by 'ileiwe123';
grant all privileges on ileiwe_setup.* to 'ileiwe_user'@'localhost';
flush privileges;

