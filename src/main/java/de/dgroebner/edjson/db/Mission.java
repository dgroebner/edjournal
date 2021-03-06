package de.dgroebner.edjson.db;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.dao.MissionDao;
import de.dgroebner.edjson.db.dao.VMissionLogDao;
import de.dgroebner.edjson.db.model.DBMission;
import de.dgroebner.edjson.db.model.VMissionLog;

/**
 * Methoden für die Datenbanktabelle 'mission' zur Speicherung von Missionen
 * 
 * @author dgroebner
 */
public class Mission extends AbstractDBTable {

    /**
     * Enum der Missionsstatus
     * 
     * @author dgroebner
     */
    public enum STATUS {
        ACCEPTED, COMPLETED, DECLINED, FAILED, ABANDONED;
    }

    /**
     * Constructor.
     *
     * @param dbi {@link DBI}
     */
    public Mission(final DBI dbi) {
        super(dbi);
    }

    /**
     * Speichert eine neuen Mission.
     * 
     * @param journalId int
     * @param forSave {@link DBMission}
     */
    public void saveNew(final int journalId, final DBMission forSave) {
        final MissionDao dao = getDbi().open(MissionDao.class);
        try {
            dao.insert(journalId, forSave.getMissionId(), forSave.getFactionId(), forSave.getName(),
                    forSave.getCommodity(), forSave.getCommodityCount(), forSave.getPassengerType(),
                    forSave.getPassengerCount(), forSave.isPassengerVip(), forSave.isPassengerWanted(),
                    forSave.getDestination(), forSave.getDestinationPort(), forSave.getTargetFaction(),
                    forSave.getExpiry(), StringUtils.defaultString(forSave.getStatus(), STATUS.ACCEPTED.name()));
        } finally {
            dao.close();
        }
    }

    /**
     * Aktualisiert den Status der Mission
     * 
     * @param missionId int
     * @param status {@link STATUS}
     */
    public void updateStatus(final int missionId, final STATUS status) {
        final MissionDao dao = getDbi().open(MissionDao.class);
        try {
            dao.updateStatus(status.name(), missionId);
        } finally {
            dao.close();
        }
    }

    /**
     * Verknüpft eine Finanztransaktion mit der Mission
     * 
     * @param missionId int
     * @param financeId int
     * @param reward int
     */
    public void updateFinanceId(final int missionId, final int financeId, final int reward) {
        final MissionDao dao = getDbi().open(MissionDao.class);
        try {
            dao.updateFinanceId(financeId, reward, missionId);
        } finally {
            dao.close();
        }
    }

    /**
     * Liefert eine Liste des Missionlogs zurück
     * 
     * @return {@link List} von {@link VMissionLog}
     */
    public List<VMissionLog> getMissionLog() {
        final VMissionLogDao dao = getDbi().open(VMissionLogDao.class);
        try {
            return dao.list();
        } finally {
            dao.close();
        }
    }

}
