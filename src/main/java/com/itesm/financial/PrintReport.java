package com.itesm.financial;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class PrintReport {

    public String createContent(List<Ride> rides) {
        StringBuilder builder = new StringBuilder();
        builder.append(createHeaders("Taxi Report"));
        builder.append(createTableHeaders());
        rides.forEach( ride -> {
            builder.append(addRide(ride));
        });
        
        return builder.toString();
    }

    public void createFile(String content) throws IOException {
        FileWriter fileWriter = new FileWriter("financial-report.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(content);
        printWriter.close();
    }

    private String createHeaders(String title){
        return title + "\n";
    }

    private String createTableHeaders() {
        return 
            "TaxiID \t" +
            "Pickup time \t\t\t\t\t" +
            "Dropoff time \t\t\t\t\t" +
            "Passenger count \t" +
            "Trip Distance \t" +
            "Total amount \n";
    }

    private String addRide(Ride ride) {
        return
            formatId(ride.getTaxiId()) + "\t" +
            ride.getPickUpTime() + "\t" +
            ride.getDropOffTime() + "\t" +
            ride.getPassengerCount() + "\t\t\t\t\t" +
            formatDistance(ride.getTripDistance()) + "\t\t\t" +
            formatAmount(ride.getTollsAmount()) + "\n";
    }

    private String formatAmount(double amount) {
        if(amount < 0) {
            return "(" + amount + ")";
        }
        return Double.toString(amount);
    }

    private String formatId(long id) {
        if(id < 1000) {
            return id + "\t";
        }
        return Long.toString(id);
    }

    private String formatDistance(double distance) {
        return String.format("%.2f",distance);
    }
    
}
