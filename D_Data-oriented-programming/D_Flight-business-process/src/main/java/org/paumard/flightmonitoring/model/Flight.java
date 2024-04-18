package org.paumard.flightmonitoring.model;

public record Flight(
        City from,
        City to,
        FlightPrice price
) {
    public void updatePrice(FlightPrice price) {
        //this.price = price;
    }
}
