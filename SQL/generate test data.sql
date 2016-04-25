insert into person(id, first_name, last_name, email, active, login, password)
values(1, 'test', 'user', 'test@test.com', 'Y', 'user', '1234');

insert into privilege(id, name, description) values('TEST_P', 'Test privilege', '');
insert into role(id, name, description) values('TEST_R', 'Test role', '');

insert into person_role(person, role) values(1, 'TEST_R');

insert into privilege_action(privilege, action) values('TEST_P', 'WRITE');
insert into privilege_action(privilege, action) values('TEST_P', 'READ');

insert into role_privilege_action(role, privilege, action) values('TEST_R', 'TEST_P', 'WRITE');

insert into privilege(id, name, description) values('TEST_P2', 'Test privilege2', '');
insert into privilege(id, name, description) values('TEST_P3', 'Test privilege3', '');
insert into privilege(id, name, description) values('TEST_P4', 'Test privilege4', '');


insert into privilege_action(privilege, action) values('TEST_P2', 'WRITE');
insert into privilege_action(privilege, action) values('TEST_P2', 'READ');
insert into privilege_action(privilege, action) values('TEST_P3', 'WRITE');
insert into privilege_action(privilege, action) values('TEST_P4', 'READ');