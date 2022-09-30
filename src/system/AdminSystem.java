package system;

import database.RailwayDatabase;
import inputreader.InputReader;
import inputreader.KeyboardInputReader;

public class AdminSystem implements BookingSystem {

    private RailwayDatabase railwayDatabase = RailwayDatabase.getInstance();
    InputReader inputReader = new KeyboardInputReader();
    @Override
    public void execute() {

    }
}
