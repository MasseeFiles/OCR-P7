
-- Creation des tables de la BDD
CREATE TABLE rating ( --pas de titre au pluriel sur tables
    rating_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    moodys_rating VARCHAR(50) NOT NULL,
    sandp_rating VARCHAR(50) NOT NULL,
    fitch_rating VARCHAR(50) NOT NULL,
    order_number INT NOT NULL,
    );

CREATE TABLE bid_list (
    bid_list_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    account VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    bid_quantity DOUBLE NOT NULL,
    ask_quantity DOUBLE NOT NULL,
    bid DOUBLE NOT NULL,
    ask DOUBLE NOT NULL,
    benchmark VARCHAR(50) NOT NULL,
    bid_list_date TIMESTAMP,
    commentary VARCHAR(200) NOT NULL,
    security VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    trader VARCHAR(50) NOT NULL,
    book VARCHAR(50) NOT NULL,
    creation_name VARCHAR(50) NOT NULL,
    creation_date TIMESTAMP,
    revision_name VARCHAR(200) NOT NULL,
    revision_date TIMESTAMP,
    deal_name VARCHAR(200) NOT NULL,
    deal_type VARCHAR(200) NOT NULL,
    source_list_id VARCHAR(200) NOT NULL,
    side VARCHAR(200) NOT NULL,
    );

CREATE TABLE curve_point (
    curve_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id INT NOT NULL,
    as_of_date TIMESTAMP,
    term DOUBLE NOT NULL,
    curve_value DOUBLE NOT NULL,
    creation_date TIMESTAMP
    PRIMARY KEY (curve_id)
    );

CREATE TABLE rule (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(200) NOT NULL,
    json VARCHAR(200) NOT NULL,
    template VARCHAR(200) NOT NULL,
    sql_str VARCHAR(200) NOT NULL,
    sql_part VARCHAR(200) NOT NULL
    );

CREATE TABLE _user (
    user_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(200) NOT NULL,
    password VARCHAR(200) NOT NULL,
    full_name VARCHAR(200) NOT NULL,
    role VARCHAR(200) NOT NULL,
    );

-- Remplissage table rating
    INSERT INTO rating (moodys_rating , sandprating , fitch_rating , order_number)
    VALUES
    ('moodysRating1' , 'sandpRating1' , 'fitchRating1' , '27'),
    ('moodysRating2' , 'sandpRating2' , 'fitchRating2' , '5'),
    ('moodysRating3' , 'sandpRating3' , 'fitchRating3' , '74');

-- Remplissage table curve_point
    INSERT INTO curve_point ()
      VALUES
      ('1' , '2023-01-01 12:34:56' , '1.01' , '11.01' , CURRENT_TIMESTAMP),
      ('2' , '2023-01-02 12:34:56' , '1.02' , '11.02' , CURRENT_TIMESTAMP),
      ('3' , '2023-01-03 12:34:56' , '1.03' , '11.03' , CURRENT_TIMESTAMP),
