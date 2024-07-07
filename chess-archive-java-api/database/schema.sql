CREATE TABLE category (
	category_id serial PRIMARY KEY,
	category_name varchar(200)
);

CREATE TABLE source_material (
	source_id serial PRIMARY KEY,
	source_name varchar(200)
);

CREATE TABLE opening (
	opening_id serial PRIMARY KEY,
	source_id int REFERENCES source_material(source_id),
	source_page int,
	source_subnumber int,
	pgn varchar(800),
	notes varchar(1000)
);

CREATE TABLE opening_category (
	category_id int REFERENCES category(category_id),
	opening_id int REFERENCES opening(opening_id),
	CONSTRAINT PK_opening_category PRIMARY KEY (category_id, opening_id)
);