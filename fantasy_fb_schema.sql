CREATE TABLE IF NOT EXISTS nflteam (
	
	team_id SERIAL PRIMARY KEY,
	team_name VARCHAR(30) NOT NULL,
	city VARCHAR(20) NOT NULL,
	conference CHAR(3),
	division VARCHAR(5)
	
);

CREATE TABLE IF NOT EXISTS nflposition (

	position_id SERIAL PRIMARY KEY,
	position_name VARCHAR(7) UNIQUE NOT NULL, -- change to 3 depending on DEF or DEFENSE
	position_type VARCHAR(20)
	
);

CREATE TABLE IF NOT EXISTS nflplayer (

	player_id SERIAL PRIMARY KEY,
	first_name VARCHAR(25) NOT NULL,
	last_name VARCHAR(25) NOT NULL,
	position_id INT NOT NULL REFERENCES nflposition(position_id),
	team_id INT NOT NULL REFERENCES nflteam(team_id),
	status VARCHAR(20),
	birthdate DATE,
	height INT,
	weight INT
	 
);

CREATE TABLE IF NOT EXISTS nflgame (

	game_id SERIAL PRIMARY KEY,
	season_year INT NOT NULL,
	week INT NOT NULL,
	game_date DATE NOT NULL,
	home_team_id INT REFERENCES nflteam(team_id),
	away_team_id INT REFERENCES nflteam(team_id),
	home_team_score INT,
	away_team_score INT,
	status VARCHAR(20),
	UNIQUE (season_year, week, home_team_id, away_team_id)

);

CREATE TABLE IF NOT EXISTS nflstat (

	stat_id SERIAL PRIMARY KEY,
	stat_code VARCHAR(10) UNIQUE NOT NULL,
	stat_name VARCHAR(20) NOT NULL,
	category VARCHAR(20) NOT NULL,
	unit VARCHAR(20)
	
);

CREATE TABLE IF NOT EXISTS nflplayergamestat (

	player_game_stat_id SERIAL PRIMARY KEY,
	player_id INT NOT NULL REFERENCES nflplayer(player_id),
	game_id INT NOT NULL REFERENCES nflgame(game_id),
	stat_id INT NOT NULL REFERENCES nflstat(stat_id),
	game_stat_value NUMERIC(10,2) NOT NULL,
	UNIQUE (player_id, game_id, stat_id)
	
);

CREATE TABLE IF NOT EXISTS nflplayerseasonstat (

	player_season_stat_id SERIAL PRIMARY KEY,
	player_id INT NOT NULL REFERENCES nflplayer(player_id),
	season INT NOT NULL,
	stat_id INT NOT NULL REFERENCES nflstat(stat_id),
	season_stat_value NUMERIC(10,2),
	UNIQUE (player_id, season, stat_id)
	
);

CREATE TABLE IF NOT EXISTS fantasyscoring (

	scoring_id SERIAL PRIMARY KEY,
	scoring_text VARCHAR (10) NOT NULL,
	stat_id INT NOT NULL REFERENCES nflstat(stat_id),
	pounts NUMERIC(10,2) NOT NULL
	
);
