package at.technikum;

import at.technikum.apps.display.DisplayApp;
import at.technikum.apps.mtcg.MtcgApp;
import at.technikum.server.Server;

import java.sql.SQLException;

public class main {

    public static void main(String[] args){

        Server server = new Server(new MtcgApp());
        server.start();
    }
}