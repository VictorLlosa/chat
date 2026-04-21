import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Cliente {
    private Socket socket;
    private Scanner scanner;
    private OutputStream out;
    private byte[] ipCliente = {100,85,63,47};
    private byte[] ipServidor = {100, 110, 53, 80};
    private int puertoServidor = 6000; //hacer un get para que no tengamos que cambiarlo cada vez

    public Cliente(int port){
        try{
            socket = new Socket(InetAddress.getByAddress(ipServidor), puertoServidor, InetAddress.getByAddress(ipCliente), port);
            scanner = new Scanner(System.in, System.getProperty("stdin.encoding"));
            out = socket.getOutputStream();

        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void enviarMensajes(){
        String mensaje;
        byte[] bytes;
        while(true){
            System.out.println("Escribe un mensaje:\n");
            mensaje = scanner.nextLine();
            bytes = mensaje.getBytes(StandardCharsets.UTF_8);
            try {
                out.write(bytes);
            } catch (IOException e) {
                System.out.println("Error al enviar" + e.getMessage());
            }

        }
        
    }
    public static void main(String[] args) {
        Cliente cliente = new Cliente(0);
        cliente.enviarMensajes();
    }
}

