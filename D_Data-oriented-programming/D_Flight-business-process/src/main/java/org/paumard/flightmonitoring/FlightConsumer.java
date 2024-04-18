package org.paumard.flightmonitoring;

import org.paumard.flightmonitoring.model.FlightPrice;

public interface FlightConsumer {

    void updateFlight(FlightPrice price);
}
