INSERT INTO `testdb`.`manager` (`id`, `password`, `role`, `username`) VALUES ('2', 'password2', 'MANAGER', 'manager2@email');
INSERT INTO `testdb`.`manager` (`id`, `password`, `role`, `username`) VALUES ('1', 'password1', 'MANAGER', 'manager1@email');


INSERT INTO `testdb`.`admin` (`id`, `password`, `role`, `username`) VALUES ('1', 'password1', 'ADMIN', 'admin1@mail');
INSERT INTO `testdb`.`admin` (`id`, `password`, `role`, `username`) VALUES ('2', 'password2', 'ADMIN', 'admin2@mail');


INSERT INTO `testdb`.`user` (`id`, `count`, `name`, `phone`) VALUES ('1', '0', 'user1', '123');
INSERT INTO `testdb`.`user` (`id`, `count`, `name`, `phone`) VALUES ('2', '1', 'user2', '456');


INSERT INTO `testdb`.`taxi` (`taxi_id`, `car_class`, `car_registration_sign`, `is_free`, `location`) VALUES ('1', 'ECONOMY', '1234', true, 'Россия, Москва, станция метро Парк Победы');
INSERT INTO `testdb`.`taxi` (`taxi_id`, `car_class`, `car_registration_sign`, `is_free`, `location`) VALUES ('2', 'LUX', '5678', false, 'Россия, Москва, станция метро Кутузовская');


