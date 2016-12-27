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
	stock int NOT NULL default 0,
	rarity varchar(25) NULL,
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
	allegiance varchar(25) NULL,
	government varchar(25) NULL,
	economy varchar(25) NULL,
	CONSTRAINT pk_starport PRIMARY KEY clustered (id),
	CONSTRAINT fk_starport_journal FOREIGN KEY (journal_id) REFERENCES journal (id) ON DELETE SET NULL,
	CONSTRAINT fk_starport_starsystem FOREIGN KEY (starsystem_id) REFERENCES starsystem (id) ON DELETE CASCADE,
	CONSTRAINT fk_starport_faction FOREIGN KEY (faction_id) REFERENCES faction (id) ON DELETE SET NULL,
)
CREATE INDEX i_starport_name on starport (name);

--drop table starport_visits
CREATE TABLE starport_visits(
    id INT IDENTITY(1,1) NOT NULL,
	journal_id INT NOT NULL,
	starport_id INT NOT NULL,
	ship_id INT NULL,
	CONSTRAINT pk_starportvisits PRIMARY KEY clustered (id),
	CONSTRAINT fk_starportvisits_journal FOREIGN KEY (journal_id) REFERENCES journal (id) ON DELETE CASCADE,
	CONSTRAINT fk_starportvisits_starport FOREIGN KEY (starport_id) REFERENCES starport (id) ON DELETE CASCADE,
	CONSTRAINT fk_starportvisits_ship FOREIGN KEY (ship_id) REFERENCES ship (id) ON DELETE SET NULL
);
CREATE INDEX i_starportvisits_journalport on starport_visits (journal_id, starport_id);

--drop table starsystem_visits
CREATE TABLE starsystem_visits(
    id INT IDENTITY(1,1) NOT NULL,
	journal_id INT NOT NULL,
	starsystem_id INT NOT NULL,
	ship_id INT NULL,
	CONSTRAINT pk_starsystemvisits PRIMARY KEY clustered (id),
	CONSTRAINT fk_starsystemvisits_journal FOREIGN KEY (journal_id) REFERENCES journal (id) ON DELETE CASCADE,
	CONSTRAINT fk_starsystemvisits_starsystem FOREIGN KEY (starsystem_id) REFERENCES starsystem (id) ON DELETE CASCADE,
	CONSTRAINT fk_starsystemvisits_ship FOREIGN KEY (ship_id) REFERENCES ship (id) ON DELETE SET NULL
);
CREATE INDEX i_starsystemvisits_journalport on starsystem_visits (journal_id, starsystem_id);

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

go
CREATE FUNCTION fcTokenizer 
/*****************************************************************
* Funktion zum Trennen eines Strings mit einem Trennzeichen      *
*                                                                *
*                                                                *
* Parameter:                                                     *
* - der zu durchsuchende String                                  *
* - die Position vor dem ersten Trennzeichen                     *
* - das Trennzeichen (bei Oracle Optional)                       *
*                                                                *
* Verwendung:                                                    *
*                                                                *
* select '1='+avviso.fcTokenizer ('1|2|3|', 1, '|')              *
*****************************************************************/
(@Parameters varchar(2000), @position int, @delimiter varchar(1))
RETURNS VARCHAR(200)
AS  
BEGIN
    DECLARE @dx varchar(9)
    DECLARE @loops int
    SET @loops = 0
    IF @position < 1 RETURN ''
 
     IF @delimiter is null  SET @delimiter = '|'
     IF len(@delimiter) < 1 SET @delimiter = '|'
     SET @dx = left(@delimiter, DATALENGTH(@delimiter)-1)
 
     DECLARE @Value varchar(80), @Pos int
 
     SET @Parameters = LTRIM(RTRIM(@Parameters)) + @delimiter
     SET @Pos = CHARINDEX(@delimiter, @Parameters, 1)
 
     IF REPLACE(@Parameters, @delimiter, @dx) <> ''
     BEGIN
          WHILE @Pos > 0
          BEGIN
               SET @loops = @loops + 1
               SET @Value = LTRIM(RTRIM(LEFT(@Parameters, @Pos - 1)))
               IF @Value <> '' AND @loops = @position
               BEGIN
		    RETURN ltrim(rtrim(@Value))
               END
               SET @Parameters = SUBSTRING(@Parameters, @Pos + DATALENGTH(@delimiter), 8000)
               SET @Pos = CHARINDEX(@delimiter, @Parameters, 1)
           END
     END    
     RETURN ''
END      
GO

CREATE FUNCTION fcDistance 
/*****************************************************************
* Funktion zum Berechnen des Abstands zweier Sternensysteme      *
*                                                                *
*                                                                *
* Parameter:                                                     *
* - der zu durchsuchende String                                  *
* - die Position vor dem ersten Trennzeichen                     *
* - das Trennzeichen (bei Oracle Optional)                       *
*                                                                *
* Verwendung:                                                    *
*                                                                *
* select '1='+avviso.fcTokenizer ('1|2|3|', 1, '|')              *
*****************************************************************/
(@Quellsystem varchar(200), @Zielsystem varchar(200))
RETURNS FLOAT
AS  
BEGIN
declare @x1 float (25), @x2 float (25), @y1 float (25), @y2 float (25), @z1 float (25), @z2 float (25);

select @x1 = cast(fcTokenizer(starpos, 1, ':') as float) from starsystem where name = @Quellsystem
select @y1 = cast(fcTokenizer(starpos, 2, ':') as float) from starsystem where name = @Quellsystem
select @z1 = cast(fcTokenizer(starpos, 3, ':') as float) from starsystem where name = @Quellsystem
select @x2 = cast(fcTokenizer(starpos, 1, ':') as float) from starsystem where name = @Zielsystem
select @y2 = cast(fcTokenizer(starpos, 2, ':') as float) from starsystem where name = @Zielsystem
select @z2 = cast(fcTokenizer(starpos, 3, ':') as float) from starsystem where name = @Zielsystem

return ROUND(SQRT(POWER(@x2 - @x1, 2) + POWER(@y2 - @y1, 2) + POWER(@z2 - @z1, 2)), 2)
END      
GO

go
if exists (select 1 from sysobjects where name = 'vshipsummary')
   drop view vshipsummary
go
create view vshipsummary (type, callsign, inara_url, distance)
 as 
select type, callsign, inara_url, 
       (select ISNULL(SUM(distance), 0) 
	      from navlog where ship_id = ship.id)
 from ship

if exists (select 1 from sysobjects where name = 'vstarsystemslog')
   drop view vstarsystemslog
go
create view vstarsystemslog (visits, systemname, system_url, 
       factionname, faction_url, security, allegiance,
	   government, economy, starpos)
 as 
select count(*), starsystem.name, starsystem.inara_url, 
       faction.name, faction.inara_url, security, starsystem.allegiance,
	   government, economy, starpos
  from starsystem_visits
  join starsystem on starsystem.id = starsystem_id
  left join faction on faction.id = faction_id
  group by starsystem.name, starsystem.inara_url, 
       faction.name, faction.inara_url, security, starsystem.allegiance,
	   government, economy, starpos
go

if exists (select 1 from sysobjects where name = 'vstarportvisits')
   drop view vstarportvisits
go
create view vstarportvisits (timestamp, portname, port_url, systemname, system_url, 
       factionname, faction_url, allegiance,
	   type, government, economy, distanceToluku)
 as
select journal.timestamp, starport.name, starport.inara_url, starsystem.name, starsystem.inara_url,
       faction.name, faction.inara_url, starport.allegiance,
	   starport.type, starport.government, starport.economy,
	   fcDistance('Toluku', starsystem.name)
  from starport_visits
  join journal on journal.id = starport_visits.journal_id
  join starport on starport_visits.starport_id = starport.id
  join starsystem on starsystem.id = starsystem_id
  join faction on faction.id = starport.faction_id
go

if exists (select 1 from sysobjects where name = 'vnavlog')
   drop view vnavlog
go
create view vnavlog (shipname, shiptype, ship_url, timestamp, systemname, system_url, distance, fuelused, distanceToluku)
 as  
select ship.callsign, ship.type, ship.inara_url, navlog.timestamp, starsystem.name, starsystem.inara_url, distance, fuelused, fcDistance('Toluku', name) 
  from navlog
  left join ship on ship.id = ship_id
  join starsystem on starsystem.id = tosystem_id

go
if exists (select 1 from sysobjects where name = 'vmissionlog')
   drop view vmissionlog
go
create view vmissionlog (factionname, missionname, commodity, commodity_count, passenger_type, passenger_count, passenger_vip,
            passenger_wanted, destination, destination_port, status, reward, missionid)
 as  
select faction.name, replace(replace(mission.name, '_', ' '), 'Mission ', ''), commodity, commodity_count, 
       passenger_type, passenger_count, passenger_vip,
       passenger_wanted, destination, destination_port, status, financedata.amount, missionid
  from mission
  join faction on faction.id = faction_id
  join journal on journal.id = mission.journal_id
  left join financedata on finance_id = financedata.id

go
if exists (select 1 from sysobjects where name = 'vfinancelog')
   drop view vfinancelog
go
CREATE VIEW vfinancelog (valutadatum, amount, category, remark, factionname, factionurl)
AS
select valutadatum, amount, category, remark, faction.name, faction.inara_url
  from financedata
  join faction on faction.id = faction_id

go
if exists (select 1 from sysobjects where name = 'vrings')
   drop view vrings
go
CREATE VIEW vrings (art, starsystemname, ringname, ringtype, starsystem_url, distanceInsystem, distanceToluku)
AS
select 'Planetarer Ring', starsystem.name, ring.name, ring.type, starsystem.inara_url, planet.distance_from_arrival_ls, dbo.fcDistance('Toluku', starsystem.name)
--select ring.type + ' ' + ring.name + ' [url=' + starsystem.inara_url + ']' + planet.name + '[/url]'
  from ring
  join planet on ring.planet_id = planet.id
  join starsystem on planet.starsystem_id = starsystem.id
union all
select CASE WHEN
         ring.name like '%Belt%' then 'Asteroidengürtel'
		 ELSE 'Stellarer Ring'
	   END,
  starsystem.name, ring.name, ring.type, starsystem.inara_url, star.distance_from_arrival_ls, dbo.fcDistance('Toluku', starsystem.name)
--select ring.type + ' ' + ring.name + ' [url=' + starsystem.inara_url + ']' + planet.name + '[/url]'
  from ring
  join star on star_id = star.id
  join starsystem on star.starsystem_id = starsystem.id

go
if exists (select 1 from sysobjects where name = 'vmaterial_summary')
  drop view vmaterial_summary
go
create view vmaterial_summary (material, amount, planet, planet_type, gravity, material_url,
            starsystem_url, distanceInsystem, distanceToluku)
as
select material.name, amount, planet.name, planet.type, planet.surface_gravity, material.inara_url, 
       starsystem.inara_url, planet.distance_from_arrival_ls, dbo.fcDistance('Toluku', starsystem.name)
 from
 (select *, row_number() OVER (partition by planet_material.material_id order by amount desc) as rn
  from planet_material) t
join material on t.material_id = material.id
join planet on t.planet_id = planet.id
join starsystem on planet.starsystem_id = starsystem.id
where t.rn = 1
go

if exists (select 1 from sysobjects where name = 'vmaterial_overview')
  drop view vmaterial_overview
go
CREATE VIEW vmaterial_overview (material, amount, planet, planet_type, gravity, material_url,
            starsystem_url, distanceInsystem, distanceToluku)	 
AS
SELECT material.name, amount, 
       planet.name, planet.type, surface_gravity, material.inara_url, starsystem.inara_url,
	   planet.distance_from_arrival_ls, dbo.fcDistance('Toluku', starsystem.name)
  FROM starsystem 
  JOIN planet ON starsystem.id = planet.starsystem_id 
  JOIN planet_material ON planet_material.planet_id = planet.id 
  JOIN material ON material.id = planet_material.material_id
GO

if exists (select 1 from sysobjects where name = 'vstar')
  drop view vstar
go
create view vstar
       (starname, type, stellar_mass, radius, absolute_magnitude, 
       ageMY, surface_temperature, semi_major_axis, eccentricity, orbital_inclination, 
	   periapsis, orbital_period, rotation_period, 
	   starsystem_url, distanceInsystem, distanceToluku, starsystem_id)
 as
select star.name, type, stellar_mass, radius, absolute_magnitude, 
       ageMY, surface_temperature, semi_major_axis, eccentricity, orbital_inclination, 
	   periapsis, orbital_period, rotation_period, 
	   starsystem.inara_url, distance_from_arrival_ls, dbo.fcDistance('Toluku', starsystem.name),
	   starsystem.id
  from star
  join starsystem on starsystem.id = starsystem_id

go
if exists (select 1 from sysobjects where name = 'vplanet')
  drop view vplanet
go
create view vplanet
       (planetname, type, tidal_lock, terraform_state, atmosphere, volcanism,
       mass_em, radius, surface_gravity, surface_temperature,
	   surface_pressure, landable, semi_major_axis, eccentricity,
	   orbital_inclination, periapsis, orbital_period,
	   rotation_period, starsystem_url, distanceInsystem, distanceToluku,
	   starsystem_id)
as
  select planet.name, type, tidal_lock, terraform_state, atmosphere, volcanism,
       mass_em, radius, surface_gravity, surface_temperature,
	   surface_pressure, landable, semi_major_axis, eccentricity,
	   orbital_inclination, periapsis, orbital_period,
	   rotation_period, starsystem.inara_url, distance_from_arrival_ls, 
	   dbo.fcDistance('Toluku', starsystem.name),
	   starsystem.id
  from planet
  join starsystem on starsystem_id = starsystem.id

go
if exists (select 1 from sysobjects where name = 'vcombatlog')
  drop view vcombatlog
go
create view vcombatlog (timestamp, action, enemy, faction_name, faction_url, shipName, ship_url)
as
select timestamp, action, enemy, faction.name, faction.inara_url, ship.callsign, ship.inara_url
  from combatlog 
  join faction on faction.id = faction_id
  join ship on ship.id = ship_id
