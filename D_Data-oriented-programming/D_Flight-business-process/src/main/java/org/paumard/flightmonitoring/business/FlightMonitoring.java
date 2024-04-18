package org.paumard.flightmonitoring.business;

import org.paumard.flightmonitoring.FlightConsumer;
import org.paumard.flightmonitoring.model.Flight;
import org.paumard.flightmonitoring.model.FlightID;
import org.paumard.flightmonitoring.model.FlightPrice;
import org.paumard.flightmonitoring.service.FlightDBServiceInterface;
import org.paumard.flightmonitoring.service.FlightGuiInterface;
import org.paumard.flightmonitoring.service.FlightPriceMonitoringServiceInterface;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FlightMonitoring {

    private static final Map<FlightID, Flight> monitoredFlights = new ConcurrentHashMap<>();

    private final FlightDBServiceInterface dbService;
    private final FlightPriceMonitoringServiceInterface priceMonitoringService;
    private final FlightGuiInterface flightGUIService;

    public FlightMonitoring(FlightGuiInterface flightGUI,
                            FlightPriceMonitoringServiceInterface priceMonitoringService,
                            FlightDBServiceInterface flightDBService) {
        flightGUIService = flightGUI;
        this.priceMonitoringService = priceMonitoringService;
        this.dbService = flightDBService;
    }

    public static FlightMonitoring getInstance(FlightGuiInterface flightGUI,
                                               FlightPriceMonitoringServiceInterface priceMonitoringService,
                                               FlightDBServiceInterface flightDBService) {
        priceMonitoringService.updatePrices();
        FlightMonitoring flightMonitoring = new FlightMonitoring(flightGUI, priceMonitoringService, flightDBService);
        flightMonitoring.launchDisplay();
        return flightMonitoring;
    }

    public void followFlight(FlightID idFlight) {
        var flight = dbService.fetchFlight(idFlight);
        FlightID flightID = new FlightID(idFlight.flightId());
        FlightConsumer flightConsumer = price -> flight.updatePrice(new FlightPrice(price.price()));
        priceMonitoringService.followPrice(flightID, flightConsumer);
    }

    public void monitorFlight(FlightID idFlight) {
        var flight = dbService.fetchFlight(idFlight);
        monitoredFlights.put(idFlight, flight);
    }

    public void launchDisplay() {
        var executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
//            System.out.println("Displaying " + monitoredFlights.size() + " flights");
            for (var flight : monitoredFlights.values()) {
                flightGUIService.displayFlight(flight);
            }
        };
        executor.scheduleAtFixedRate(task, 0, 500, TimeUnit.MILLISECONDS);
    }
}