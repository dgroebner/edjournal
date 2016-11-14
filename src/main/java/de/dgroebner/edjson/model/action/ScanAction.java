package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.Scan.Fields.ABSOLUTE_MAGNITUDE;
import static de.dgroebner.edjson.model.data.Scan.Fields.AGE_MY;
import static de.dgroebner.edjson.model.data.Scan.Fields.ATMOSPHERE;
import static de.dgroebner.edjson.model.data.Scan.Fields.BODY_NAME;
import static de.dgroebner.edjson.model.data.Scan.Fields.DISTANCE_FROM_ARRIVAL_LS;
import static de.dgroebner.edjson.model.data.Scan.Fields.ECCENTRICITY;
import static de.dgroebner.edjson.model.data.Scan.Fields.LANDABLE;
import static de.dgroebner.edjson.model.data.Scan.Fields.MASS_EM;
import static de.dgroebner.edjson.model.data.Scan.Fields.ORBITAL_INCLINATION;
import static de.dgroebner.edjson.model.data.Scan.Fields.ORBITAL_PERIOD;
import static de.dgroebner.edjson.model.data.Scan.Fields.PERIAPSIS;
import static de.dgroebner.edjson.model.data.Scan.Fields.PLANET_CLASS;
import static de.dgroebner.edjson.model.data.Scan.Fields.RADIUS;
import static de.dgroebner.edjson.model.data.Scan.Fields.ROTATION_PERIOD;
import static de.dgroebner.edjson.model.data.Scan.Fields.SEMI_MAJOR_AXIS;
import static de.dgroebner.edjson.model.data.Scan.Fields.STAR_TYPE;
import static de.dgroebner.edjson.model.data.Scan.Fields.STELLAR_MASS;
import static de.dgroebner.edjson.model.data.Scan.Fields.SURFACE_GRAVITY;
import static de.dgroebner.edjson.model.data.Scan.Fields.SURFACE_PRESSURE;
import static de.dgroebner.edjson.model.data.Scan.Fields.SURFACE_TEMPERATURE;
import static de.dgroebner.edjson.model.data.Scan.Fields.TERRAFORM_STATE;
import static de.dgroebner.edjson.model.data.Scan.Fields.TIDAL_LOCK;
import static de.dgroebner.edjson.model.data.Scan.Fields.VOLCANISM;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Planet;
import de.dgroebner.edjson.db.PlanetMaterial;
import de.dgroebner.edjson.db.RingTable;
import de.dgroebner.edjson.db.Star;
import de.dgroebner.edjson.db.model.DBPlanet;
import de.dgroebner.edjson.db.model.DBRing;
import de.dgroebner.edjson.db.model.DBStar;
import de.dgroebner.edjson.model.data.Ring;
import de.dgroebner.edjson.model.data.Scan;
import de.dgroebner.edjson.model.data.ScannedMaterials;
import de.dgroebner.edjson.model.data.ScannedMaterials.Fields;

/**
 * Action für das JournalModell {@link Scan}
 * 
 * @author dgroebner
 */
public class ScanAction extends AbstractAction<Scan> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final Scan model) {
        final boolean isStar = StringUtils.isNotBlank(model.getValueAsString(STAR_TYPE));
        if (isStar) {
            saveStarData(dbi, journalId, model);
        } else {
            savePlanetData(dbi, journalId, model);
        }
    }

    /**
     * Speichert die Daten des Planeten
     * 
     * @param dbi {@link DBI} Datenbankzugriffsobjekt
     * @param journalId int
     * @param model {@link Scan}
     */
    private void savePlanetData(final DBI dbi, final int journalId, final Scan model) {
        final DBPlanet dbPlanet = new DBPlanet();
        dbPlanet.setName(model.getValueAsString(BODY_NAME));
        dbPlanet.setType(model.getValueAsString(PLANET_CLASS));
        dbPlanet.setDistanceFromArrivalLS(model.getValueAsBigDecimal(DISTANCE_FROM_ARRIVAL_LS));
        dbPlanet.setRadius(model.getValueAsBigDecimal(RADIUS));
        dbPlanet.setSurfaceTemperature(model.getValueAsBigDecimal(SURFACE_TEMPERATURE));
        dbPlanet.setSemiMajorAxis(model.getValueAsBigDecimal(SEMI_MAJOR_AXIS));
        dbPlanet.setEccentricity(model.getValueAsBigDecimal(ECCENTRICITY));
        dbPlanet.setOrbitalInclination(model.getValueAsBigDecimal(ORBITAL_INCLINATION));
        dbPlanet.setPeriapsis(model.getValueAsBigDecimal(PERIAPSIS));
        dbPlanet.setOrbitalPeriod(model.getValueAsBigDecimal(ORBITAL_PERIOD));
        dbPlanet.setRotationPeriod(model.getValueAsBigDecimal(ROTATION_PERIOD));
        dbPlanet.setTidalLock(model.getValueAsBoolean(TIDAL_LOCK));
        dbPlanet.setTerraformState(model.getValueAsString(TERRAFORM_STATE));
        dbPlanet.setAtmosphere(model.getValueAsString(ATMOSPHERE));
        dbPlanet.setVolcanism(model.getValueAsString(VOLCANISM));
        dbPlanet.setMassEM(model.getValueAsBigDecimal(MASS_EM));
        dbPlanet.setSurfaceGravity(model.getValueAsBigDecimal(SURFACE_GRAVITY));
        dbPlanet.setSurfaceTemperature(model.getValueAsBigDecimal(SURFACE_TEMPERATURE));
        dbPlanet.setSurfacePressure(model.getValueAsBigDecimal(SURFACE_PRESSURE));
        dbPlanet.setLandable(model.getValueAsBoolean(LANDABLE));
        final int id = new Planet(dbi).saveNew(journalId, dbPlanet);
        model.getRings().forEach(ring -> saveRingToPlanet(dbi, journalId, id, ring));
        model.getMaterials().getPresentMaterials()
                .forEach(materialField -> saveMaterialToPlanet(dbi, journalId, id, model.getMaterials(), materialField));
    }

    /**
     * Speichert das Material mit dem übergebenen Feldnamen zum Planeten mit der übergebenen id
     * 
     * @param dbi {@link DBI} Datenbankzugriffsobjekt
     * @param journalId int
     * @param planetId int
     * @param materials {@link ScannedMaterials}
     * @param materialField {@link Fields}
     */
    private void saveMaterialToPlanet(final DBI dbi, final int journalId, final int planetId,
            final ScannedMaterials materials, final Fields materialField) {
        new PlanetMaterial(dbi).save(journalId, planetId, materialField.getCode(),
                materials.getValueAsBigDecimal(materialField));

    }

    /**
     * Speichert die Daten des Sterns
     * 
     * @param dbi {@link DBI} Datenbankzugriffsobjekt
     * @param journalId int
     * @param model {@link Scan}
     */
    private void saveStarData(final DBI dbi, final int journalId, final Scan model) {
        final DBStar dbStar = new DBStar();
        dbStar.setName(model.getValueAsString(BODY_NAME));
        dbStar.setType(model.getValueAsString(STAR_TYPE));
        dbStar.setDistanceFromArrivalLS(model.getValueAsBigDecimal(DISTANCE_FROM_ARRIVAL_LS));
        dbStar.setStellarMass(model.getValueAsBigDecimal(STELLAR_MASS));
        dbStar.setRadius(model.getValueAsBigDecimal(RADIUS));
        dbStar.setAbsoluteMagnitude(model.getValueAsBigDecimal(ABSOLUTE_MAGNITUDE));
        dbStar.setAgeMY(model.getValueAsInt(AGE_MY));
        dbStar.setSurfaceTemperature(model.getValueAsBigDecimal(SURFACE_TEMPERATURE));
        dbStar.setSemiMajorAxis(model.getValueAsBigDecimal(SEMI_MAJOR_AXIS));
        dbStar.setEccentricity(model.getValueAsBigDecimal(ECCENTRICITY));
        dbStar.setOrbitalInclination(model.getValueAsBigDecimal(ORBITAL_INCLINATION));
        dbStar.setPeriapsis(model.getValueAsBigDecimal(PERIAPSIS));
        dbStar.setOrbitalPeriod(model.getValueAsBigDecimal(ORBITAL_PERIOD));
        dbStar.setRotationPeriod(model.getValueAsBigDecimal(ROTATION_PERIOD));
        final int starId = new Star(dbi).saveNew(journalId, dbStar);
        model.getRings().forEach(ring -> saveRingToStar(dbi, journalId, starId, ring));
    }

    /**
     * Speichert einen neuen Ring für den übergebenen Stern
     * 
     * @param dbi {@link DBI} Datenbankzugriffsobjekt
     * @param journalId int
     * @param starId int
     * @param ring int
     */
    private void saveRingToStar(final DBI dbi, final int journalId, final int starId, final Ring ring) {
        final DBRing dbRing = new DBRing();
        dbRing.setStarId(starId);
        storeRingBasics(ring, dbRing);
        new RingTable(dbi).save(journalId, dbRing);
    }

    /**
     * Speichert einen neuen Ring für den übergebenen Stern
     * 
     * @param dbi {@link DBI} Datenbankzugriffsobjekt
     * @param journalId int
     * @param planetId int
     * @param ring int
     */
    private void saveRingToPlanet(final DBI dbi, final int journalId, final int planetId, final Ring ring) {
        final DBRing dbRing = new DBRing();
        dbRing.setPlanetId(planetId);
        storeRingBasics(ring, dbRing);
        new RingTable(dbi).save(journalId, dbRing);
    }

    /**
     * Speichert Standarddaten für den übergebenen Ring im DBRing Objekt
     * 
     * @param ring {@link Ring}
     * @param dbRing {@link DBRing}
     */
    private static void storeRingBasics(final Ring ring, final DBRing dbRing) {
        dbRing.setName(ring.getValueAsString(Ring.Fields.NAME));
        dbRing.setType(ring.getValueAsString(Ring.Fields.RING_CLASS));
        dbRing.setMassMt(ring.getValueAsBigDecimal(Ring.Fields.MASS_MT).toPlainString());
        dbRing.setInnerRad(ring.getValueAsBigDecimal(Ring.Fields.INNER_RAD).toPlainString());
        dbRing.setOuterRad(ring.getValueAsBigDecimal(Ring.Fields.OUTER_RAD).toPlainString());
    }

}
