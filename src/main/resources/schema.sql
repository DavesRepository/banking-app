CREATE TABLE users(
     username varchar(255)  NOT NULL,
     email varchar(255)  NOT NULL,
     password varchar(255)  NOT NULL,
     PRIMARY KEY (username)
);

CREATE TABLE currentaccount (
      accountnumber varchar(255)  NOT NULL,
      balance varchar(255)  NOT NULL,
      username varchar(255)  NOT NULL,
      PRIMARY KEY (accountnumber),
      FOREIGN KEY (username) REFERENCES users(username)
);

CREATE TABLE savingsaccount (
     accountnumber varchar(255)  NOT NULL,
     balance varchar(255)  NOT NULL,
     username varchar(255)  NOT NULL,
     PRIMARY KEY (accountnumber),
     FOREIGN KEY (username) REFERENCES users(username)
);
