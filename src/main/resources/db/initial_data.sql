

INSERT INTO currency (currency_id, currency_name, currency_code)
VALUES
  (1, 'US Dollar','USD'),
  (2, 'Rupee', 'INR'),
  (3, 'Pound', 'GBP');

INSERT INTO transaction_status (id, name)
VALUES
       (1, 'Scheduled'),
       (2, 'In Process'),
       (3, 'Failed'),
       (4, 'Passed');


 INSERT INTO bank_user (user_id, user_name)
VALUES
       (1, 'Pravinkumar Singh'),
       (2, 'Paul'),
       (3, 'Adam'),
       (4, 'Robin');

    INSERT INTO bank_account (account_number, user_id,account_type,balance,blocked_amount,currency_id)
VALUES
       (1, 1,'Saving',100.25,0.0,1),
       (2, 2,'Saving',200.25,0.0,1),
       (3, 3,'Saving',300.25,0.0,2),
       (4, 4,'Saving',400.25,0.0,3);