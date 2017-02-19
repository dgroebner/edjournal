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
        /* @formatter:off */
        final DBPlanet dbPlanet = DBPlanet.builder()
                .name(model.getValueAsString(BODY_NAME))
                .type(model.getValueAsString(PLANET_CLASS))
                .distanceFromArrivalLS(model.getValueAsBigDecimal(DISTANCE_FROM_ARRIVAL_LS))
                .radius(model.getValueAsBigDecimal(RADIUS))
                .surfaceTemperature(model.getValueAsBigDecimal(SURFACE_TEMPERATURE))
                .eccentricity(model.getValueAsBigDecimal(ECCENTRICITY))
                .orbitalInclination(model.getValueAsBigDecimal(ORBITAL_INCLINATION))
                .periapsis(model.getValueAsBigDecimal(PERIAPSIS))
                .orbitalPeriod(model.getValueAsBigDecimal(ORBITAL_PERIOD))
                .rotationPeriod(model.getValueAsBigDecimal(ROTATION_PERIOD))
                .tidalLock(model.getValueAsBoolean(TIDAL_LOCK))
                .terraformState(model.getValueAsString(TERRAFORM_STATE))
                .atmosphere(model.getValueAsString(ATMOSPHERE))
                .volcanism(model.getValueAsString(VOLCANISM))
                .massEM(model.getValueAsBigDecimal(MASS_EM))
                .surfaceGravity(model.getValueAsBigDecimal(SURFACE_GRAVITY))
                .surfaceTemperature(model.getValueAsBigDecimal(SURFACE_TEMPERATURE))
                .surfacePressure(model.getValueAsBigDecimal(SURFACE_PRESSURE))
                .landable(model.getValueAsBoolean(LANDABLE))
                .build();
        /* @formatter:on */

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
        /* @formatter:off */
        final DBStar dbStar = DBStar.builder()
                .name(model.getValueAsString(BODY_NAME))
                .type(model.getValueAsString(STAR_TYPE))
                .distanceFromArrivalLS(model.getValueAsBigDecimal(DISTANCE_FROM_ARRIVAL_LS))
                .stellarMass(model.getValueAsBigDecimal(STELLAR_MASS))
                .radius(model.getValueAsBigDecimal(RADIUS))
                .absoluteMagnitude(model.getValueAsBigDecimal(ABSOLUTE_MAGNITUDE))
                .ageMY(model.getValueAsInt(AGE_MY))
                .surfaceTemperature(model.getValueAsBigDecimal(SURFACE_TEMPERATURE))
                .semiMajorAxis(model.getValueAsBigDecimal(SEMI_MAJOR_AXIS))
                .eccentricity(model.getValueAsBigDecimal(ECCENTRICITY))
                .orbitalInclination(model.getValueAsBigDecimal(ORBITAL_INCLINATION))
                .periapsis(model.getValueAsBigDecimal(PERIAPSIS))
                .orbitalPeriod(model.getValueAsBigDecimal(ORBITAL_PERIOD))
                .rotationPeriod(model.getValueAsBigDecimal(ROTATION_PERIOD))
                .build();
        /* @formatter:on */

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
        final DBRing dbRing = storeRingBasics(ring);
        dbRing.setStarId(starId);
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
        final DBRing dbRing = storeRingBasics(ring);
        dbRing.setPlanetId(planetId);
        new RingTable(dbi).save(journalId, dbRing);
    }

    /**
     * Speichert Standarddaten für den übergebenen Ring im DBRing Objekt und gibt dieses zurück
     * 
     * @param ring {@link Ring}
     * @return {@link DBRing}
     */
    private static DBRing storeRingBasics(final Ring ring) {
        /* @formatter:off */
        return DBRing.builder()
                .name(ring.getValueAsString(Ring.Fields.NAME))
                .type(ring.getValueAsString(Ring.Fields.RING_CLASS))
                .massMt(ring.getValueAsBigDecimal(Ring.Fields.MASS_MT).toPlainString())
                .innerRad(ring.getValueAsBigDecimal(Ring.Fields.INNER_RAD).toPlainString())
                .outerRad(ring.getValueAsBigDecimal(Ring.Fields.OUTER_RAD).toPlainString())
                .build();
        /* @formatter:on */
    }

}
