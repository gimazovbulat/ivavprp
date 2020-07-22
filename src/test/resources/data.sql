-- skills
INSERT INTO ivavprp.skill (id, name, points)
VALUES (1, 'Python', 10);
INSERT INTO ivavprp.skill (id, name, points)
VALUES (2, 'Spring Data', 10);
INSERT INTO ivavprp.skill (id, name, points)
VALUES (3, 'Kotlin', 10);
INSERT INTO ivavprp.skill (id, name, points)
VALUES (4, 'Spring Security', 10);
INSERT INTO ivavprp.skill (id, name, points)
VALUES (5, 'HTML', 5);
INSERT INTO ivavprp.skill (id, name, points)
VALUES (6, 'Linux', 5);
-- achievements
INSERT INTO ivavprp.achievement (id, name, path, points)
VALUES (1, 'Java Beginner', 'path1', 20);
INSERT INTO ivavprp.achievement (id, name, path, points)
VALUES (2, 'Java Junior', 'path2', 30);
-- skills_achievements
INSERT INTO ivavprp.skills_achievements (skill_id, achievement_id)
VALUES (2, 1);
INSERT INTO ivavprp.skills_achievements (skill_id, achievement_id)
VALUES (3, 1);
INSERT INTO ivavprp.skills_achievements (skill_id, achievement_id)
VALUES (6, 1);
INSERT INTO ivavprp.skills_achievements (skill_id, achievement_id)
VALUES (7, 1);
INSERT INTO ivavprp.skills_achievements (skill_id, achievement_id)
VALUES (2, 2);
INSERT INTO ivavprp.skills_achievements (skill_id, achievement_id)
VALUES (3, 2);
INSERT INTO ivavprp.skills_achievements (skill_id, achievement_id)
VALUES (6, 2);
INSERT INTO ivavprp.skills_achievements (skill_id, achievement_id)
VALUES (7, 2);
INSERT INTO ivavprp.skills_achievements (skill_id, achievement_id)
VALUES (4, 2);
INSERT INTO ivavprp.skills_achievements (skill_id, achievement_id)
VALUES (5, 2);
