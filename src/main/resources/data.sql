INSERT INTO BUILDING(ID, NAMEB) VALUES(-100, 'CoursFauriel');
INSERT INTO BUILDING(ID, NAMEB) VALUES(-99, '158');

INSERT INTO ROOM(ID, NAME, FLOOR, BUILDING_ID) VALUES(-10, 'Room1', 1, -100);
INSERT INTO ROOM(ID, NAME, FLOOR, BUILDING_ID) VALUES(-9, 'Room2', 1, -100);
INSERT INTO ROOM(ID, NAME, FLOOR, BUILDING_ID) VALUES(-11, 'Room3', 1, -99);
INSERT INTO ROOM(ID, NAME, FLOOR, BUILDING_ID) VALUES(-12, 'Room4', 1, -99);

INSERT INTO LIGHT(ID, LEVEL, STATUS, ROOM_ID) VALUES (-1, 8, 'OFF', -10);
INSERT INTO LIGHT(ID, LEVEL, STATUS, ROOM_ID) VALUES (-2, 0, 'OFF', -10);
INSERT INTO LIGHT(ID, LEVEL, STATUS, ROOM_ID) VALUES (-3, 8, 'OFF', -9);
INSERT INTO LIGHT(ID, LEVEL, STATUS, ROOM_ID) VALUES (-4, 0, 'OFF', -9);

