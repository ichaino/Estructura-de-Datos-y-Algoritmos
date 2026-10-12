import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;
import java.util.Scanner;

public class Lab2{

    class Cancion {
        public String nombre ;
        public int duracion ; // en segundos
        public String artista ;
        public Cancion next;
    
        public Cancion ( String nombre , int duracion , String artista ){
            this. nombre = nombre ;
            this. duracion = duracion ;
            this. artista = artista ;
            this.next = null;
            }
    }

    private Cancion head;
    private Stack<Cancion> historialDeReproduccion;
    private LinkedList<Queue<Cancion>> bibliotecaDeReproduccion;
    private int counter;

    public Lab2(){
        this.head = null;
        historialDeReproduccion = new Stack<>();
        bibliotecaDeReproduccion = new LinkedList<>();
        counter = 0;
    }


    public void agregarCancion(String nombre, int duracion, String artista)
    {
        Cancion newSong = new Cancion(nombre, duracion, artista);

        if(this.head == null){
            this.head = newSong;
        }

        else{
            Cancion aux = this.head;
            while(aux.next != null)
            {
                aux = aux.next;
            }
            aux.next = newSong;
        }
    }

    public void eliminarCancion(String nombre, String artista)
    {
        Cancion prev = null;
        Cancion current = this.head;

        while(current != null)
        {
            if(current.nombre.equals(nombre) && current.artista.equals(artista))
            {
                if(prev == null)
                {
                    this.head = current.next;
                }
                
                else
                {
                    prev.next = current.next;
                }
                System.out.println("Cancion: [" + current.nombre + " - " + current.artista + "] eliminada.");
                return;
            }
            prev = current;
            current = current.next;
            
        }
        System.out.println("Canción no encontrada en la cola de reproduccion.");
    }

    public void reproducir()
    {
        if(this.head == null)
        {
            System.out.println("No se encuentran canciones en la cola de reproduccion");
            return;
        }

        Cancion playSong = this.head;
        this.head = this.head.next;

        historialDeReproduccion.push(playSong);
        System.out.println("Reproduciendo: " + playSong.nombre + " - " + playSong.artista);

    }

    public void imprimirHistorialDeReprodiccion()
    {   
        System.out.println("Historial de Reproduccion: ");
        Stack<Cancion> temp = new Stack<>();
        while(!historialDeReproduccion.isEmpty())
        {
            Cancion song = historialDeReproduccion.pop();
            temp.push(song);
            System.out.println(song.nombre + " - " + song.artista);
        }

        while(!temp.isEmpty())
        {
            historialDeReproduccion.push(temp.pop());
        }
    }

    public void crearColaDeReproduccion() 
     {   
        Queue<Cancion> newCola = new LinkedList<>();
        bibliotecaDeReproduccion.add(newCola);
        System.out.println("Cola de reproduccion #" + counter + " creada." );
       counter++;
    }

    public void eliminarColaDeReproduccion(int num)
    {
        if(num >= 0 && num < bibliotecaDeReproduccion.size())
        {
            bibliotecaDeReproduccion.remove(num);
            counter--;
            System.out.println("Cola de Reproduccion #" + num + " eliminada.");
        }

        else{
            System.out.println("La Cola de Reproduccion #" + num + " no se encuentra creada.");
        }
    }

    public void agregarCancionColaReproduccion(int num, String nombre, int duracion, String artista)
    {
        if(num >= 0 && num < bibliotecaDeReproduccion.size())
        {
            Queue<Cancion> colaReproduccion = bibliotecaDeReproduccion.get(num);
            Cancion newSong = new Cancion(nombre, duracion, artista);
            colaReproduccion.add(newSong);
            System.out.println("Cancion agregada a la Cola de Reproduccion #" + num);
        }

        else{
            System.out.println("No se encuentra la cola de reproduccion #" + num);
        }
    }

    public void buscarCancion(String buscar)
    {
        boolean aux = false;
        Cancion current = this.head;

        while(current != null)
        {
            if(current.nombre.equals(buscar) || current.artista.equals(buscar))
            {
                System.out.println("Cancion encontrada en la cola de reproduccion: [" + current.nombre + " - " + current.artista + " (" + current.duracion + "s)]");
                aux = true;
            }
            current = current.next;
        }

        for(int i=0; i<historialDeReproduccion.size();i++)
        {
            Cancion song = historialDeReproduccion.get(i);
            if(song.nombre.equals(buscar) || song.artista.equals(buscar))
            {
                System.out.println("Cancion encontrada en el historial de reproduccion: " + song.nombre + " - " + song.artista + " (" + song.duracion + ")");
                aux = true;
            }
        }

        if(!aux)
        {
            System.out.println("No se encontro ninguna cancion con dicho Nombre/Artista.");
        }
    }

    public void reproducirPlaylist(int num)
    {
        if(num >= 0 && num < bibliotecaDeReproduccion.size())
        {
            Queue<Cancion> playList = bibliotecaDeReproduccion.get(num);
            if(!playList.isEmpty())
            {
                System.out.println("Reproduciendo PlayList Seleccionada.");
                Cancion song = playList.poll();
                System.out.println("Reproduciendo: [" + song.nombre + " - " + song.artista + "(" + song.duracion + "s)]");
                historialDeReproduccion.push(song);
            }
            else{
                System.out.println("La PlayList seleccionada esta vacia.");
            }
        }
        else{
            System.out.println("La PlayList #" + num + " no existe");
        }   
    }


    public void estadisticasDeReproduccion() 
    {
        if (historialDeReproduccion.isEmpty()) 
        {
            System.out.println("No se han reproducido canciones aun.");
            return;
        }
    
        String[] songs = new String[historialDeReproduccion.size()];
        int[] reproducciones = new int[historialDeReproduccion.size()];
        int duracionTotal = 0; 
        int aux = 0; 
    
        for (int i = 0; i < historialDeReproduccion.size(); i++) 
        {
            Cancion song = historialDeReproduccion.get(i);
            duracionTotal += song.duracion;
            String claveCancion = song.nombre + " - " + song.artista;
            boolean encontrada = false;

            for (int j = 0; j < aux; j++) 
            {
                if (songs[j].equals(claveCancion)) 
                {
                    reproducciones[j]++;
                    encontrada = true;
                    break;
                }
            }
    
            if (!encontrada) 
            {
                songs[aux] = claveCancion;
                reproducciones[aux] = 1;
                aux++;
            }
        }
    
        System.out.println("Canciones mas reproducidas:");
        for (int i = 0; i < aux; i++) 
        {
            System.out.println(songs[i] + ": " + reproducciones[i] + " reproducciones");
        }
    
        System.out.println("Duracion total de reproduccion:");
        if (duracionTotal >= 3600) 
        {
            int horas = duracionTotal / 3600;
            int minutos = (duracionTotal % 3600) / 60;
            int segundos = duracionTotal % 60;
            System.out.println(horas + " horas, " + minutos + " minutos, " + segundos + " segundos");
        } 

        else if (duracionTotal >= 60) 
        {
            int minutos = duracionTotal / 60;
            int segundos = duracionTotal % 60;
            System.out.println(minutos + " minutos, " + segundos + " segundos");
        } 
        
        else 
        {
            System.out.println(duracionTotal + " segundos");
        }
    }

    public void mostrarSiguientes() {
        if (head == null) {
            System.out.println("No hay canciones en la cola de reproduccion.");
            return;
        }
    
        System.out.println("Siguientes 10 canciones a reproducir:");
    
        Cancion current = head;
        int count = 0;
        while (current != null && count < 10) {
            System.out.println(current.nombre + " - " + current.artista + " (" + current.duracion + "s)");
            current = current.next;
            count++;
        }
    
        if (count == 0) {
            System.out.println("No hay más canciones en la cola de reproduccion.");
        }
    }

    public void mostrarUltimas10CancionesHistorial() 
    {
        System.out.println("Últimas 10 canciones del historial de reproducción:");
        Stack<Cancion> tempStack = new Stack<>();
        int count = 0;
    
        while (!historialDeReproduccion.isEmpty() && count < 10) {
            Cancion song = historialDeReproduccion.pop();
            tempStack.push(song);
            count++;
        }
    
        while (!tempStack.isEmpty()) {
            Cancion song = tempStack.pop();
            System.out.println(song.nombre + " - " + song.artista);
        }
    
        while (!tempStack.isEmpty()) {
            historialDeReproduccion.push(tempStack.pop());
        }
    }
        
    public static void main(String[] args) {
        Lab2 menu = new Lab2();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\n=== Menú ===");
            System.out.println("1. Agregar Cancion");
            System.out.println("2. Eliminar Cancion");
            System.out.println("3. Historial de Reproduccion");
            System.out.println("4. Reproducir Cancion");
            System.out.println("5. Buscar Cancion");
            System.out.println("6. Reproducir Playlist");
            System.out.println("7. Crear Cola de Reproduccion Personalizada (Playlist)");
            System.out.println("8. Agregar Cancion a Cola de Reproduccion (Playlist)");
            System.out.println("9. Eliminar Cola de Reproduccion (Playlist)");
            System.out.println("10. Estadisticas de Reproduccion");
            System.out.println("11. Mostrar siguientes 10 canciones a reproducir");
            System.out.println("12. Mostrar siguientes 10 canciones en el historial");
            System.out.println("13. Salir");
            System.out.print("Ingrese una opcion: ");
            opcion = scanner.nextInt();
        
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre de la cancion: ");
                    String nombre = scanner.next();
                    System.out.print("Ingrese la duracion de la cancion (en segundos): ");
                    int duracion = scanner.nextInt();
                    System.out.print("Ingrese el nombre del artista: ");
                    String artista = scanner.next();
                    menu.agregarCancion(nombre, duracion, artista);
                    break;
                case 2:
                    System.out.print("Ingrese el nombre de la cancion a eliminar: ");
                    String nombreEliminar = scanner.next();
                    System.out.print("Ingrese el nombre del artista de la cancion a eliminar: ");
                    String artistaEliminar = scanner.next();
                    menu.eliminarCancion(nombreEliminar, artistaEliminar);
                    break;
                case 3:
                    menu.imprimirHistorialDeReprodiccion();
                    break;
                case 4:
                    menu.reproducir();
                    break;
                case 5:
                    System.out.print("Ingrese el nombre o el artista de la cancion a buscar: ");
                    String buscar = scanner.next();
                    menu.buscarCancion(buscar);
                    break;
                case 6:
                    System.out.print("Ingrese el numero de la playlist a reproducir: ");
                    int numPlaylist = scanner.nextInt();
                    menu.reproducirPlaylist(numPlaylist);
                    break;
                case 7:
                    menu.crearColaDeReproduccion();
                    break;
                case 8:
                    System.out.print("Ingrese el numero de la cola de reproduccion: ");
                    int numCola = scanner.nextInt();
                    System.out.print("Ingrese el nombre de la cancion a agregar: ");
                    String nombreCancion = scanner.next();
                    System.out.print("Ingrese la duracion de la cancion (en segundos): ");
                    int duracionCancion = scanner.nextInt();
                    System.out.print("Ingrese el nombre del artista: ");
                    String artistaCancion = scanner.next();
                    menu.agregarCancionColaReproduccion(numCola, nombreCancion, duracionCancion, artistaCancion);
                    break;
                case 9:
                    System.out.print("Ingrese el numero de la cola de reproduccion a eliminar: ");
                    int numColaEliminar = scanner.nextInt();
                    menu.eliminarColaDeReproduccion(numColaEliminar);
                    break;
                case 10:
                    menu.estadisticasDeReproduccion();
                    break;
                case 11:
                    menu.mostrarSiguientes();
                    break;
                case 12:
                    menu.mostrarUltimas10CancionesHistorial();
                    break;
                case 13:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción invalida, por favor ingrese un número entre 1 y 13.");
            }
        } while (opcion != 13);
        
        scanner.close();
    }
}

