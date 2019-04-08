import java.io.IOException;

public class Main {

    public static void main(String args[]) throws IOException {

        ServerPool sp = new ServerPool();

        P2PClient p2PClient1 = new P2PClient(11, Constants.CLIENT_IP_1);
        P2PClient p2PClient2 = new P2PClient(12, Constants.CLIENT_IP_2);
        P2PClient p2PClient3 = new P2PClient(13, Constants.CLIENT_IP_3);
        P2PClient p2PClient4 = new P2PClient(14, Constants.CLIENT_IP_4);

        p2PClient1.init();
        p2PClient2.init();
        p2PClient3.init();
        p2PClient4.init();

        p2PClient1.informAndUpdate("/Users/Francesco/Desktop/P2PImages/a.jpeg");
        p2PClient2.informAndUpdate("/Users/Francesco/Desktop/P2PImages/b.jpeg");
        p2PClient3.informAndUpdate("/Users/Francesco/Desktop/P2PImages/c.jpeg");
        p2PClient4.informAndUpdate("/Users/Francesco/Desktop/P2PImages/d.jpeg");


        p2PClient1.queryForContent("c.jpeg");

        System.out.println("\nAFTER ADDING ALL IMAGES THE DIRECTORY HAVE THE FOLLOWING ENTRIES:\n");

        for (DirectoryServer ds : sp.getDirectoryServers()) {
            ds.printAllRecords();
        }

        p2PClient1.fileTransfer("c.jpeg");
        System.out.println("\nAFTER FILE TRANSFERS,THE DIRECTORY SERVERS HAVE THE FOLLOWING ENTRIES:\n");
        for (DirectoryServer ds : sp.getDirectoryServers()) {
            ds.printAllRecords();
        }

        p2PClient1.exit();
        p2PClient2.exit();
        p2PClient3.exit();
        p2PClient4.exit();

        System.out.println("\nAFTER CLIENTS EXIT,THE DIRECTORY SERVERS HAVE THE FOLLOWING ENTRIES:\n");

        for (DirectoryServer ds : sp.getDirectoryServers()) {
            ds.printAllRecords();
        }

    }
}