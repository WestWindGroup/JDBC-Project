SELECT projects.id,name,description,last_name AS project_manager FROM projects,staff WHERE projects_manager_id=staff.id;
-- SELECT staff.id,first_name,last_name,name,salary
-- FROM staff,specialties,staff_specialties,team_staff
-- WHERE staff.id=staff_specialties.staff_id AND
-- specialties.id=specialty_id AND
-- specialties.name='Project Manager' AND
-- team_staff.staff_id=staff.id AND
-- team_staff.team_id IS NULL;
# ALTER TABLE testproject.projects
#   ADD projects_manager_id BIGINT(20) NOT NULL AFTER description;
# CREATE TABLE IF NOT EXISTS teams_typeteam (
#   team_id   BIGINT NOT NULL,
#   typeteam_id BIGINT NOT NULL,
#   FOREIGN KEY (team_id) REFERENCES teams (id),
#   FOREIGN KEY (typeteam_id) REFERENCES typeteam (id),
#
#   UNIQUE (typeteam_id, team_id)
# )
#   ENGINE = InnoDB;
# CREATE DATABASE project;
# DELETE FROM teams WHERE id=1;
# CREATE TABLE IF NOT EXISTS teams_typeteam (
#   type   BIGINT NOT NULL,
#   typeteam_id BIGINT NOT NULL,
#   FOREIGN KEY (typeteam_id) REFERENCES typeteam (id),
#
#   UNIQUE (typeteam_id, type)
# )
#   ENGINE = InnoDB;
-- Table: teams
# CREATE TABLE IF NOT EXISTS teams (
#   id          BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
#   type        BIGINT   NOT NULL,
#   FOREIGN KEY (type) REFERENCES typeteam (id)
# )
#   ENGINE = InnoDB;