
import java.net.*;
import java.net.InetAddress;
import java.io.*;

public class Servidor{

    private ServerSocket socketServidor;
    private Socket socketCliente;
    private BufferedReader in;
    private PrintWriter out;
    private byte[] ipServidor = {100, 110, 53, 80};

    //necesitamos la IP del servidor
    public Servidor(int port){
        try{
            //InetAddress.getByAddress devuelve un objeto tipo InetAddress

            socketServidor = new ServerSocket(port,5, InetAddress.getByAddress(ipServidor));
        }catch(IOException e){
            System.out.println("Error al crear el socket servidor" + e.getMessage());
        }
    }

    public void levantarServer() {
        try {
            socketCliente = socketServidor.accept(); //devuelve el socket que va a usar luego el cliente
            System.out.println("Cliente conectado con ip: " + socketCliente.getInetAddress().toString());
            out = new PrintWriter(socketCliente.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        } catch (IOException e) {
            System.out.println("Error al recibir peticion" + e.getMessage());
        }
        String mensaje = null;
        do {
            try {
                mensaje = in.readLine();
                System.out.println("AAAAA");
                System.out.println(mensaje);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } while (mensaje != null);

    }

    public void pararDeEscuchar (){
        try{
            in.close();
            out.close();
            socketCliente.close();
            socketServidor.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    static void main(String[] args) {
        Servidor servidor = new Servidor(6000);
        servidor.levantarServer();

    }

}
