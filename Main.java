package packages.Mineswepper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner inputGetter=new Scanner(System.in);
        Player player=new Player();
        player.start(inputGetter);
    }
}
