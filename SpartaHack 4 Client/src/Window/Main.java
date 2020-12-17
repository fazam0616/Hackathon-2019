package Window;

import Game.Maps.Map;
import Game.Match;
import Game.Player;
import Game.Point;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static Window window;
    public static Match match;
    public static Socket socket;
    public static PrintWriter output;
    public static Scanner input;

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        String name = "";
        int r,g,b;
        Scanner keyboard = new Scanner(System.in);

        socket = new Socket("localhost",10000);
        output = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()),true);
        input = new Scanner(new BufferedInputStream(socket.getInputStream()));

        Socket matchSocket = new Socket("localhost",1001);
        ObjectInputStream matchInput = new ObjectInputStream(new BufferedInputStream(matchSocket.getInputStream()));



        System.out.print("Enter your username: ");
        name = keyboard.nextLine();

        System.out.print("Enter the RGB of you colour: ");
        r = keyboard.nextInt();
        g = keyboard.nextInt();
        b = keyboard.nextInt();

        output.println("start "+name+" ("+r+","+g+","+b+")");

        window = new Window();
        while (true){
            //match = (Match) matchInput.readObject();
            if (!window.pressed.equals("")){
                output.println("key "+name+" "+window.pressed);
                System.out.println("Sending");
            }

            window.repaint();
        }
    }
}
