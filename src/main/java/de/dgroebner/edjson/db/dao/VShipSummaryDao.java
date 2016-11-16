package de.dgroebner.edjson.db.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.VShipSummaryMapper;
import de.dgroebner.edjson.db.model.DBShip;
import de.dgroebner.edjson.db.model.VShipSummary;

/**
 * DAO-Interface für den View 'vshipsummary'
 * 
 * @author dgroebner
 */
@RegisterMapper(VShipSummaryMapper.class)
public interface VShipSummaryDao extends AbstractDao {

    /**
     * Gibt die Liste der Schiffe aus dem View zurück
     * 
     * @return {@link List} of {@link DBShip}
     */
    @SqlQuery("SELECT type, callsign, inara_url, distance FROM vshipsummary")
    List<VShipSummary> list();

}
