import datamodel.Time;
import exception.InvalidTimeException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvalidTimeException {
        List<Time> list = new ArrayList<>();
        list.add(new Time("1 22 30"));
        list.add(new Time("0 03 22"));
        list.add(new Time("0 9 55"));
        list.add(new Time("2 18 00"));
        Collections.sort(list);
        System.out.println(list);
    }
}