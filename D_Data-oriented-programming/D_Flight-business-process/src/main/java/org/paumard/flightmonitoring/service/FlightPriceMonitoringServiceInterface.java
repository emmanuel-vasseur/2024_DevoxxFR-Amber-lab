package org.paumard.flightmonitoring.service;

import org.paumard.flightmonitoring.FlightConsumer;
import org.paumard.flightmonitoring.model.FlightID;

public interface FlightPriceMonitoringServiceInterface {
    void followPrice(FlightID flightID, FlightConsumer consumer);
    void updatePrices();
}
