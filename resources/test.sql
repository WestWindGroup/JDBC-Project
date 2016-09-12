
-- SELECT staff.id,first_name,last_name,name,salary
-- FROM staff,specialties,staff_specialties,team_staff
-- WHERE staff.id=staff_specialties.staff_id AND
-- specialties.id=specialty_id AND
-- specialties.name='Project Manager' AND
-- team_staff.staff_id=staff.id AND
-- team_staff.team_id IS NULL;
ALTER TABLE projects
  ADD projects_manager_id BIGINT(20) NOT NULL AFTER description;


