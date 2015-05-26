BEGIN TRANSACTION;
CREATE TABLE `place` (
	`pk_id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`location`	TEXT,
	`latitude`	REAL,
	`longitude`	REAL,
	`description`	INTEGER,
	`image`	BLOB
);
INSERT INTO `place` VALUES (1,'Somewhere',23.134444,113.3,'A Place',NULL);
COMMIT;
