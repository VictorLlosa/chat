import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Cliente {
    private Socket socket;
    private Scanner scanner;
    private PrintWriter out;
    private byte[] ipCliente = {100,85,63,47};
    private byte[] ipServidor = {100, 110, 53, 80};
    private int puertoServidor = 6000; //hacer un get para que no tengamos que cambiarlo cada vez

    public Cliente(){
        try{
            socket = new Socket(InetAddress.getByName("127.0.0.1"), puertoServidor);
            //scanner = new Scanner(System.in, System.getProperty("stdin.encoding"));
            out = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            System.out.println(e.getMessage() + " se ha lanzado una IO Exception en la ctr de Cliente ");
        }
    }

    public void enviarMensaje(String mensaje){
        out.println(mensaje);
    }
    static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.enviarMensaje("Hola");
    }
}

