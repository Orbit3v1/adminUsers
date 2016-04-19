insert into person(id, first_name, last_name, email, state)
values(1, 'test', 'user', 'test@test.com', 'active');

insert into privilege(id, name, description) values('TEST_P', 'Test privilege', '');
insert into role(id, name, description) values('TEST_R', 'Test role', '');

insert into person_role(person, role) values(1, 'TEST_R');

insert into privilege_action(privilege, action) values('TEST_P', 'WRITE');
insert into privilege_action(privilege, action) values('TEST_P', 'READ');

insert into role_privilege_action(role, privilege, action) values('TEST_R', 'TEST_P', 'WRITE');