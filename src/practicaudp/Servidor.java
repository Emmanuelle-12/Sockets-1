package practicaudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) throws IOException {
        final int porte = 5001;
        int cent = 0;
        byte[] buffer = new byte[1024];

        DatagramSocket socketUDP = new DatagramSocket(porte);
        try {          
            
            do{
                System.out.println("El servidor UDP esta iniciado!");//confirmamos de que estamos dentro del servidor
                InetAddress direccionCliente = InetAddress.getByName("Aqui va la ip del cliente");
            
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

                socketUDP.receive(peticion);//recibimos la peticion que solicito el cliente
                System.out.println("Se recibio la siguiente informacion: ");//mostramos en pantalla la confirmacion
                String mensaje = new String(peticion.getData());//guardamos en la variable mensaje la misma cadena que se recibio del cliente
                
                          
                
                
                System.out.println(mensaje);//mostramos por pantalla el mensaje recibido

                int puertoCliente = peticion.getPort();//obtenemos el puerto
                InetAddress direccion = peticion.getAddress();//obtenemos la direccion ip donde de donde se solicito la peticion

                buffer = mensaje.getBytes();//comprobamos el tamanio del mensaje
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);//empaquetamos el mismo mensaje  recibido

                System.out.println("Se envio la respuesta del servidor UDP!");//mostramos la confirmacion de que se envio la respuesta al cliente
                socketUDP.send(respuesta);//enviamos la respuesta(mismo mensaje recibido) al cliente                
            }while(cent == 0);
            
        } catch (SocketException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IOException ex){
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
