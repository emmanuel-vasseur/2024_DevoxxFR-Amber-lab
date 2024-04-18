package org.paumard.flightmonitoring;

import org.paumard.flightmonitoring.business.FlightMonitoring;
import org.paumard.flightmonitoring.db.FlightDBService;
import org.paumard.flightmonitoring.db.model.Flight;
import org.paumard.flightmonitoring.db.model.IDFlight;
import org.paumard.flightmonitoring.gui.FlightGUI;
import org.paumard.flightmonitoring.model.FlightID;
import org.paumard.flightmonitoring.pricemonitoring.FlightPriceMonitoringService;

public class Main {

    public static void main(String[] args) {

        var flightMonitoring = FlightMonitoring.getInstance(
                FlightGUI.getInstance(),
                FlightPriceMonitoringService.getInstance(),
                FlightDBService.getInstance()
        );

        var f1 = new FlightID("PaAt");
        var f2 = new FlightID("AmNe");
        var f3 = new FlightID("LoMi");
        var f4 = new FlightID("FrWa");

        flightMonitoring.followFlight(f1);
        flightMonitoring.followFlight(f2);
        flightMonitoring.followFlight(f3);
        flightMonitoring.followFlight(f4);

        flightMonitoring.monitorFlight(f3);
        flightMonitoring.monitorFlight(f4);

        while (true) {

        }
    }
}