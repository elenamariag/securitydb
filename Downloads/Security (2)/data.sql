INSERT INTO `securitydb`.`company` (`company_id`, `company_address`, `company_name`, `email`, `password`) VALUES ('1', 'Amsterdam', 'Amazon', 'elena@amazon.com', 'elena');

INSERT INTO `securitydb`.`role` (`id`, `name`) VALUES ('1', 'ADMIN');
INSERT INTO `securitydb`.`role` (`id`, `name`) VALUES ('2', 'CUSTOMER');

INSERT INTO `securitydb`.`user` (`user_id`, `address`, `date_of_birth`, `email`, `first_name`, `last_name`, `user_type`, `company_id`, `password`, `username`) VALUES ('1', 'Amsterdam', '01-01-1997', 'admin', 'Elena', '', 'ADMIN', '1', '$2a$12$YV0hxNA15lKQ2tLJAKPe2eCGryvaeIG3vlNIVABYDuqQnOKh4OMay', 'admin');

INSERT INTO `securitydb`.`user_role` (`user_id`, `role_id`) VALUES ('1', '1');

