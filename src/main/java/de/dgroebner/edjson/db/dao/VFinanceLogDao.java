package de.dgroebner.edjson.db.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.dgroebner.edjson.db.mapper.VFinanceLogMapper;
import de.dgroebner.edjson.db.model.VFinanceLog;

/**
 * DAO-Interface für den View 'vfinancelog'
 * 
 * @author dgroebner
 */
@RegisterMapper(VFinanceLogMapper.class)
public interface VFinanceLogDao extends AbstractDao {

    /**
     * Selektiert die letzten 50 Einträge des Finanzlogs
     * 
     * @return {@link List} von {@link VFinanceLog}
     */
    @SqlQuery("SELECT TOP 50 valutadatum, amount, category, remark, factionname, factionurl FROM vfinancelog ORDER BY valutadatum DESC")
    List<VFinanceLog> list();

}
