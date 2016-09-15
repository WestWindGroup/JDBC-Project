# SELECT projects.id,name,description,last_name AS project_manager FROM projects,staff WHERE projects_manager_id=staff.id;
# SELECT staff.id,first_name,last_name,name,salary
# FROM staff,specialties,staff_specialties,team_staff
# WHERE staff.id=staff_specialties.staff_id AND
# specialties.id=specialty_id AND
# specialties.name='Project Manager' AND
# team_staff.staff_id=staff.id AND
# team_staff.team_id IS NULL;
# SELECT staff.id,first_name,last_name,age,specialties.name,salary
# FROM staff,specialties,staff_specialties
# WHERE staff.id=staff_specialties.staff_id AND
# staff_specialties.specialty_id=specialties.id AND
# specialties.name='Project Manager';
# ALTER TABLE teams
# ADD team_number BIGINT(20) NOT NULL ;
# UPDATE teams SET team_number=3 WHERE id=3;
DELETE FROM projects WHERE id=8;
# INSERT INTO teams VALUES(id);
# INSERT INTO project_teams VALUE (1,2);
# INSERT INTO teams_typeteam VALUE (2,1);
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
# SELECT projects.id,teams.id,typeteam.name,staff.last_name,staff.id AS id_staff,specialties.name AS specialty,specialties.salary
# FROM projects,teams,typeteam,staff,specialties,staff_specialties,teams_typeteam,project_teams,team_staff
# WHERE projects.id=project_teams.project_id AND
#       project_teams.teams_id=teams.id AND
#       teams.id=teams_typeteam.team_id AND
#       teams_typeteam.typeteam_id=typeteam.id AND
#       staff.id=team_staff.staff_id AND
#       team_staff.team_id=teams.id AND
#       staff_specialties.staff_id=staff.id AND
#       staff_specialties.specialty_id=specialties.id
# ORDER BY teams.id;

# SELECT staff.id,first_name,last_name,age,specialties.name,salary
# FROM staff,specialties,staff_specialties,projects
# WHERE staff.id=staff_specialties.staff_id AND
#       staff_specialties.specialty_id=specialties.id AND
#       specialties.name='Project Manager' AND
#       projects.projects_manager_id<>staff.id;




















