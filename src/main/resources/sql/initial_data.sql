INSERT INTO public._users(reg_date,email, password, identifier)
    VALUES (current_timestamp, 'dev@semantyca.com', 'secret','admin');


INSERT INTO public._groups(reg_date, email, groupname)
VALUES (current_timestamp, 'dev@semantyca.com', 'admins');