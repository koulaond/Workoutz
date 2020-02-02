DROP TABLE IF EXISTS exercise_muscles;
DROP TABLE IF EXISTS exercise;
DROP TABLE IF EXISTS exercise_type;
DROP TABLE IF EXISTS muscles;

CREATE TABLE exercise_type (
    id                  INT                 NOT NULL        PRIMARY KEY,
    exercise_type       VARCHAR(255)        NOT NULL,
    additional_info     VARCHAR(255)        NOT NULL
);

CREATE TABLE muscles (
    id                  INT                 NOT NULL        PRIMARY KEY,
    muscles_name        VARCHAR(255)        NOT NULL,
    body_part           VARCHAR(255)        NOT NULL
);

CREATE TABLE exercise (
    id                  INT                 NOT NULL        PRIMARY KEY,
    exercise_name       VARCHAR(255)        NOT NULL,
    description         VARCHAR(255)        NOT NULL,
    exercise_type_id    INT                 NOT NULL,
    FOREIGN KEY (`exercise_type_id`) REFERENCES exercise_type (`id`) ON DELETE CASCADE
);

CREATE TABLE exercise_muscles (
    exercise_id         INT                 NOT NULL,
    muscles_id          INT                 NOT NULL
);