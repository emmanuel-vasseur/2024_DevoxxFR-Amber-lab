package org.paumard.flightmonitoring.gui;

import org.paumard.flightmonitoring.service.FlightGuiInterface;
import org.paumard.flightmonitoring.model.Flight;

public class FlightGUI implements FlightGuiInterface {

    public static FlightGUI getInstance() {
        return new FlightGUI();
    }

    public void displayFlight(Flight flight) {
        System.out.println(
                "Flight from " + flight.from().name() + " to " + flight.to().name() +
                ": price is now " + flight.price().price());
    }
}
