CREATE TABLE session(
  sessionID int NOT NULL,
  date VARCHAR(50) NOT NULL,
  durationInMinutes int NOT NULL,
  form tinyint,
  performance tinyint,
  isTemplate boolean NOT NULL,
  PRIMARY KEY (sessionID),
  isOutDoor    BOOL,
  temperature    FLOAT    NOT NULL,
  weatherType    VarChar(50)    NOT NULL,
  airQuality    int    NOT NULL,
  ventilation    int    NOT NULL,
  peopleWatchingMe    int    NOT NULL
);
CREATE TABLE notes(
  noteID int NOT NULL,
  purpose VarChar(50) NOT NULL,
  PRIMARY KEY (noteID),
  tips VarChar(50) NOT NULL
);
CREATE TABLE exercise(
  name VarChar(50) NOT NULL,
  description VarChar(50),
  PRIMARY KEY (name),
  isCardio        BOOL,
  length_KM    double    NOT NULL,
  length_minutes    int    NOT NULL,
  weight    Double    NOT NULL,
  reps    int    NOT NULL,
  sets    int    NOT NULL
);
CREATE TABLE exerciseresult(
  exerciseresultID int  NOT NULL,
  PRIMARY KEY(exerciseresultID),
  isCardio        BOOL,
  length_KM    double    NOT NULL,
  length_minutes    int    NOT NULL,
  weight    Double    NOT NULL,
  reps    int    NOT NULL,
  sets    int    NOT NULL
);
CREATE TABLE groups(
  ID    int    NOT NULL,
  PRIMARY KEY(ID),
  name    VarChar(50)    NOT NULL
);
ALTER TABLE db.session
ADD COLUMN exercise VarChar(50),
ADD FOREIGN KEY fk_name(exercise) REFERENCES db.exercise(name)
;

ALTER TABLE db.notes
ADD COLUMN sessionID int NOT NULL,
ADD FOREIGN KEY fk_name(sessionID) REFERENCES db.session(sessionID)
;

ALTER TABLE db.exercise
ADD COLUMN exerciseresultID int,
ADD FOREIGN KEY fk_name1(exerciseresultID) REFERENCES db.exerciseresult(exerciseresultID)
;

ALTER TABLE db.exercise
ADD COLUMN replacements VarChar(50),
ADD FOREIGN KEY fk_name2(replacements) REFERENCES db.exercise(name)
;

ALTER TABLE db.exercise
ADD COLUMN groupID int,
ADD FOREIGN KEY fk_name3(groupID) REFERENCES db.groups(ID)
;

ALTER TABLE db.exerciseresult
ADD COLUMN exercise VarChar(50),
ADD FOREIGN KEY fk_name(exercise) REFERENCES db.exercise(name)
;

ALTER TABLE db.groups
ADD COLUMN category int NOT NULL,
ADD FOREIGN KEY fk_name1(category) REFERENCES db.groups(ID)
;

ALTER TABLE db.groups
ADD COLUMN exercise VarChar(50),
ADD FOREIGN KEY fk_name2(exercise) REFERENCES db.exercise(name)
;
CREATE TABLE template(templateID int NOT NULL,  durationInMinutes int NOT NULL,  isOutDoor boolean NOT NULL,  exerciseID varchar(50), PRIMARY KEY(templateID), FOREIGN KEY (exerciseID) REFERENCES exercise(name));

INSERT INTO exerciseresult(exerciseresultID, isCardio, length_KM, length_minutes, weight, reps, sets) VALUES (1, true, 2, 2, 2, 2, 2);
INSERT INTO exerciseresult(exerciseresultID, isCardio, length_KM, length_minutes, weight, reps, sets) VALUES (2, true, 2, 2, 2, 2, 2);
INSERT INTO exerciseresult(exerciseresultID, isCardio, length_KM, length_minutes, weight, reps, sets) VALUES (3, false, 0, 0, 420, 2, 2);
INSERT INTO exerciseresult(exerciseresultID, isCardio, length_KM, length_minutes, weight, reps, sets) VALUES (4, false, 0, 0, 360, 2, 2);

INSERT INTO exercise (name, description, isCardio, length_KM, length_minutes, weight, reps, sets) VALUES ("Gainz ecercise", "SUPER STRENGTH Maximizes gains", FALSE , 0, 30, 420, 12, 4);
INSERT INTO exercise (name, description, isCardio, length_KM, length_minutes, weight, reps, sets) VALUES ("Super Cardio", "SUPER CARDIO Maximizes air intake", TRUE , 420, 9000, 0, 1, 1);
