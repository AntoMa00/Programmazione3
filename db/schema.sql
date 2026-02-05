CREATE TABLE Utente (
    username      VARCHAR(64) PRIMARY KEY,
    password      VARCHAR(256) NOT NULL,
    nome          VARCHAR(16) NOT NULL,
    cognome       VARCHAR(16) NOT NULL,
    dataNascita  DATE NOT NULL,
    sesso         CHAR(1),
    nTel         CHAR(10),
    email         VARCHAR(80)
);

CREATE TABLE Amministratore (
    username      VARCHAR(64) PRIMARY KEY,
    password      VARCHAR(256) NOT NULL,
    nome          VARCHAR(16) NOT NULL,
    cognome       VARCHAR(16) NOT NULL,
    dataNascita  DATE NOT NULL,
    sesso         CHAR(1),
    nTel         CHAR(10),
    email         VARCHAR(80)
);


CREATE TABLE ProdottoAcquistabile (
    codice        CHAR(4) PRIMARY KEY,
    nome          VARCHAR(16) NOT NULL,
    descrizione   VARCHAR(256),
    quantita      INT NOT NULL,
    costo         DECIMAL(6,2) NOT NULL,
    categoria     VARCHAR(32),
    username      VARCHAR(64) NOT NULL,

    CONSTRAINT fk_pa_utente
        FOREIGN KEY (username)
            REFERENCES Utente(username)
);


CREATE TABLE Carrello (
    codice        CHAR(4) PRIMARY KEY,
    nome          VARCHAR(16) NOT NULL,
    descrizione   VARCHAR(256),
    quantita      INT NOT NULL,
    costo         DECIMAL(6,2) NOT NULL,
    categoria     VARCHAR(32),
    username      VARCHAR(64) NOT NULL,

    CONSTRAINT fk_carrello_utente
        FOREIGN KEY (username)
            REFERENCES Utente(username)
);


CREATE TABLE ProdottoAcquistato (
    codice        CHAR(4) PRIMARY KEY,
    nome          VARCHAR(16) NOT NULL,
    descrizione   VARCHAR(256),
    quantita      INT NOT NULL,
    costo         DECIMAL(6,2) NOT NULL,
    categoria     VARCHAR(32),
    username      VARCHAR(64) NOT NULL,

    CONSTRAINT fk_pacq_utente
        FOREIGN KEY (username)
            REFERENCES Utente(username)
);
