create table `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(`id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `time_table` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `start_time` varchar(10) NOT NULL,
  `end_time` varchar(10) NOT NULL,
  `title` varchar(100) NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  `description` MEDIUMTEXT,
  `status` int(10) unsigned NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT TRUE,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(`id`),
  CONSTRAINT `FK_time_table_to_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_time_table_to_work_status` FOREIGN KEY (`status`) REFERENCES `work_status` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `work_status` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY(`id`),
  UNIQUE KEY `status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into user(user_name) VALUES ('akhilesh');
insert into user(user_name) VALUES ('diwakar');
insert into user(user_name) VALUES ('rachit');
insert into user(user_name) VALUES ('sorabh');
insert into user(user_name) VALUES ('meeting');

insert into work_status(status) VALUES ('UnAssigned');
insert into work_status(status) VALUES ('NotYetStarted');
insert into work_status(status) VALUES ('WIP');
insert into work_status(status) VALUES ('Done');
insert into work_status(status) VALUES ('OnHold');
