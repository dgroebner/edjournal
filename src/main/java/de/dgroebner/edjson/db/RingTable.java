package de.dgroebner.edjson.db;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.db.dao.RingDao;
import de.dgroebner.edjson.db.dao.VRingDao;
import de.dgroebner.edjson.db.model.DBRing;
import de.dgroebner.edjson.db.model.VRings;

/**
 * Methoden für die Datenbanktabelle 'ring' zur Speicherung des Ringdaten
 * 
 * @author dgroebner
 */
public class RingTable extends AbstractDBTable {

    /**
     * Constructor.
     *
     * @param dbi {@link DBI}
     */
    public RingTable(final DBI dbi) {
        super(dbi);
    }

    /**
     * Schreibt für einen neuen Ring in die Datenbank.
     * 
     * @param journalId int
     * @param ring {@link DBRing}
     */
    public void save(final int journalId, final DBRing ring) {
        final String currentStarsystem = new Properties(getDbi()).getValueForEntry(ENTRIES.CURRENT_STAR_SYSTEM);
        final String ringTypeCleaned = StringUtils.removeStart(ring.getType(), "eRingClass_");
        final RingDao dao = getDbi().open(RingDao.class);
        try {
            if (ring.getStarId() > 0) {
                dao.insertForStar(journalId, Integer.parseInt(currentStarsystem), ring.getStarId(), ring.getName(),
                        ringTypeCleaned, ring.getMassMt(), ring.getInnerRad(), ring.getOuterRad());
            } else {
                dao.insertForPlanet(journalId, Integer.parseInt(currentStarsystem), ring.getPlanetId(), ring.getName(),
                        ringTypeCleaned, ring.getMassMt(), ring.getInnerRad(), ring.getOuterRad());
            }

        } finally {
            dao.close();
        }
    }

    /**
     * Gibt eine Liste der Ringe zurück
     * 
     * @return {@link List} von {@link VRings}
     */
    public List<VRings> list() {
        final VRingDao dao = getDbi().open(VRingDao.class);
        try {
            return dao.list();
        } finally {
            dao.close();
        }
    }

    /**
     * Zählt die vorhandenen Ringe
     * 
     * @return int
     */
    public int count() {
        final VRingDao dao = getDbi().open(VRingDao.class);
        try {
            return dao.count();
        } finally {
            dao.close();
        }
    }

}
