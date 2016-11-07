package de.dgroebner.edjson.model.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dgroebner.edjson.model.GenericModel;
import de.dgroebner.edjson.model.JournalModelField;

/**
 * Modellklasse für den Elite Dangerous Journaleintrag 'Scan'
 * 
 * @author dgroebner
 */
public class Scan extends GenericModel<Scan.Fields> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Scan.class);

    /**
     * Felder des Elite Dangerous Journaleintrag 'Scan'
     * 
     * @author dgroebner
     */
    public enum Fields implements JournalModelField {
        /* @formatter:off */
        TIMESTAMP("timestamp", LocalDateTime.class),
        EVENT("event", String.class),
        BODY_NAME("BodyName", String.class),
        DISTANCE_FROM_ARRIVAL_LS("DistanceFromArrivalLS", BigDecimal.class),
        STAR_TYPE("StarType", String.class),
        STELLAR_MASS("StellarMass", BigDecimal.class),
        RADIUS("Radius", BigDecimal.class),
        ABSOLUTE_MAGNITUDE("AbsoluteMagnitude", BigDecimal.class),
        AGE_MY("Age_MY", Integer.class),
        SURFACE_TEMPERATURE("SurfaceTemperature", BigDecimal.class),
        SEMI_MAJOR_AXIS("SemiMajorAxis", BigDecimal.class),
        ECCENTRICITY("Eccentricity", BigDecimal.class),
        ORBITAL_INCLINATION("OrbitalInclination", BigDecimal.class),
        PERIAPSIS("Periapsis", BigDecimal.class),
        ORBITAL_PERIOD("OrbitalPeriod", BigDecimal.class),
        ROTATION_PERIOD("RotationPeriod", BigDecimal.class),
        RINGS("Rings", JSONArray.class),
        TIDAL_LOCK("TidalLock", Boolean.class),
        TERRAFORM_STATE("TerraformState", String.class),
        PLANET_CLASS("PlanetClass", String.class),
        ATMOSPHERE("Atmosphere", String.class),
        VOLCANISM("Volcanism", String.class),
        MASS_EM("MassEM", BigDecimal.class),
        SURFACE_GRAVITY("SurfaceGravity", BigDecimal.class),
        SURFACE_PRESSURE("SurfacePressure", BigDecimal.class),
        LANDABLE("Landable", Boolean.class),
        MATERIALS("Materials", JSONObject.class);
        /* @formatter:on */

        private String code;

        private Class<?> type;

        Fields(final String code, final Class<?> type) {
            this.code = code;
            this.type = type;
        }

        @Override
        public String getCode() {
            return code;
        }

        @Override
        public Class<?> getType() {
            return type;
        }
    }

    /**
     * Erzeugt eine neue Dateiheader Modellklasse für das übergebene {@link JSONObject}
     * 
     * @param json {@link JSONObject}
     */
    public Scan(final JSONObject json) {
        super(json, Arrays.asList(Fields.values()));
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    /**
     * Liefert <code>true</code> wenn das gescannte Objekt das Hauptobjekt (Einsprungpunkt) im System ist
     * 
     * @return boolean
     */
    public boolean isEntryPoint() {
        return BigDecimal.ZERO.compareTo(getValueAsBigDecimal(Fields.DISTANCE_FROM_ARRIVAL_LS)) == 0;
    }

    /**
     * Liefert <code>true</code> wenn das gescannte Objekt ein Stern ist
     * 
     * @return boolean
     */
    public boolean isStar() {
        return StringUtils.isNotBlank(getValueAsString(Fields.STAR_TYPE));
    }

    /**
     * Liefert <code>true</code> wenn das gescannte Objekt ein Planet ist
     * 
     * @return boolean
     */
    public boolean isPlanet() {
        return StringUtils.isNotBlank(getValueAsString(Fields.PLANET_CLASS));
    }

    /**
     * Liefert gefundene Materialien zurück
     * 
     * @return {@link Materials}
     */
    public Materials getMaterials() {
        return new Materials(getValueAsJsonObject(Fields.MATERIALS));
    }

    /**
     * Liefert gefundene Ringe zurück
     * 
     * @return {@link Set} of {@link Ring}
     */
    public Set<Ring> getRings() {
        final Set<Ring> rings = new HashSet<>();
        getValueAsJsonArray(Fields.RINGS).forEach(ring -> rings.add(new Ring((JSONObject) ring)));
        return rings;
    }
}
