import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws IOException {


        Scanner scan= new Scanner(System.in);

        System.out.println("Ds or P: ");

        String type=scan.nextLine();
        String id;

        System.out.println("Specify ID:");
        id=scan.nextLine();



        System.out.println("Creating: " + type +" with ID: " + id);

        String localhost = P2PClient.parsePath(InetAddress.getLocalHost().toString());

        System.out.println(localhost);

        if(type.equals("ds")){
           DirectoryServer ds1 = new DirectoryServer(localhost, Integer.parseInt(id));
           ds1.openUDPSocket();
           ds1.openTCPSocket();

           System.out.println("IP of right neighbour:");
                                            
           String neighbour=scan.nextLine();
           ds1.setRightNeighbor(neighbour);

        }



//
//        ServerPool sp = new ServerPool();
//
//        P2PClient p2PClient1 = new P2PClient(11, Constants.CLIENT_IP_1);
//        P2PClient p2PClient2 = new P2PClient(12, Constants.CLIENT_IP_2);
//        P2PClient p2PClient3 = new P2PClient(13, Constants.CLIENT_IP_3);
//        P2PClient p2PClient4 = new P2PClient(14, Constants.CLIENT_IP_4);
//
//        p2PClient1.init();
//        p2PClient2.init();
//        p2PClient3.init();
//        p2PClient4.init();
//
//        p2PClient1.informAndUpdate("/Users/Francesco/Desktop/P2PImages/a.jpeg");
//        p2PClient2.informAndUpdate("/Users/Francesco/Desktop/P2PImages/b.jpeg");
//        p2PClient3.informAndUpdate("/Users/Francesco/Desktop/P2PImages/c.jpeg");
//        p2PClient4.informAndUpdate("/Users/Francesco/Desktop/P2PImages/d.jpeg");
//
//
//        System.out.println("\nAFTER ADDING ALL IMAGES THE DIRECTORY HAVE THE FOLLOWING ENTRIES:\n");
//
//        for (DirectoryServer ds : sp.getDirectoryServers()) {
//            ds.printAllRecords();
//        }
//
//        p2PClient1.fileTransfer("c.jpeg");
//        System.out.println("\nAFTER FILE TRANSFERS,THE DIRECTORY SERVERS HAVE THE FOLLOWING ENTRIES:\n");
//        for (DirectoryServer ds : sp.getDirectoryServers()) {
//            ds.printAllRecords();
//        }
//
////        p2PClient1.exit();
////        p2PClient2.exit();
////        p2PClient3.exit();
////        p2PClient4.exit();
//
//        System.out.println("\nAFTER CLIENTS EXIT,THE DIRECTORY SERVERS HAVE THE FOLLOWING ENTRIES:\n");
//
//        for (DirectoryServer ds : sp.getDirectoryServers()) {
//            ds.printAllRecords();
//        }

    }
}