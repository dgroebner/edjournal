package de.dgroebner.edjson.db;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.dao.StarsystemDao;
import de.dgroebner.edjson.db.model.DBStarsystem;

/**
 * Methoden für die Datenbanktabelle 'starsystem' zur Speicherung der Sternensysteme
 * 
 * @author dgroebner
 */
public class Starsystem extends AbstractDBTable {

    /**
     * Constructor.
     *
     * @param dbi {@link DBI}
     */
    public Starsystem(final DBI dbi) {
        super(dbi);
    }

    /**
     * Speichert ein neues Sternensystem sofern noch nicht vorhanden und gibt das gespeicherte Datenbankobjekt zurück
     * 
     * @param journalId int
     * @param forSave {@link DBStarsystem}
     * @return DBStarsystem
     */
    public DBStarsystem writeOrGetStarsystem(final int journalId, final DBStarsystem forSave) {
        final StarsystemDao dao = getDbi().open(StarsystemDao.class);
        try {
            final DBStarsystem saved = dao.findByName(forSave.getName());
            if (saved != null) {
                final String pos = StringUtils.defaultIfBlank(forSave.getStarpos(), saved.getStarpos());
                dao.updateMetaData(forSave.getFactionId(), forSave.getSecurity(), forSave.getAllegiance(),
                        forSave.getGovernment(), forSave.getEconomy(), pos, forSave.getName());
            } else {
                dao.insert(journalId, forSave.getName(), null, forSave.getFactionId(), forSave.getSecurity(),
                        forSave.getAllegiance(), forSave.getGovernment(), forSave.getEconomy(), forSave.getStarpos());
            }
            return dao.findByName(forSave.getName());
        } finally {
            dao.close();
        }
    }

    /**
     * Liefert das gespeicherte Sternensystem für den übergebenen Namen
     * 
     * @param name {@link String}
     * @return {@link DBStarsystem}
     */
    public DBStarsystem getByName(final String name) {
        final StarsystemDao dao = getDbi().open(StarsystemDao.class);
        try {
            return dao.findByName(name);
        } finally {
            dao.close();
        }
    }

}
