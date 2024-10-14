package packages.Mineswepper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan=new Scanner(System.in);
        Player player=new Player();
        player.start(scan);
    }
}
