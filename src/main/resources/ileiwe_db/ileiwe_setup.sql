create database ileiwe_setup;

create user 'ileiwe_user'@'localhost' identified by 'ileiwe123';
grant all privileges on ileiwe_setup.* to 'ileiwe_user'@'localhost';
flush privileges;