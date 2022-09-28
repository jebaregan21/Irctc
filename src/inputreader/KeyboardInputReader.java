package inputreader;

import java.util.Scanner;

public class KeyboardInputReader implements InputReader{
    Scanner scan = new Scanner(System.in);

    @Override
    public String readLine() {
        return scan.nextLine();
    }

    @Override
    public int readInt() {
        return scan.nextInt();
    }

    @Override
    public void close() {
        scan.close();
    }
}
