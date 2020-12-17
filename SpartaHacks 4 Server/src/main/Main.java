package main;

import game.Map;
import game.Match;
import game.Player;
import game.Point;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static Socket socket;
    public static ServerSocket server;
    public static Scanner input = null;
    public static PrintWriter output = null;
    public static LinkedList<String> commands = new LinkedList<>();

    public static Thread reader;
    public static Thread acceptor;
    public static Match match;
    public static Thread updater;

    public static ServerSocket serializedServer;
    public static Socket serializedSocket;
    public static ObjectOutputStream oos = null;

    public static boolean matchWait = false;
    public static boolean socketWait = false;

    public static void main(String[] args) throws IOException, InterruptedException {
        match = new Match(new Map("src\\maps\\Map1.bmp"));
        server = new ServerSocket(10000);
        serializedServer = new ServerSocket(1001);
        System.out.println("Initialized...");
        /*
        Reads input stream and sends data to command list
         */
        reader = new Thread(()->{
            while (!input.hasNextLine()){

            }
            System.out.println("Reading line...");
            while (true){
                long currentTime = System.currentTimeMillis();
                while (socketWait){}
                socketWait = true;
                while (input.hasNextLine()){
                    String data = input.nextLine();
                    System.out.println(data);
                    Scanner stringReader = new Scanner(data);
                    String func = stringReader.next();

                    switch(func){
                        case "start":
                            System.out.println("Initiating player...");
                            startPlayer(data);
                            break;
                        case "key":
                            keyPress(data);
                            break;
                    }
                    long passedTime = System.currentTimeMillis()-currentTime;
                    try {
                        Thread.sleep(1000/60-(passedTime < 1000/60 ? passedTime : -1000/60));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                socketWait = false;
            }
        });

        /*
        Accepts requests to server
         */
        acceptor = new Thread(()->{
            try {
                socket = server.accept();
                input = new Scanner(new InputStreamReader(socket.getInputStream()));
                reader.start();
                System.out.println("User Connected!");
            } catch (IOException e) {}
            while (true){
                try {
                    socket = server.accept();
                    output = new PrintWriter(socket.getOutputStream(),true);
                    System.out.println("User Connected!");

                    while (socketWait){}
                    socketWait = true;
                    input = new Scanner(new InputStreamReader(socket.getInputStream()));
                    socketWait = false;

                } catch (Exception e){}
        }});

        /*
        Accepts connection attempts to the match-updater socket
         */
        Thread socketAcceptor = new Thread(()->{
            try {
                serializedSocket = serializedServer.accept();
                oos = new ObjectOutputStream(serializedSocket.getOutputStream());
                oos.flush();
                //updater.start();
            } catch(Exception e){}
            while (true){
                try {
                    serializedSocket = serializedServer.accept();
                    while (matchWait){}
                    matchWait = true;
                    oos = new ObjectOutputStream(serializedSocket.getOutputStream());
                    matchWait = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        updater = new Thread(()->{
            while (true){
                try {
                    while (matchWait){}
                    matchWait = true;
                    oos.writeObject(match);
                    matchWait =false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        socketAcceptor.start();
        acceptor.start();
        Window window = new Window();

        while (true){
            window.repaint();
        }
    }

    public static void keyPress(String data){
        Scanner stringReader = new Scanner(data);
        stringReader.next();
        String userName = stringReader.next();
        String key = stringReader.next();
        Player player = null;
        for(Player p:match.players){
            if (p.username.equals(userName.trim())){
                player = p;
            }
        }
        key = key.toUpperCase();
        if (player!=null){
            for(char c:key.toCharArray()){
                switch (c){
                    case 'W':
                        player.pos.y -= player.speed*1.1;
                        break;
                    case 'S':
                        player.pos.y += player.speed*1.1;
                        break;
                    case 'D':
                        player.pos.x += player.speed*1.1;
                        break;
                    case 'A':
                        player.pos.x -= player.speed*1.1;
                        break;
                }
            }
        }
        player.updateCollision();
    }

    public static void startPlayer(String data){
        Scanner stringReader = new Scanner(data);
        stringReader.next();

        String username = stringReader.next().trim();
        String color = stringReader.next();
        color = color.substring(1,color.length()-1).replaceAll(","," ");
        Scanner colorReader = new Scanner(color);
        Point pos = new Point(Math.random()*match.map.map.length*10,
                Math.random()*match.map.map[0].length*10);
        int r,g,b;
        System.out.println("Welcome "+username+" spawning at "+pos.x+", "+pos.y);
        r = colorReader.nextInt();
        g = colorReader.nextInt();
        b = colorReader.nextInt();

        Color realColor = new Color(r,g,b);

        match.players.add(new Player(username,realColor, pos));
    }
}
