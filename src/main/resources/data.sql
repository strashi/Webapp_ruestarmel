DROP TABLE IF EXISTS dbuser;

CREATE TABLE dbuser (
                        id INT AUTO_INCREMENT  PRIMARY KEY,
                        username VARCHAR(250) NOT NULL,
                        password VARCHAR(250) NOT NULL,
                        role VARCHAR(250) NOT NULL
);
INSERT INTO dbuser (username, password, role) VALUES ('visiteur', '$2y$10$7/jI2A3k0/zvHvojsM4H7em.Y4YLuL0iP8YOZ7.B/./Tr5fTQHr/K', 'USER'),
                                                     ('admin', '$2y$10$CVQkVagxNeFJOf38whHDbejc4b8BCqAXD0sVsQNIKX5HDpYtc5J.e', 'ADMIN');