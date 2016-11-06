-- MySQL Workbench Synchronization
-- Generated: 2015-05-12 10:31
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Veaceslav Macari

use nodes;

-- SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
--  SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
-- SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

drop table `nodes`.`node`;
CREATE TABLE IF NOT EXISTS `nodes`.`node` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(256) NULL DEFAULT NULL,
  `battery_level` INT(11) NULL DEFAULT 0,
  `configuration` TEXT NULL DEFAULT NULL,
  `protocol` VARCHAR(45) NULL DEFAULT NULL,
  `fw_version` VARCHAR(45) NULL DEFAULT NULL,
  `is_rebooting` TINYINT(1) NULL DEFAULT 0,
  `last_update_time` DATETIME NULL DEFAULT NULL,
  `version` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

drop table `nodes`.`data`;
CREATE TABLE IF NOT EXISTS `nodes`.`data` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'The table holds received data ',
  `data` VARCHAR(100) NULL DEFAULT NULL,
  `data_type` VARCHAR(50) NULL DEFAULT NULL,
  `node_id` INT(11) NULL DEFAULT NULL,
  `sensor_id` INT(11) NULL DEFAULT NULL,
  `time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_node_id_idx` (`node_id` ASC)
  )

ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

drop table `nodes`.`sensor`;
CREATE TABLE IF NOT EXISTS `nodes`.`sensor` (

  `id` INT(11) NOT NULL,
  `node_id` INT(11) NOT NULL,

  `name` VARCHAR(150) NULL DEFAULT NULL,
  `type` INT(11) NULL DEFAULT NULL,

  PRIMARY KEY (`id`, `node_id`),
  INDEX `fk_node_id_idx` (`node_id` ASC),
  FOREIGN KEY (`node_id`) REFERENCES `nodes`.`node` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION)


ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_general_ci;

drop table `nodes`.`log`;
CREATE TABLE IF NOT EXISTS `nodes`.`log` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `time` DATETIME NULL DEFAULT NULL,
  `message` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
