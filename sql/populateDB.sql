use z233j_JHutter;

INSERT INTO SCENARIO
	(scenarioName)
VALUES ('zuul-toddler');

INSERT INTO ROOM
	(scenarioName, roomName, roomDesc, startRoom)
VALUES
	('zuul-toddler', 'hallway', 'in a hallway. There are shoes strewn everywhere.', 1),
	('zuul-toddler', 'kitchen', 'in a messy kitchen. It''s not toddler-proofed.', 0),
	('zuul-toddler', 'bathroom', 'in a bathroom.', 0),
	('zuul-toddler', 'patio', 'on a patio.', 0),
	('zuul-toddler', 'master bedroom', 'in a master bedroom. There is a feeling a sanctuary here.', 0),
	('zuul-toddler', 'toddler bedroom', 'in a child''s bedroom. It is suffocatingly pink.', 0),
	('zuul-toddler', 'living room', 'in a living room. Don''t step on any legos.', 0)
;


INSERT INTO ROOM_EXITS
	(scenarioName, roomName, direction, exitRoom)
VALUES
	('zuul-toddler', 'hallway', 'west', 'master bedroom'),
	('zuul-toddler', 'hallway', 'east', 'bathroom'),
	('zuul-toddler', 'hallway', 'north', 'living room'),
	('zuul-toddler', 'master bedroom', 'east', 'hallway'),
	('zuul-toddler', 'bathroom', 'west', 'hallway'),
	('zuul-toddler', 'living room', 'south', 'hallway'),
	('zuul-toddler', 'living room', 'north', 'patio'),
	('zuul-toddler', 'living room', 'west', 'kitchen'),
	('zuul-toddler', 'living room', 'east', 'toddler bedroom'),
	('zuul-toddler', 'patio', 'south', 'living room'),
	('zuul-toddler', 'kitchen', 'east', 'living room'),
	('zuul-toddler', 'toddler bedroom', 'west', 'living room')
;