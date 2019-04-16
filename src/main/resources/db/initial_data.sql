
 insert into bank_user (user_id, user_name)
values
       (1, 'Pravinkumar Singh'),
       (2, 'Paul'),
       (3, 'Adam'),
       (4, 'Robin'),
       (5, 'Rachel');

    insert into bank_account (account_number, user_id,account_type,balance,blocked_amount,currency)
values
       (1, 1,'Saving',100.25,0.0,'USD'),
       (2, 2,'Saving',200.25,0.0,'USD'),
       (3, 3,'Saving',300.25,0.0,'USD'),
       (4, 4,'Saving',400.25,0.0,'INR'),
       (5, 5,'Saving',500.25,0.0,'GBP');


    insert into user_transaction (from_account_number,to_account_number,amount,currency,creation_date,update_date,transaction_status,comments)
values
         (1,2,12.2,'USD',now(),now(),'Scheduled','Initiated');