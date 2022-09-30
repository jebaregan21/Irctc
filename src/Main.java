import database.RailwayDatabase;
import datamodel.CarriageInfo;
import datamodel.Quota;
import datamodel.Time;
import datamodel.TrainSearchModel;
import exception.InvalidTimeException;
import exception.NoVacancyException;
import model.Carriage;
import model.Passenger;
import model.Station;
import model.Train;
import utility.DateUtility;

import java.text.ParseException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ParseException, NoVacancyException, InvalidTimeException {
//        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("12/08/2022");
//        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse("13/08/2022");
//        Date date2 = DateUtility.addToDate(date,1);
//        Map<Date,Integer> map = new HashMap<>();
//        map.put(date,2);
//        map.put(date1,5);
//        map.put(date2,7);
//        System.out.println(map.get(date1));

        CarriageInfo carriageInfo = new CarriageInfo(64,10,"SLEEPER","SU UB M L");

        Carriage carriage = new Carriage(carriageInfo);
//
//        Train train = new Train(11345,"Rameshwaram Express");
//        train.addCarriage(carriage,350);
        Station station1 = new Station("COIMBATORE","CBE");
        Station station2 = new Station("SIVAGANGA","SVG");
        Station station3 = new Station("MADURAI","MDU");
        Station station4 = new Station("CHENNAI","CHN");

        RailwayDatabase railwayDatabase = RailwayDatabase.getInstance();
        railwayDatabase.addStation(station1);
        railwayDatabase.addStation(station2);
        railwayDatabase.addStation(station3);
        railwayDatabase.addStation(station4);

        Passenger passenger = new Passenger("Jeba","MALE",20, Quota.GENERAL, station1, station2);
        Passenger passenger1 = new Passenger("Regan","MALE",30,Quota.GENERAL,station1,station4);
        Passenger passenger3 = new Passenger("Raj","MALE",60,Quota.GENERAL,station2,station4);
        Passenger passenger4 = new Passenger("Cool","MALE",44,Quota.GENERAL,station1,station4);

        Train train = new Train(15334,"Chennai Express");
        train.addStop(station1,new Time("0 18 45"));
        train.addStop(station2,new Time("1 2 45"));
        train.addStop(station3,new Time("1 3 45"));
        train.addStop(station4,new Time("1 7 00"));

        railwayDatabase.addTrain(train);

        train.addCarriage(carriage,350);
        List<Passenger> list1 = new ArrayList<>();
        list1.add(passenger);
        list1.add(passenger4);
        List<Passenger> list2 = new ArrayList<>();
        list2.add(passenger1);
        list2.add(passenger3);

        System.out.println(train.bookGeneralTicket(list1,"SLEEPER"));
        System.out.println(train.bookGeneralTicket(list2,"SLEEPER"));

        TrainSearchModel model = new TrainSearchModel("SVG","CHN","03/10/2022");
        System.out.println(railwayDatabase.getTrainsBetween(model));
        System.out.println(railwayDatabase.getTrainsBetween(model).get(0).getAvailability());
//        List<Passenger> list = new ArrayList<>();
//        list.add(passenger);
//        list.add(passenger);
//        list.add(passenger1);
//
//        System.out.println(train.bookGeneralTicket(list,"SLEEPER"));
//        System.out.println(carriage.getChart());
    }
}