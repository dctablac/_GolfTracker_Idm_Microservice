USE gt_idm;

CREATE TABLE privilege_level (
	plevel INTEGER NOT NULL,
    pname VARCHAR(20) NOT NULL,
    PRIMARY KEY (plevel)
);

CREATE TABLE session_status (
	status_id INTEGER NOT NULL,
    status VARCHAR(20) NOT NULL,
    PRIMARY KEY (status_id)
);

CREATE TABLE user_status (
	status_id INTEGER NOT NULL,
    status VARCHAR(20) NOT NULL,
    PRIMARY KEY (status_id)
);

CREATE TABLE user (
	user_id INTEGER NOT NULL AUTO_INCREMENT,
    email VARCHAR(50) NOT NULL UNIQUE,
    status INTEGER NOT NULL,
    plevel INTEGER NOT NULL,
    salt VARCHAR(8) NOT NULL,
    pword VARCHAR(128) NOT NULL,
    PRIMARY KEY (user_id),
    FOREIGN KEY (plevel) REFERENCES privilege_level(plevel) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (status) REFERENCES user_status(status_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE session (
	session_id VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    status INTEGER NOT NULL,
    time_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    last_used TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    expr_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (status) REFERENCES session_status(status_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (email) REFERENCES user(email) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO session_status (status_id, status) VALUES (1, 'ACTIVE'), (2, 'CLOSED'), (3, 'EXPIRED'), (4, 'REVOKED');
INSERT INTO privilege_level (plevel, pname) VALUES (1, 'ROOT'), (2, 'ADMIN'), (3, 'EMPLOYEE'), (4, 'SERVICE'), (5, 'USER');
INSERT INTO user_status (status_id, status) VALUES (1, 'ACTIVE'), (2, 'CLOSED'), (3, 'LOCKED'), (4, 'REVOKED');
