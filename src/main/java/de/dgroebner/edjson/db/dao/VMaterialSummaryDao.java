package de.dgroebner.edjson.db.dao;

import static de.dgroebner.edjson.db.mapper.VMaterialSummaryMapper.COLUMN_PLANET;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.VMaterialSummaryMapper;
import de.dgroebner.edjson.db.model.VMaterialSummary;

/**
 * DAO-Interface für den View 'vmaterial_summary'
 * 
 * @author dgroebner
 */
@RegisterMapper(VMaterialSummaryMapper.class)
public interface VMaterialSummaryDao extends AbstractDao {

    /**
     * Gibt eine Liste Maximalvorkommen der Materialien zurück
     * 
     * @return {@link List} von {@link VMaterialSummary}
     */
    @SqlQuery("SELECT material, amount, planet, planet_type, gravity, material_url, starsystem_url, distanceInsystem, distanceToluku FROM vmaterial_summary ORDER BY material")
    List<VMaterialSummary> listSummary();

    /**
     * Gibt eine Gesamtliste der Materialvorkommen zurück
     * 
     * @return {@link List} von {@link VMaterialSummary}
     */
    @SqlQuery("SELECT material, amount, planet, planet_type, gravity, material_url, starsystem_url, distanceInsystem, distanceToluku FROM vmaterial_overview ORDER BY planet, amount DESC")
    List<VMaterialSummary> list();

    /**
     * Gibt eine Gesamtliste der Materialvorkommen für den übergebenen Planeten zurück
     * 
     * @param planet {@link String} planet
     * @return {@link List} von {@link VMaterialSummary}
     */
    @SqlQuery("SELECT material, amount, planet, planet_type, gravity, material_url, starsystem_url, distanceInsystem, distanceToluku FROM vmaterial_overview WHERE planet = :planet ORDER BY amount DESC")
    List<VMaterialSummary> list(@Bind(COLUMN_PLANET) String planet);


}
