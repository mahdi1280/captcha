create table captcha_code(
    id varchar primary key ,
    captcha_number varchar(20),
    expired date,
    disabled boolean,
    version integer
);