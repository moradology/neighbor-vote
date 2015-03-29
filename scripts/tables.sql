DROP TABLE IF EXISTS voters;
CREATE TABLE voters (
        geocoded_location VARCHAR(255),
        tigerline_id INTEGER,
        geocode_status VARCHAR(255) NOT NULL,
        geocode_score VARCHAR(255),
        address VARCHAR(255) NOT NULL,
        tigerline_side VARCHAR(4),
        id VARCHAR(12) PRIMARY KEY NOT NULL,
        x FLOAT,
        y FLOAT,
        lastname VARCHAR(255) NOT NULL,
        firstname VARCHAR(255) NOT NULL,
        middlename VARCHAR(255),
        dob DATE NOT NULL,
        streetaddress VARCHAR(255) NOT NULL,
        city VARCHAR(255) NOT NULL,
        state VARCHAR(255) NOT NULL,
        zip INTEGER NOT NULL,
        lastvotedate DATE,
        affiliation VARCHAR(255),
        status VARCHAR(1) NOT NULL
);

SELECT AddGeometryColumn ('public','voters','geom',4326,'POINT',2);
