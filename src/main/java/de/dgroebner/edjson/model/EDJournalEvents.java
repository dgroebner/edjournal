package de.dgroebner.edjson.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;

import de.dgroebner.edjson.model.action.JournalEventAction;
import de.dgroebner.edjson.model.action.LoggingAction;
import de.dgroebner.edjson.model.data.ApproachSettlement;
import de.dgroebner.edjson.model.data.Bounty;
import de.dgroebner.edjson.model.data.BuyAmmo;
import de.dgroebner.edjson.model.data.BuyDrones;
import de.dgroebner.edjson.model.data.BuyExplorationData;
import de.dgroebner.edjson.model.data.BuyTradeData;
import de.dgroebner.edjson.model.data.CapShipBond;
import de.dgroebner.edjson.model.data.CockpitBreached;
import de.dgroebner.edjson.model.data.CollectCargo;
import de.dgroebner.edjson.model.data.CommitCrime;
import de.dgroebner.edjson.model.data.CommunityGoalJoin;
import de.dgroebner.edjson.model.data.CommunityGoalReward;
import de.dgroebner.edjson.model.data.DataScanned;
import de.dgroebner.edjson.model.data.DatalinkScan;
import de.dgroebner.edjson.model.data.Died;
import de.dgroebner.edjson.model.data.DockFighter;
import de.dgroebner.edjson.model.data.DockSRV;
import de.dgroebner.edjson.model.data.Docked;
import de.dgroebner.edjson.model.data.DockingCancelled;
import de.dgroebner.edjson.model.data.DockingDenied;
import de.dgroebner.edjson.model.data.DockingGranted;
import de.dgroebner.edjson.model.data.DockingRequested;
import de.dgroebner.edjson.model.data.DockingTimeout;
import de.dgroebner.edjson.model.data.EjectCargo;
import de.dgroebner.edjson.model.data.EngineerApply;
import de.dgroebner.edjson.model.data.EngineerCraft;
import de.dgroebner.edjson.model.data.EngineerProgress;
import de.dgroebner.edjson.model.data.EscapeInterdiction;
import de.dgroebner.edjson.model.data.FSDJump;
import de.dgroebner.edjson.model.data.FactionKillBond;
import de.dgroebner.edjson.model.data.FetchRemoteModule;
import de.dgroebner.edjson.model.data.Fileheader;
import de.dgroebner.edjson.model.data.FuelScoop;
import de.dgroebner.edjson.model.data.HeatDamage;
import de.dgroebner.edjson.model.data.HeatWarning;
import de.dgroebner.edjson.model.data.HullDamage;
import de.dgroebner.edjson.model.data.Interdicted;
import de.dgroebner.edjson.model.data.Interdiction;
import de.dgroebner.edjson.model.data.LaunchFighter;
import de.dgroebner.edjson.model.data.LaunchSRV;
import de.dgroebner.edjson.model.data.Liftoff;
import de.dgroebner.edjson.model.data.LoadGame;
import de.dgroebner.edjson.model.data.Location;
import de.dgroebner.edjson.model.data.MarketBuy;
import de.dgroebner.edjson.model.data.MarketSell;
import de.dgroebner.edjson.model.data.MaterialCollected;
import de.dgroebner.edjson.model.data.MaterialDiscarded;
import de.dgroebner.edjson.model.data.MaterialDiscovered;
import de.dgroebner.edjson.model.data.MiningRefined;
import de.dgroebner.edjson.model.data.MissionAbandoned;
import de.dgroebner.edjson.model.data.MissionAccepted;
import de.dgroebner.edjson.model.data.MissionCompleted;
import de.dgroebner.edjson.model.data.MissionFailed;
import de.dgroebner.edjson.model.data.ModuleBuy;
import de.dgroebner.edjson.model.data.ModuleRetrieve;
import de.dgroebner.edjson.model.data.ModuleSell;
import de.dgroebner.edjson.model.data.ModuleSellRemote;
import de.dgroebner.edjson.model.data.ModuleStore;
import de.dgroebner.edjson.model.data.ModuleSwap;
import de.dgroebner.edjson.model.data.PayFines;
import de.dgroebner.edjson.model.data.PayLegacyFines;
import de.dgroebner.edjson.model.data.Progress;
import de.dgroebner.edjson.model.data.Promotion;
import de.dgroebner.edjson.model.data.Rank;
import de.dgroebner.edjson.model.data.RebootRepair;
import de.dgroebner.edjson.model.data.ReceiveText;
import de.dgroebner.edjson.model.data.RedeemVoucher;
import de.dgroebner.edjson.model.data.RefuelAll;
import de.dgroebner.edjson.model.data.RefuelPartial;
import de.dgroebner.edjson.model.data.Repair;
import de.dgroebner.edjson.model.data.RepairAll;
import de.dgroebner.edjson.model.data.RestockVehicle;
import de.dgroebner.edjson.model.data.Resurrect;
import de.dgroebner.edjson.model.data.Scan;
import de.dgroebner.edjson.model.data.Screenshot;
import de.dgroebner.edjson.model.data.SelfDestruct;
import de.dgroebner.edjson.model.data.SellDrones;
import de.dgroebner.edjson.model.data.SellExplorationData;
import de.dgroebner.edjson.model.data.SendText;
import de.dgroebner.edjson.model.data.ShieldState;
import de.dgroebner.edjson.model.data.ShipyardBuy;
import de.dgroebner.edjson.model.data.ShipyardNew;
import de.dgroebner.edjson.model.data.ShipyardSell;
import de.dgroebner.edjson.model.data.ShipyardSwap;
import de.dgroebner.edjson.model.data.ShipyardTransfer;
import de.dgroebner.edjson.model.data.SupercruiseEntry;
import de.dgroebner.edjson.model.data.SupercruiseExit;
import de.dgroebner.edjson.model.data.Synthesis;
import de.dgroebner.edjson.model.data.Touchdown;
import de.dgroebner.edjson.model.data.Undocked;
import de.dgroebner.edjson.model.data.VehicleSwitch;
import de.dgroebner.edjson.model.data.WingAdd;
import de.dgroebner.edjson.model.data.WingJoin;
import de.dgroebner.edjson.model.data.WingLeave;

/**
 * Enum der Elite Dangerous Journal Event mit Zuordnung der Modellklassen
 * 
 * @author dgroebner
 */
public enum EDJournalEvents implements EDJournalEventInterface {

    /* @formatter:off */
    /* Startup */
    HEADER("Fileheader", Fileheader.class),
    LOADGAME("LoadGame", LoadGame.class),
    PROGRESS("Progress", Progress.class),
    RANK("Rank", Rank.class),
    
    /* Travel */
    APPROACH_SETTLEMENT("ApproachSettlement", ApproachSettlement.class),
    DOCKED("Docked", Docked.class),
    DOCKING_CANCELLED("DockingCancelled", DockingCancelled.class),
    DOCKING_DENIED("DockingDenied", DockingDenied.class),
    DOCKING_GRANTED("DockingGranted", DockingGranted.class),
    DOCKING_REQUESTED("DockingRequested", DockingRequested.class),
    DOCKING_TIMEOUT("DockingTimeout", DockingTimeout.class),
    FSDJUMP("FSDJump", FSDJump.class),
    LIFTOFF("Liftoff", Liftoff.class),
    LOCATION("Location", Location.class),
    SUPERCRUISE_ENTRY("SupercruiseEntry", SupercruiseEntry.class),
    SUPERCRUISE_EXIT("SupercruiseExit", SupercruiseExit.class),
    TOUCHDOWN("Touchdown", Touchdown.class),
    UNDOCKED("Undocked", Undocked.class),
    
    /* Combat */
    BOUNTY("Bounty", Bounty.class),
    CAP_SHIP_BOND("CapShipBond", CapShipBond.class),
    DIED("Died", Died.class),
    ESCAPE_INTERDICTION("EscapeInterdiction", EscapeInterdiction.class),
    FACTION_KILL_BOND("FactionKillBond", FactionKillBond.class),
    HEAT_DAMAGE("HeatDamage", HeatDamage.class),
    HEAT_WARNING("HeatWarning", HeatWarning.class),
    HULL_DAMAGE("HullDamage", HullDamage.class),
    INTERDICTED("Interdicted", Interdicted.class),
    INTERDICTION("Interdiction", Interdiction.class),
    SHIELD_STATE("ShieldState", ShieldState.class),

    /* Exploration */
    SCAN("Scan", Scan.class),
    MATERIAL_COLLECTED("MaterialCollected", MaterialCollected.class),
    MATERIAL_DISCARDED("MaterialDiscarded", MaterialDiscarded.class),
    MATERIAL_DISCOVERED("MaterialDiscovered", MaterialDiscovered.class),
    BUY_EXPLORATION_DATA("BuyExplorationData", BuyExplorationData.class),
    SELL_EXPLORATION_DATA("SellExplorationData", SellExplorationData.class),
    SCREENSHOT("Screenshot", Screenshot.class),

    /* Trade */
    BUY_TRADE_DATA("BuyTradeData", BuyTradeData.class),
    COLLECT_CARGO("CollectCargo", CollectCargo.class),
    EJECT_CARGO("EjectCargo", EjectCargo.class),
    MARKET_BUY("MarketBuy", MarketBuy.class),
    MARKED_SELL("MarketSell", MarketSell.class),
    MINING_REFINED("MiningRefined", MiningRefined.class),
    
    /*Station Services */
    BUY_AMMO("BuyAmmo", BuyAmmo.class),
    BUY_DRONES("BuyDrones", BuyDrones.class),
    COMMUNITY_GOAL_JOIN("CommunityGoalJoin", CommunityGoalJoin.class),
    COMMUNITY_GOAL_REWARED("CommunityGoalReward", CommunityGoalReward.class),
    ENGINEER_APPLY("EngineerApply", EngineerApply.class),
    ENGINEER_CRAFT("EngineerCraft", EngineerCraft.class),
    ENGINEER_PROGRESS("EngineerProgress", EngineerProgress.class),
    FETCH_REMOTE_MODULE("FetchRemoteModule", FetchRemoteModule.class),
    MISSION_ABANDONED("MissionAbandoned", MissionAbandoned.class),
    MISSION_ACCEPTED("MissionAccepted", MissionAccepted.class),
    MISSION_COMPLETED("MissionCompleted", MissionCompleted.class),
    MISSION_FAILED("MissionFailed", MissionFailed.class),
    MODULE_BUY("ModuleBuy", ModuleBuy.class),
    MODULE_RETRIEVE("ModuleRetrieve", ModuleRetrieve.class),
    MODULE_SELL("ModuleSell", ModuleSell.class),
    MODULE_SELL_REMOTE("ModuleSellRemote", ModuleSellRemote.class),
    MODULE_STORE("ModuleStore", ModuleStore.class),
    MODULE_SWAP("ModuleSwap", ModuleSwap.class),
    PAY_FINES("PayFines", PayFines.class),
    PAY_LEGACY_FINES("PayLegacyFines", PayLegacyFines.class),
    REDEEM_VOUCHER("RedeemVoucher", RedeemVoucher.class),
    REFUEL_ALL("RefuelAll", RefuelAll.class),
    REFUEL_PARTIAL("RefuelPartial", RefuelPartial.class),
    REPAIR("Repair", Repair.class),
    REPAIR_ALL("RepairAll", RepairAll.class),
    RESTOCK_VEHICLE("RestockVehicle", RestockVehicle.class),
    SELL_DRONES("SellDrones", SellDrones.class),
    SHIPYARD_BUY("ShipyardBuy", ShipyardBuy.class),
    SHIPYARD_NEW("ShipyardNew", ShipyardNew.class),
    SHIPYARD_SELL("ShipyardSell", ShipyardSell.class),
    SHIPYARD_SWAP("ShipyardSwap", ShipyardSwap.class),
    SHIPYARD_TRANSFER("ShipyardTransfer", ShipyardTransfer.class),
    
    /* Power Play */
    
    
    /*Other Events */
    COCKPIT_BREACHED("CockpitBreached", CockpitBreached.class),
    COMMIT_CRIME("CommitCrime", CommitCrime.class),
    DATALINK_SCANNED("DatalinkScan", DatalinkScan.class),
    DATA_SCANNED("DataScanned", DataScanned.class),
    DOCK_FIGHTER("DockFighter", DockFighter.class),
    DOCK_SRV("DockSRV", DockSRV.class),
    FUEL_SCOOP("FuelScoop", FuelScoop.class),
    LAUNCH_FIGHTER("LaunchFighter", LaunchFighter.class),
    LAUNCH_SRV("LaunchSRV", LaunchSRV.class),
    PROMOTION("Promotion", Promotion.class),
    REBOOT_REPAIR("RebootRepair", RebootRepair.class),
    RECIEVE_TEXT("ReceiveText", ReceiveText.class),
    RESURRECT("Resurrect", Resurrect.class),
    SELF_DESTRUCT("SelfDestruct", SelfDestruct.class),
    SEND_TEXT("SendText", SendText.class),
    SYNTHESIS("Synthesis", Synthesis.class),
    VEHICLE_SWITCH("VehicleSwitch", VehicleSwitch.class),
    WING_ADD("WingAdd", WingAdd.class),
    WING_JOIN("WingJoin", WingJoin.class),
    WING_LEAVE("WingLeave", WingLeave.class),
    
    UNKONWN("unknown");
    /* @formatter:on */

    private static final Logger LOGGER = LoggerFactory.getLogger(EDJournalEvents.class);

    private String code;

    private Class<? extends JournalModel> model;

    EDJournalEvents(final String code, final Class<? extends JournalModel> model) {
        this.code = code;
        this.model = model;
    }

    EDJournalEvents(final String code) {
        this.code = code;
        this.model = null;
    }


    /**
     * Gibt den Code des Events im Journal zurück
     * 
     * @return {@link String}
     */
    public String getCode() {
        return code;
    }

    /**
     * Ermittelt für den übergebenen Journalcode das passende Enum. Liefert {@link EDJournalEvents#UNKONWN} wenn keines
     * Zuordnet werden konnte.
     * 
     * @param codeForCheck {@link String}
     * @return {@link EDJournalEvents}
     */
    public static EDJournalEvents forCode(final String codeForCheck) {
        return Arrays.stream(EDJournalEvents.values()).filter(event -> event.getCode().equalsIgnoreCase(codeForCheck))
                .findFirst().orElse(UNKONWN);
    }

    @Override
    public JournalModel getModel(final JSONObject json) {
        if (model == null) {
            return null;
        }

        try {
            final Constructor<? extends JournalModel> ct = model.getConstructor(JSONObject.class);
            return ct.newInstance(json);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            LOGGER.error(e.toString(), e);
            throw Throwables.propagate(e);
        }
    }

    @Override
    public JournalEventAction getAction() {
        return new LoggingAction();
    }
}