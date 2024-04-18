package org.paumard.flightmonitoring.db;

import org.paumard.flightmonitoring.db.model.*;
import org.paumard.flightmonitoring.model.FlightID;
import org.paumard.flightmonitoring.model.Flight;
import org.paumard.flightmonitoring.model.FlightPrice;
import org.paumard.flightmonitoring.model.City;
import org.paumard.flightmonitoring.service.FlightDBServiceInterface;

import java.util.HashMap;
import java.util.Map;

public class FlightDBService implements FlightDBServiceInterface {

    private static Map<String, City> cities = Map.ofEntries(
            Map.entry("Pa", new City("Paris")),
            Map.entry("Lo", new City("London")),
            Map.entry("Am", new City("Amsterdam")),
            Map.entry("Fr", new City("Francfort")),
            Map.entry("NY", new City("New York")),
            Map.entry("Wa", new City("Washington")),
            Map.entry("At", new City("Atlanta")),
            Map.entry("Mi", new City("Miami"))
    );

    private static Map<FlightID, Flight> flights = new HashMap<>();

    public static FlightDBService getInstance() {
        return new FlightDBService();
    }

    public Flight fetchFlight(FlightID flightId) {
        System.out.println("Fetching flight " + flightId);

        return flights.computeIfAbsent(flightId,
                _ -> {
                    var from = flightId.flightId().substring(0, 2);
                    var to = flightId.flightId().substring(2);
                    return new Flight(new City(cities.get(from).name()), new City(cities.get(to).name()), new FlightPrice(100));
                    //return new Flight(flightId, cities.get(from), cities.get(to), new FlightPrice(100), new Plane("Airbus A350"));
                });
    }
}
