import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class P2PServer extends TCPServer {
    private P2PClient p2PClient;

    P2PServer(String IPAddress, int serverID, int port, P2PClient p2PClient) throws UnknownHostException {
        super(IPAddress, serverID, port);
        this.p2PClient = p2PClient;
    }

    @Override
    protected void createTCPSocket() throws IOException {
        String clientMessage;
        BufferedImage httpResponse;
        ServerSocket welcomeSocket = new ServerSocket(this.getPort(), 0, this.getIPAddress());
        System.out.println("Server: " + this.getServerID() + " creating TCP Socket at: " + welcomeSocket.getInetAddress().toString() + ":" + welcomeSocket.getLocalPort());

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientMessage = inFromClient.readLine();

            System.out.println("server: " + getServerID() + " received: " + clientMessage + " from: " + connectionSocket.getRemoteSocketAddress().toString());
            httpResponse = handleClientMessage(clientMessage);
            ImageIO.write(httpResponse, "jpg", outToClient);
            connectionSocket.close();
        }
    }

     BufferedImage handleClientMessage(String clientMessage) throws IOException {
        return this.p2PClient.handleFileTransferRequest(clientMessage);
    }

    @Override
    void sendTCPMessage(String data, String dstIP, int dstPort) throws IOException {
        Socket clientSocket = new Socket(InetAddress.getByName(dstIP), dstPort, getIPAddress(), Constants.PEER_TCP_OUT_PORT);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        outToServer.writeBytes(data + '\n');
        outToServer.writeBytes(data + '\n');
        OutputStream os;

        try {
            File file = new File(p2PClient.getImagesPath() + "/newFile.jpeg");
            os = new FileOutputStream(file);
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = clientSocket.getInputStream().read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        clientSocket.close();
    }
}
