package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.FSDJump.Fields.ALLEGIANCE;
import static de.dgroebner.edjson.model.data.FSDJump.Fields.ECONOMY_LOCALISED;
import static de.dgroebner.edjson.model.data.FSDJump.Fields.FACTION;
import static de.dgroebner.edjson.model.data.FSDJump.Fields.FACTION_STATE;
import static de.dgroebner.edjson.model.data.FSDJump.Fields.FUEL_USED;
import static de.dgroebner.edjson.model.data.FSDJump.Fields.GOVERNMENT_LOCALISED;
import static de.dgroebner.edjson.model.data.FSDJump.Fields.JUMP_DIST;
import static de.dgroebner.edjson.model.data.FSDJump.Fields.SECURITY_LOCALISED;
import static de.dgroebner.edjson.model.data.FSDJump.Fields.STAR_POS;
import static de.dgroebner.edjson.model.data.FSDJump.Fields.STAR_SYSTEM;
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
        final DBFaction faction = new Faction(dbi).writeOrGetFraction(journalId, model.getValueAsString(FACTION),
                model.getValueAsString(FACTION_STATE));

        final DBStarsystem starSystemForSave = new DBStarsystem();
        starSystemForSave.setFactionId(faction.getId());
        starSystemForSave.setName(model.getValueAsString(STAR_SYSTEM));
        starSystemForSave.setEconomy(model.getValueAsString(ECONOMY_LOCALISED));
        starSystemForSave.setGovernment(model.getValueAsString(GOVERNMENT_LOCALISED));
        starSystemForSave.setSecurity(model.getValueAsString(SECURITY_LOCALISED));
        starSystemForSave.setAllegiance(model.getValueAsString(ALLEGIANCE));
        starSystemForSave.setStarpos(model.getValueAsCoordinates(STAR_POS).getCoordString());
        final Starsystem starsystemDao = new Starsystem(dbi);
        final DBStarsystem savedStarSystem = starsystemDao.writeOrGetStarsystem(journalId, starSystemForSave);
        starsystemDao.saveVisit(savedStarSystem);
        new Properties(dbi).save(ENTRIES.CURRENT_STAR_SYSTEM, savedStarSystem.getId());

        new Navlog(dbi).save(journalId, model.getValueAsLocalDateTime(TIMESTAMP), savedStarSystem.getId(),
                model.getValueAsBigDecimal(JUMP_DIST), model.getValueAsBigDecimal(FUEL_USED));
    }

}
