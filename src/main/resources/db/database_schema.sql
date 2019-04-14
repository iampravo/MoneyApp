drop table  if exists user_transaction;
drop table  if exists bank_account;
drop table  if exists bank_user;
drop table  if exists currency;
drop table  if exists transaction_status;

create TABLE IF NOT EXISTS   currency (
  currency_id INT PRIMARY KEY,
  currency_name VARCHAR(30),
  currency_code VARCHAR(3)
);

create TABLE IF NOT EXISTS bank_user (
  user_id BIGINT   PRIMARY KEY auto_increment,
  user_name VARCHAR(256) NOT NULL
);



create TABLE IF NOT EXISTS bank_account (
  account_number BIGINT PRIMARY KEY auto_increment,
  user_id BIGINT NOT NULL,
  account_type VARCHAR(256) NOT NULL,
  balance DECIMAL(19,4) NOT NULL,
  blocked_amount DECIMAL(19,4) NOT NULL,
  currency_id INT NOT NULL,
  FOREIGN KEY(user_id) REFERENCES bank_user(user_id),
  FOREIGN KEY(currency_id) REFERENCES currency(currency_id)
);

create TABLE IF NOT EXISTS transaction_status (
  id INT PRIMARY KEY,
  name VARCHAR(30)
);

create TABLE IF NOT EXISTS user_transaction (
  transaction_id BIGINT PRIMARY KEY  auto_increment,
  from_account_number BIGINT NOT NULL,
  to_account_number BIGINT NOT NULL,
  amount DECIMAL(19,4) NOT NULL,
  currency_id INT NOT NULL,
  creation_date TIMESTAMP NOT NULL,
  update_date TIMESTAMP,
  status_id INT NOT NULL,
  comment VARCHAR(4000),
  FOREIGN KEY(from_account_number) REFERENCES bank_account(account_number),
  FOREIGN KEY(to_account_number) REFERENCES bank_account(account_number),
  FOREIGN KEY(currency_id) REFERENCES currency(currency_id),
  FOREIGN KEY(status_id) REFERENCES transaction_status(id)
);
