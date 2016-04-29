---@author JHutter
---@date 2015-11-25
---a8 database creation


USE z233j_JHutter;

CREATE TABLE SCENARIO(
	scenarioName		VARCHAR(20)		NOT NULL,
    
    CONSTRAINT			SCENARIO_PK		PRIMARY KEY(scenarioName)
);

CREATE TABLE ROOM(
	scenarioName		VARCHAR(20)		NOT NULL,
    roomName			VARCHAR(20)		NOT NULL,
    roomDesc			VARCHAR(255)	NOT NULL,
    startRoom			INT				NOT NULL, ---1 if yes, 0 if no
    
    CONSTRAINT			ROOM_PK			PRIMARY KEY(scenarioName, roomName),
    CONSTRAINT			ROOM_FK1		FOREIGN KEY(scenarioName)
										REFERENCES SCENARIO(scenarioName)
);

CREATE TABLE ROOM_EXITS(
	scenarioName		VARCHAR(20)		NOT NULL,
    roomName			VARCHAR(20)		NOT NULL,
    direction			VARCHAR(10)		NOT NULL,
    exitRoom			VARCHAR(20)		NOT NULL,
    
    CONSTRAINT			RE_PK			PRIMARY KEY(scenarioName, roomName, direction),
    CONSTRAINT			RE_FK1			FOREIGN KEY(scenarioName, roomName)
										REFERENCES ROOM(scenarioName,roomName),
	CONSTRAINT			RE_FK3			FOREIGN KEY(scenarioName, exitRoom)
										REFERENCES ROOM(scenarioName, roomName)
);