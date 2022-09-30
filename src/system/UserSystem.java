package system;

import database.RailwayDatabase;
import inputreader.InputReader;
import inputreader.KeyboardInputReader;

import java.util.Scanner;

public class UserSystem implements BookingSystem{

    private RailwayDatabase railwayDatabase = RailwayDatabase.getInstance();
    InputReader inputReader = new KeyboardInputReader();
    @Override
    public void execute() {

    }
}
