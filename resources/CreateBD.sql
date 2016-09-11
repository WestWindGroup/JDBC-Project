-- Table: staff
CREATE TABLE IF NOT EXISTS staff (
  id         BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50)  NOT NULL,
  last_name  VARCHAR(100) NOT NULL,
  age        INT          UNSIGNED NOT NULL,
  INDEX (last_name)
)
  ENGINE = InnoDB;

-- Table: specialties
CREATE TABLE IF NOT EXISTS specialties (
  id   INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  salary  DOUBLE   UNSIGNED NOT NULL,
  INDEX (name)
)
  ENGINE = InnoDB;

-- Table for mapping staff and specialties: staff_specialties
CREATE TABLE IF NOT EXISTS staff_specialties (
  staff_id BIGINT NOT NULL,
  specialty_id INT    NOT NULL,
  FOREIGN KEY (staff_id) REFERENCES staff (id),
  FOREIGN KEY (specialty_id) REFERENCES specialties (id),

  UNIQUE (staff_id, specialty_id)
)
  ENGINE = InnoDB;

-- Table: projects
CREATE TABLE IF NOT EXISTS projects (
  id          BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(50)   NOT NULL,
  description VARCHAR(1000) NOT NULL,
  INDEX (name)
)
  ENGINE = InnoDB;

-- Table: teams
CREATE TABLE IF NOT EXISTS teams (
  id          BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
  type        BIGINT   NOT NULL
)
  ENGINE = InnoDB;

-- Table: typeteam
CREATE TABLE IF NOT EXISTS typeteam(
  id          BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(1000)   NOT NULL
  
)
  ENGINE = InnoDB;
  
  CREATE TABLE IF NOT EXISTS teams_typeteam (
  teams_id   BIGINT NOT NULL,
  typeteam_id BIGINT NOT NULL,
  FOREIGN KEY (teams_id) REFERENCES teams (id),
  FOREIGN KEY (typeteam_id) REFERENCES typeteam (id),

  UNIQUE (typeteam_id, teams_id)
)
  ENGINE = InnoDB;

-- Table for mapping projects and teams : project_teams 
CREATE TABLE IF NOT EXISTS project_teams  (
  project_id   BIGINT NOT NULL,
  teams_id BIGINT NOT NULL,
  FOREIGN KEY (project_id) REFERENCES projects (id),
  FOREIGN KEY (teams_id) REFERENCES teams (id),

  UNIQUE (project_id, teams_id)
)
  ENGINE = InnoDB;


-- Table for mapping team and developers: team_developers
CREATE TABLE IF NOT EXISTS team_staff (
  team_id   BIGINT NOT NULL,
  staff_id BIGINT NOT NULL,
  FOREIGN KEY (team_id) REFERENCES teams (id),
  FOREIGN KEY (staff_id) REFERENCES staff (id),

  UNIQUE (team_id, staff_id)
)
  ENGINE = InnoDB;