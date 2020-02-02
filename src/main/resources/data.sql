DELETE FROM exercise_type;

INSERT INTO exercise_type (id, exercise_type, additional_info) VALUES
    (1, 'Strength', 'Strength type of exercise'),
    (2, 'Endurance', 'Endurance / Aerobic type of exercise'),
    (3, 'Flexibility', 'Flexibility type of exercise');

DELETE FROM muscles;

INSERT INTO muscles (id, muscles_name, body_part) VALUES
    (1, 'Front delt', 'Shoulders'),
    (2, 'Rear delt', 'Shoulders'),
    (3, 'Bicep', 'Arms'),
    (4, 'Triceps', 'Arms'),
    (5, 'Breast', 'Torso'),
    (6, 'Trapeze', 'Shoulders'),
    (7, 'Higher back', 'Back'),
    (8, 'Lower back', 'Back'),
    (9, 'Buttock', ' Buttock'),
    (10, 'Hamstring', 'Legs'),
    (11, 'Quadricep', 'Legs'),
    (12, 'Abdominal straight', 'Abdomen'),
    (13, 'Abdominal diagonal', 'Abdomen'),
    (14, 'Calf high', 'Calves'),
    (15, 'Calf low', 'Calves');

DELETE FROM exercise;

INSERT INTO exercise (id, exercise_name, description, exercise_type_id) VALUES
    (1, 'Bench Press', 'Bench Press description', 1),
    (2, 'Front Squats', 'Front Squats description', 1),
    (3, 'Pull Ups', 'Pull Ups description', 1),
    (4, 'Swimming', 'Swimming description', 2),
    (5, 'Jogging', 'Jogging description', 2),
    (6, 'Burpees', 'Burpees description', 2),
    (7, 'Climbing', 'Climbing description', 2),
    (8, 'Suzuki Stretching', 'Suzuki Stretching', 3);

DELETE FROM exercise_muscles;

INSERT INTO exercise_muscles (exercise_id, muscles_id) VALUES
    (1, 1),
    (1, 4),
    (1, 5),
    (2, 1),
    (2, 10),
    (2, 11),
    (3, 3),
    (3, 7),
    (4, 7),
    (4, 8),
    (5, 10),
    (5, 11),
    (5, 12),
    (5, 13),
    (5, 14),
    (5, 15),
    (6, 1),
    (6, 4),
    (6, 5),
    (6, 10),
    (6, 11),
    (6, 12),
    (6, 13),
    (6, 14),
    (6, 15),
    (7, 3),
    (7, 7);
