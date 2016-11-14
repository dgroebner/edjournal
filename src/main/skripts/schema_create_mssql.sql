CREATE TABLE journalfile (
  id INT IDENTITY(1,1) NOT NULL,
  filename VARCHAR(50),
  readdate datetime not null,
  CONSTRAINT pk_journalfile PRIMARY KEY clustered (id)
  );
CREATE UNIQUE INDEX i_journalfile_filename ON journalfile (filename);

CREATE TABLE journal (
  id INT IDENTITY(1,1) NOT NULL,
  journalfile_id INT NOT NULL,
  timestamp datetime NOT NULL,
  event VARCHAR(50) NOT NULL,
  message VARCHAR(max) NOT NULL,
  CONSTRAINT pk_journal PRIMARY KEY clustered (id),
  CONSTRAINT fk_journal_journalfile FOREIGN KEY (journalfile_id) REFERENCES journalfile (ID) ON DELETE CASCADE
);
CREATE INDEX i_journal_eventTimestamp on journal (event, timestamp);

--drop table material
CREATE TABLE material (
	id INT IDENTITY(1,1) NOT NULL,
	name varchar(200) NULL,
	inara_url varchar(1000) NULL,
	kuerzel varchar(2) NULL,
	ed_name varchar(200) NOT NULL,
	category varchar(50) NULL,
	stock int NOT NULL default 0
	CONSTRAINT pk_material PRIMARY KEY clustered (id)
)
CREATE INDEX i_material_edname on material(ed_name);

--drop table commlog
CREATE TABLE commlog (
  id INT IDENTITY(1,1) NOT NULL,
  journal_id INT NOT NULL,
  timestamp datetime NOT NULL,
  sender varchar(50) NOT NULL,
  receiver varchar(50) NOT NULL,
  channel varchar(50) NULL,
  message varchar(max) NULL,
  CONSTRAINT pk_commlog PRIMARY KEY clustered (id),
  CONSTRAINT fk_commlog_journal FOREIGN KEY (journal_id) REFERENCES journal (id) ON DELETE CASCADE
)

--drop table ship
CREATE TABLE ship (
  id INT IDENTITY(1,1) NOT NULL,
  edId INT NOT NULL,
  type varchar(50) NOT NULL,
  callsign varchar(100) NULL,
  inara_url varchar(200) NULL,
  CONSTRAINT pk_ship PRIMARY KEY clustered (id)
)
CREATE INDEX i_ship_edId on ship (edId);

--drop TABLE properties 
CREATE TABLE properties (
  id INT IDENTITY(1,1) NOT NULL,
  entry VARCHAR(25) NOT NULL,
  value VARCHAR(50) NOT NULL,
  CONSTRAINT pk_properties PRIMARY KEY clustered (id)
)
CREATE INDEX i_properties_entry on properties (entry);

--drop table faction
CREATE TABLE faction (
  id INT IDENTITY(1,1) NOT NULL,
  journal_id INT NULL,
  name VARCHAR(200) NOT NULL UNIQUE,
  state VARCHAR(25) NULL,
  allegiance VARCHAR(25) NULL,
  inara_url varchar(200) NULL,
  CONSTRAINT pk_faction PRIMARY KEY clustered (id),
  CONSTRAINT fk_faction_journal FOREIGN KEY (journal_id) REFERENCES journal (id) ON DELETE SET NULL
)
CREATE INDEX i_faction_name on faction (name);

--drop table starsystem
CREATE TABLE starsystem(
	id INT IDENTITY(1,1) NOT NULL,
	journal_id INT NULL,
	name varchar(200) NOT NULL UNIQUE,
	inara_url varchar(1000) NULL,
	faction_id int NULL,
	security varchar(50) NULL,
	allegiance varchar(25) NULL,
	government varchar(25) NULL,
	economy varchar(25) NULL,
	starpos varchar(50) NULL,
	CONSTRAINT pk_starsystem PRIMARY KEY clustered (id),
	CONSTRAINT fk_starsystem_journal FOREIGN KEY (journal_id) REFERENCES journal (id) ON DELETE SET NULL,
	CONSTRAINT fk_starsystem_faction FOREIGN KEY (faction_id) REFERENCES faction (id) ON DELETE SET NULL
)
CREATE INDEX i_starsystem_name on starsystem (name);

--drop table star
CREATE TABLE star(
	id INT IDENTITY(1,1) NOT NULL,
	journal_id INT NULL,
	starsystem_id INT NULL,
	name VARCHAR(200) NOT NULL UNIQUE,
	type VARCHAR(50) NOT NULL,
	distance_from_arrival_ls NUMERIC(15,6) NOT NULL,
	stellar_mass NUMERIC(15,6) NULL,
	radius NUMERIC(15,6) NULL,
	absolute_magnitude NUMERIC(15,6) NULL,
	ageMY INT NULL,
	surface_temperature NUMERIC(15,6) NULL,
	semi_major_axis NUMERIC(15,6) NULL,
	eccentricity NUMERIC(15,6) NULL,
	orbital_inclination NUMERIC(15,6) NULL,
	periapsis NUMERIC(15,6) NULL,
	orbital_period NUMERIC(15,6) NULL,
	rotation_period NUMERIC(15,6) NULL,
	CONSTRAINT pk_star PRIMARY KEY clustered (id),
	CONSTRAINT fk_star_journal FOREIGN KEY (journal_id) REFERENCES journal (id) ON DELETE SET NULL,
	CONSTRAINT fk_star_starsystem FOREIGN KEY (starsystem_id) REFERENCES starsystem (id) ON DELETE SET NULL
)
CREATE INDEX i_star_name on star (name);

--drop table planet
CREATE TABLE planet(
	id INT IDENTITY(1,1) NOT NULL,
	journal_id INT NULL,
	starsystem_id INT NULL,
	name VARCHAR(200) NOT NULL UNIQUE,
	type VARCHAR(50) NOT NULL,
	distance_from_arrival_ls NUMERIC(15,6) NOT NULL,
	tidal_lock BIT NOT NULL,
	terraform_state VARCHAR(50) NULL,
	atmosphere VARCHAR(50) NULL,
	volcanism VARCHAR(50) NULL,
	mass_em NUMERIC(15,6) NULL,
	radius NUMERIC(15,6) NULL,
	surface_gravity NUMERIC(15,6) NULL,
	surface_temperature NUMERIC(15,6) NULL,
	surface_pressure NUMERIC(15,6) NULL,
	landable BIT NOT NULL,
	semi_major_axis NUMERIC(15,6) NULL,
	eccentricity NUMERIC(15,6) NULL,
	orbital_inclination NUMERIC(15,6) NULL,
	periapsis NUMERIC(15,6) NULL,
	orbital_period NUMERIC(15,6) NULL,
	rotation_period NUMERIC(15,6) NULL,
	CONSTRAINT pk_planet PRIMARY KEY clustered (id),
	CONSTRAINT fk_planet_journal FOREIGN KEY (journal_id) REFERENCES journal (id) ON DELETE SET NULL,
	CONSTRAINT fk_planet_starsystem FOREIGN KEY (starsystem_id) REFERENCES starsystem (id) ON DELETE SET NULL
)
CREATE INDEX i_planet_name on star (name);

--drop table planet_material
CREATE TABLE planet_material(
	id INT IDENTITY(1,1) NOT NULL,
	journal_id INT NULL,
	planet_id INT NOT NULL,
	material_id INT NOT NULL,
	amount NUMERIC(12,2) NOT NULL,
	CONSTRAINT pk_planetmaterial PRIMARY KEY clustered (id),
	CONSTRAINT fk_planetmaterial_journal FOREIGN KEY (journal_id) REFERENCES journal (id) ON DELETE SET NULL,
	CONSTRAINT fk_planetmaterial_material FOREIGN KEY (material_id) REFERENCES material (id) ON DELETE CASCADE,
	CONSTRAINT fk_planetmaterial_planet FOREIGN KEY (planet_id) REFERENCES planet (id) ON DELETE CASCADE
)
CREATE UNIQUE INDEX ui_planetmaterial on planet_material (planet_id, material_id);

--drop table ring
CREATE TABLE ring(
	id INT IDENTITY(1,1) NOT NULL,
	journal_id INT NULL,
	starsystem_id INT NULL,
	star_id INT NULL,
	planet_id INT NULL,
	name VARCHAR(200) NOT NULL UNIQUE,
	type VARCHAR(50) NOT NULL,
	mass_mt VARCHAR(50) NULL,
	inner_rad VARCHAR(50) NULL,
	outer_rad VARCHAR(50) NULL,
	CONSTRAINT pk_ring PRIMARY KEY clustered (id),
	CONSTRAINT fk_ring_journal FOREIGN KEY (journal_id) REFERENCES journal (id) ON DELETE SET NULL,
	CONSTRAINT fk_ring_starsystem FOREIGN KEY (starsystem_id) REFERENCES starsystem (id) ON DELETE SET NULL,
	CONSTRAINT fk_ring_star FOREIGN KEY (star_id) REFERENCES star (id) ON DELETE SET NULL,
	CONSTRAINT fk_ring_planet FOREIGN KEY (planet_id) REFERENCES planet (id) ON DELETE SET NULL
)
CREATE INDEX i_ring_starsystemId on ring (starsystem_id);
CREATE INDEX i_ring_starId on ring (star_id);
CREATE INDEX i_ring_name on ring (name);

--drop table starport
CREATE TABLE starport(
	id INT IDENTITY(1,1) NOT NULL,
	journal_id INT NULL,
	starsystem_id INT NOT NULL,
	name varchar(200) NOT NULL UNIQUE,
	type varchar(25) NOT NULL,
	inara_url varchar(1000) NULL,
	faction_id int NULL,
	security varchar(50) NULL,
	allegiance varchar(25) NULL,
	government varchar(25) NULL,
	economy varchar(25) NULL,
	CONSTRAINT pk_starport PRIMARY KEY clustered (id),
	CONSTRAINT fk_starport_journal FOREIGN KEY (journal_id) REFERENCES journal (id) ON DELETE SET NULL,
	CONSTRAINT fk_starport_starsystem FOREIGN KEY (starsystem_id) REFERENCES starsystem (id) ON DELETE CASCADE,
	CONSTRAINT fk_starport_faction FOREIGN KEY (faction_id) REFERENCES faction (id) ON DELETE SET NULL,
)
CREATE INDEX i_starport_name on starport (name);

--drop table financedata
CREATE TABLE financedata (
  id INT IDENTITY(1,1) NOT NULL,
  journal_id INT NOT NULL,
  valutadatum DATETIME NOT NULL,
  amount NUMERIC(12,0) NOT NULL,
  category VARCHAR(50) NOT NULL,
  remark VARCHAR(200) NOT NULL,
  faction_id int NOT NULL
  CONSTRAINT pk_financedata PRIMARY KEY clustered (id),
  CONSTRAINT fk_financedata_journal FOREIGN KEY (journal_id) REFERENCES journal (id) ON DELETE CASCADE,
  CONSTRAINT fk_financedata_faction FOREIGN KEY (faction_id) REFERENCES faction (id)
)

--drop table navlog
CREATE TABLE navlog (
  id INT IDENTITY(1,1) NOT NULL,
  journal_id INT NOT NULL,
  ship_id INT NULL,
  timestamp datetime NOT NULL,
  tosystem_id int NOT NULL,
  distance numeric(12,6) NOT NULL,
  fuelused numeric(12,6) NOT NULL,
  CONSTRAINT pk_navlog PRIMARY KEY clustered (id),
  CONSTRAINT fk_navlog_journal FOREIGN KEY (journal_id) REFERENCES journal (id) ON DELETE CASCADE,
  CONSTRAINT fk_navlog_ship FOREIGN KEY (ship_id) REFERENCES ship (id) ON DELETE SET NULL,
  CONSTRAINT fk_navlog_tosystem FOREIGN KEY (tosystem_id) REFERENCES starsystem (id) ON DELETE CASCADE,
)
CREATE INDEX i_navlog_shipid ON navlog(ship_id)

--drop table combatlog
CREATE TABLE combatlog (
  id INT IDENTITY(1,1) NOT NULL,
  journal_id INT NOT NULL,
  ship_id INT NULL,
  timestamp datetime NOT NULL,
  action VARCHAR(50) NOT NULL,
  enemy VARCHAR(50) NULL,
  faction_id INT NOT NULL,
  reward INT NULL,
  CONSTRAINT pk_combatlog PRIMARY KEY clustered (id),
  CONSTRAINT fk_combatlog_ship FOREIGN KEY (ship_id) REFERENCES ship (id) ON DELETE SET NULL,
  CONSTRAINT fk_combatlog_journal FOREIGN KEY (journal_id) REFERENCES journal (id) ON DELETE CASCADE,
  CONSTRAINT fk_combatlog_faction FOREIGN KEY (faction_id) REFERENCES faction (id) ON DELETE CASCADE,
)
CREATE INDEX i_combatlog_shipid ON combatlog(ship_id)

--drop table mission
CREATE TABLE mission (
	id INT IDENTITY(1,1) NOT NULL,
	journal_id INT NOT NULL,
	missionId INT NOT NULL UNIQUE,
	faction_id INT NULL,
	name VARCHAR(50) NOT NULL,
	commodity varchar(200) NULL,
	commodity_count INT NULL,
	passenger_type VARCHAR(50) NULL,
	passenger_count INT NULL,
	passenger_vip BIT NULL,
	passenger_wanted BIT NULL,
	destination VARCHAR(200) NULL,
	destination_port VARCHAR(200) NULL,
	target_faction varchar(200) NULL,
	reward INT NULL,
	expiry DATETIME NULL,
	status VARCHAR(25) NOT NULL default 'ACCEPTED',
	finance_id INT NULL,
	CONSTRAINT pk_mission PRIMARY KEY clustered (id),
    CONSTRAINT fk_mission_journal FOREIGN KEY (journal_id) REFERENCES journal (id) ON DELETE CASCADE,
	CONSTRAINT fk_mission_faction FOREIGN KEY (faction_id) REFERENCES faction (id) ON DELETE SET NULL,
	CONSTRAINT fk_mission_finance FOREIGN KEY (finance_id) REFERENCES financedata (id),
)
CREATE INDEX i_mission_missionid on mission (missionId);