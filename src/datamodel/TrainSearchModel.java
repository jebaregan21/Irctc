package datamodel;

import database.RailwayDatabase;
import model.Station;
import utility.DateUtility;

import java.text.ParseException;
import java.util.Date;

public class TrainSearchModel {
    private Station source,destination;
    private Date date;

    public TrainSearchModel(String source, String destination, String date){
        RailwayDatabase railwayDatabase = RailwayDatabase.getInstance();
        this.source = railwayDatabase.getStation(source);
        this.destination = railwayDatabase.getStation(destination);
        try {
            DateUtility dateUtility = new DateUtility(date);
            this.date = dateUtility.getDate();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Station getSource() {
        return source;
    }

    public Station getDestination() {
        return destination;
    }

    public Date getDate() {
        return date;
    }
}
