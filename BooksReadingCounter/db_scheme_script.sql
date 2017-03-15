-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema epam_library
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema epam_library
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `epam_library` DEFAULT CHARACTER SET utf8 ;
USE `epam_library` ;

-- -----------------------------------------------------
-- Table `epam_library`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `epam_library`.`book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NULL,
  `author` VARCHAR(45) NULL,
  `brief` VARCHAR(50) NULL,
  `publish_year` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `epam_library`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `epam_library`.`employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `email` VARCHAR(50) NULL,
  `date_of_birth` DATE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `epam_library`.`employee_book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `epam_library`.`employee_book` (
  `employee_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  `id` INT NULL,
  PRIMARY KEY (`employee_id`, `book_id`),
  INDEX `fk_employee_has_book_book1_idx` (`book_id` ASC),
  INDEX `fk_employee_has_book_employee_idx` (`employee_id` ASC),
  CONSTRAINT `fk_employee_has_book_employee`
    FOREIGN KEY (`employee_id`)
    REFERENCES `epam_library`.`employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_has_book_book1`
    FOREIGN KEY (`book_id`)
    REFERENCES `epam_library`.`book` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
