drop table  if exists user_transaction;
drop table  if exists bank_account;
drop table  if exists bank_user;


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
  currency VARCHAR(5) NOT NULL  check (currency IN ('USD', 'INR', 'GBP')),
  FOREIGN KEY(user_id) REFERENCES bank_user(user_id)
);

create TABLE IF NOT EXISTS user_transaction (
  transaction_id BIGINT PRIMARY KEY  auto_increment,
  from_account_number BIGINT NOT NULL,
  to_account_number BIGINT NOT NULL,
  amount DECIMAL(19,4) NOT NULL,
  currency VARCHAR(5) NOT NULL  check (currency IN ('USD', 'INR', 'GBP')),
  creation_date TIMESTAMP NOT NULL,
  update_date TIMESTAMP NOT NULL,
  transaction_status VARCHAR(15) NOT NULL  check (transaction_status IN ('Scheduled', 'In Progress', 'Failed', 'Passed')),
  comments VARCHAR(4000) NOT NULL,
  FOREIGN KEY(from_account_number) REFERENCES bank_account(account_number),
  FOREIGN KEY(to_account_number) REFERENCES bank_account(account_number)
);