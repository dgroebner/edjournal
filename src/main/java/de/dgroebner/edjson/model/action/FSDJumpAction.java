package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.FSDJump.Fields.FACTION_STATE;
import static de.dgroebner.edjson.model.data.FSDJump.Fields.FUEL_USED;
import static de.dgroebner.edjson.model.data.FSDJump.Fields.JUMP_DIST;
import static de.dgroebner.edjson.model.data.FSDJump.Fields.STAR_POS;
import static de.dgroebner.edjson.model.data.FSDJump.Fields.STAR_SYSTEM;
import static de.dgroebner.edjson.model.data.FSDJump.Fields.SYSTEM_ALLEGIANCE;
import static de.dgroebner.edjson.model.data.FSDJump.Fields.SYSTEM_ECONOMY_LOCALISED;
import static de.dgroebner.edjson.model.data.FSDJump.Fields.SYSTEM_FACTION;
import static de.dgroebner.edjson.model.data.FSDJump.Fields.SYSTEM_GOVERNMENT_LOCALISED;
import static de.dgroebner.edjson.model.data.FSDJump.Fields.SYSTEM_SECURITY_LOCALISED;
import static de.dgroebner.edjson.model.data.FSDJump.Fields.TIMESTAMP;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Faction;
import de.dgroebner.edjson.db.Navlog;
import de.dgroebner.edjson.db.Properties;
import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.db.Starsystem;
import de.dgroebner.edjson.db.model.DBFaction;
import de.dgroebner.edjson.db.model.DBStarsystem;
import de.dgroebner.edjson.model.data.FSDJump;

/**
 * Action für das JournalModell {@link FSDJump}
 * 
 * @author dgroebner
 */
public class FSDJumpAction extends AbstractAction<FSDJump> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final FSDJump model) {
        final DBFaction faction = new Faction(dbi).writeOrGetFraction(journalId, model.getValueAsString(SYSTEM_FACTION),
                model.getValueAsString(FACTION_STATE));

        /* @formatter:off */
        final DBStarsystem starSystemForSave = DBStarsystem.builder()
                .factionId(faction.getId())
                .name(model.getValueAsString(STAR_SYSTEM))
                .economy(model.getValueAsString(SYSTEM_ECONOMY_LOCALISED))
                .government(model.getValueAsString(SYSTEM_GOVERNMENT_LOCALISED))
                .security(model.getValueAsString(SYSTEM_SECURITY_LOCALISED))
                .allegiance(model.getValueAsString(SYSTEM_ALLEGIANCE))
                .starpos(model.getValueAsCoordinates(STAR_POS).getCoordString())
                .build();
        /* @formatter:on */

        final Starsystem starsystemDao = new Starsystem(dbi);
        final DBStarsystem savedStarSystem = starsystemDao.writeOrGetStarsystem(journalId, starSystemForSave);
        starsystemDao.saveVisit(journalId, savedStarSystem);
        new Properties(dbi).save(ENTRIES.CURRENT_STAR_SYSTEM, savedStarSystem.getId());

        new Navlog(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), savedStarSystem.getId(),
                model.getValueAsBigDecimal(JUMP_DIST), model.getValueAsBigDecimal(FUEL_USED));
    }

}
