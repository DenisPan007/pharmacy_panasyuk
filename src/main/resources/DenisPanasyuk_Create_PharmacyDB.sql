create schema if not exists `pharmacy` collate `utf8mb4_0900_ai_ci`;
use `pharmacy`;

create table if not exists `manufacturer`
(
  `id` int(11) unsigned auto_increment
    primary key,
  `name` varchar(45) null
);

create table if not exists `release_form`
(
  `id` int(11) unsigned auto_increment
    primary key,
  `description` varchar(200) null
);

create table if not exists `drug`
(
  `id` int(11) unsigned auto_increment
    primary key,
  `name` varchar(45) not null,
  `is_prescription_required` tinyint default 0 not null,
  `price` int(11) unsigned not null,
  `manufacturer_id` int(11) unsigned not null,
  `release_form_id` int(11) unsigned not null,
  constraint `drug_manufacturer_id_fk`
    foreign key (`manufacturer_id`) references `manufacturer` (`id`),
  constraint `drug_release_form_id_fk`
    foreign key (`release_form_id`) references `release_form` (`id`)
);

create table if not exists `user`
(
  `id` int(11) unsigned auto_increment
    primary key,
  `login` varchar(45) not null,
  `password` char(64) not null,
  `firstname` varchar(45) null,
  `lastname` varchar(45) null,
  `email` varchar(45) not null,
  `role` enum('client', 'doctor', 'admin') not null,
  constraint `email_UNIQUE`
    unique (`email`),
  constraint `login_UNIQUE`
    unique (`login`)
);

create table if not exists `drug_order`
(
  `id` int(11) unsigned auto_increment
    primary key,
  `user_id` int(11) unsigned not null,
  `status` enum('new', 'at_work', 'completed', 'rejected') not null,
  `price` int not null,
  constraint `fk_drug_order_user1`
    foreign key (`user_id`) references `user` (`id`)
      on delete cascade
);

create index `fk_drug_order_user1_idx`
  on `drug_order` (`user_id`);

create table if not exists `drug_order_details`
(
  `id` int(11) unsigned auto_increment
    primary key,
  `drug_amount` smallint(11) unsigned not null,
  `drug_id` int(11) unsigned not null,
  `drug_order_id` int(11) unsigned not null,
  constraint `drug_order_details_drug_order_id_fk`
    foreign key (`drug_order_id`) references `drug_order` (`id`)
      on delete cascade,
  constraint `fk_drug_order_details_drug1`
    foreign key (`drug_id`) references `drug` (`id`)
);

create index `fk_drug_order_details_drug1_idx`
  on `drug_order_details` (`drug_id`);

create table if not exists `prescription`
(
  `id` int(11) unsigned auto_increment
    primary key,
  `description` varchar(255) null,
  `issue_date` date null,
  `validity_date` date null,
  `drug_id` int(11) unsigned not null,
  `doctor_id` int(11) unsigned not null,
  `user_id` int(11) unsigned not null,
  constraint`fk_prescription_drug1`
    foreign key (`drug_id`) references `drug` (`id`),
  constraint `fk_prescription_user1`
    foreign key (`doctor_id`) references user (`id`),
  constraint `fk_prescription_user2`
    foreign key (`user_id`) references user (`id`)
);

create index `fk_prescription_drug1_idx`
  on `prescription` (`drug_id`);

create index `fk_prescription_user1_idx`
  on `prescription` (`doctor_id`);

create index `fk_prescription_user2_idx`
  on `prescription` (`user_id`);