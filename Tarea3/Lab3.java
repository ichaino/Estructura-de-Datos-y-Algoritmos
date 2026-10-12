package Labs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class NodoArbol {
    Cancion cancion;
    NodoArbol left;
    NodoArbol right;

    public NodoArbol(Cancion cancion) {
        this.cancion = cancion;
        this.left = null;
        this.right = null;
    }
}

class Cancion {
    public int id;
    public String nombre;
    public String artista;
    public double duracion; // en milisegundos

    public Cancion(int id, String nombre, String artista, double duracion) {
        this.id = id;
        this.nombre = nombre;
        this.artista = artista;
        this.duracion = duracion;
    }
}

class BaseDeDatos {

    private List<Cancion> listaDeCanciones;
    private NodoArbol rootPorNombre;
    private NodoArbol rootPorDuracion;

    public BaseDeDatos() {
        listaDeCanciones = new ArrayList<>();
    }

    public void agregarCancion(Cancion song) {
        listaDeCanciones.add(song);
    }

    public List<Cancion> obtenerTodasLasCanciones() {
        return listaDeCanciones;
    }

    public void crearArbol() {
        rootPorNombre = null;
        rootPorDuracion = null;
        
        for (int i = 0; i < listaDeCanciones.size(); i++) {
            Cancion cancion = listaDeCanciones.get(i);

            rootPorNombre = insertarNodoPorNombre(rootPorNombre, cancion);
            rootPorDuracion = insertarNodoPorDuracion(rootPorDuracion, cancion);
        }
    }

    private NodoArbol insertarNodoPorNombre(NodoArbol nodo, Cancion cancion) {
        if (nodo == null) {
            return new NodoArbol(cancion);
        }

        if (cancion.nombre.compareToIgnoreCase(nodo.cancion.nombre) < 0) {
            nodo.left = insertarNodoPorNombre(nodo.left, cancion);
        } else {
            nodo.right = insertarNodoPorNombre(nodo.right, cancion);
        }
        return nodo;
    }

    private NodoArbol insertarNodoPorDuracion(NodoArbol nodo, Cancion cancion) {
        if (nodo == null) {
            return new NodoArbol(cancion);
        }

        if (cancion.duracion < nodo.cancion.duracion) {
            nodo.left = insertarNodoPorDuracion(nodo.left, cancion);
        } else {
            nodo.right = insertarNodoPorDuracion(nodo.right, cancion);
        }
        return nodo;
    }

    public int buscarCancion(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return -1;
        }
        return buscarEnArbolPorNombre(rootPorNombre, nombre);
    }

    private int buscarEnArbolPorNombre(NodoArbol nodo, String nombre) {
        if (nodo == null) {
            return -1;
        }

        String nombreNormalizado = nombre;
        String nombreNodoNormalizado = nodo.cancion.nombre;

        if (nombreNormalizado.equals(nombreNodoNormalizado)) {
            return nodo.cancion.id;
        } 
        else if (nombreNormalizado.compareTo(nombreNodoNormalizado) < 0) {
            return buscarEnArbolPorNombre(nodo.left, nombre);
        } 
        else {
            return buscarEnArbolPorNombre(nodo.right, nombre);
        }
    }

    public List<Integer> buscarCancionesPorDuracion(double duracion) {
        List<Integer> ids = new ArrayList<>();
        buscarEnArbolPorDuracion(rootPorDuracion, duracion, ids);
        return ids;
    }

    private void buscarEnArbolPorDuracion(NodoArbol nodo, double duracion, List<Integer> ids) {
        if (nodo == null) {
            return;
        }

        if (nodo.cancion.duracion == duracion) {
            ids.add(nodo.cancion.id);
        }

        buscarEnArbolPorDuracion(nodo.left, duracion, ids);
        buscarEnArbolPorDuracion(nodo.right, duracion, ids);
    }


    // ===== TIEMPO DE EJECUCION POR DURACION ===== \\

    public void TiempoBubbleSortPorDuracion() {
        long start = System.nanoTime();
        BubbleSortPorDuracion();
        long end = System.nanoTime();
        System.out.println("BubbleSort por duracion - Tiempo de ejecucion: " + (end - start) + " ns");
    }

    public void TiempoInsertionSortPorDuracion() {
        long start = System.nanoTime();
        InsertionSortPorDuracion();
        long end = System.nanoTime();
        System.out.println("InsertionSort por duracion - Tiempo de ejecucion: " + (end - start) + " ns");
    }

    public void TiempoMergeSortPorDuracion() {
        long start = System.nanoTime();
        MergeSortPorDuracion();
        long end = System.nanoTime();
        System.out.println("MergeSort por duracion - Tiempo de ejecucion: " + (end - start) + " ns");
    }

    public void TiempoQuickSortPorDuracion() {
        long start = System.nanoTime();
        QuickSortPorDuracion();
        long end = System.nanoTime();
        System.out.println("QuickSort por duracion - Tiempo de ejecucion: " + (end - start) + " ns");
    }
    
    // ===== TIEMPO DE EJECUCION POR NOMBRE ===== \\

    public void TiempoBubbleSortPorNombre() {
        long start = System.nanoTime();
        BubbleSortPorNombre();
        long end = System.nanoTime();
        System.out.println("BubbleSort por nombre - Tiempo de ejecucion: " + (end - start) + " ns");
    }

    public void TiempoInsertionSortPorNombre() {
        long start = System.nanoTime();
        InsertionSortPorNombre();
        long end = System.nanoTime();
        System.out.println("InsertionSort por nombre - Tiempo de ejecucion: " + (end - start) + " ns");
    }

    public void TiempoMergeSortPorNombre() {
        long start = System.nanoTime();
        MergeSortPorNombre();
        long end = System.nanoTime();
        System.out.println("MergeSort por nombre - Tiempo de ejecucion: " + (end - start) + " ns");
    }

    public void TiempoQuickSortPorNombre() {
        long start = System.nanoTime();
        QuickSortPorNombre();
        long end = System.nanoTime();
        System.out.println("QuickSort por nombre - Tiempo de ejecucion: " + (end - start) + " ns");
    }

    // ********METODOS DE ORDENAMIENTO POR NOMBRE******** \\

    public void BubbleSortPorNombre() {
        int n = listaDeCanciones.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (listaDeCanciones.get(j).nombre.compareTo(listaDeCanciones.get(j + 1).nombre) > 0) {
                    Cancion temp = listaDeCanciones.get(j);
                    listaDeCanciones.set(j, listaDeCanciones.get(j + 1));
                    listaDeCanciones.set(j + 1, temp);
                }
            } 
        }
    }
    
    public void InsertionSortPorNombre() {
        int n = listaDeCanciones.size();
        for (int i = 1; i < n; ++i) {
            Cancion key = listaDeCanciones.get(i);
            int j = i - 1;
    
            while (j >= 0 && listaDeCanciones.get(j).nombre.compareTo(key.nombre) > 0) {
                listaDeCanciones.set(j + 1, listaDeCanciones.get(j));
                j = j - 1;
            }
            listaDeCanciones.set(j + 1, key);
        }
    }
    
    public void MergeSortPorNombre() {
        listaDeCanciones = mergeSortNombre(listaDeCanciones);
    }
    
    private List<Cancion> mergeSortNombre(List<Cancion> lista) {
        if (lista.size() <= 1) {
            return lista;
        }
    
        int mid = lista.size() / 2;
        List<Cancion> left = new ArrayList<>(lista.subList(0, mid));
        List<Cancion> right = new ArrayList<>(lista.subList(mid, lista.size()));
    
        left = mergeSortNombre(left);
        right = mergeSortNombre(right);
    
        return mergeNombre(left, right);
    }
    
    private List<Cancion> mergeNombre(List<Cancion> left, List<Cancion> right) {
        List<Cancion> resultado = new ArrayList<>();
        int i = 0, j = 0;
    
        while (i < left.size() && j < right.size()) {
            if (left.get(i).nombre.compareTo(right.get(j).nombre) <= 0) {
                resultado.add(left.get(i));
                i++;
            } 
            else {
                resultado.add(right.get(j));
                j++;
            }
        }
    
        while (i < left.size()) {
            resultado.add(left.get(i));
            i++;
        }
    
        while (j < right.size()) {
            resultado.add(right.get(j));
            j++;
        }

        return resultado;
    }
    
    public void QuickSortPorNombre() {
        quickSortNombre(listaDeCanciones, 0, listaDeCanciones.size() - 1);
    }
    
    private void quickSortNombre(List<Cancion> lista, int low, int high) {
        if (low < high) {
            int aux1 = partitionNombre(lista, low, high);
            quickSortNombre(lista, low, aux1 - 1);
            quickSortNombre(lista, aux1 + 1, high);
        }
    }
    
    private int partitionNombre(List<Cancion> lista, int low, int high) {
        Cancion aux2 = lista.get(high);
        int i = (low - 1); 
    
        for (int j = low; j < high; j++) {
            if (lista.get(j).nombre.compareTo(aux2.nombre) <= 0) {
                i++;
                Cancion temp = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, temp);
            }
        }
    
        Cancion temp = lista.get(i + 1);
        lista.set(i + 1, lista.get(high));
        lista.set(high, temp);
    
        return i + 1;
    }

    //********METODOS DE ORDENAMIENTO POR DURACION*********\\

    public void BubbleSortPorDuracion() {
        int n = listaDeCanciones.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (listaDeCanciones.get(j).duracion > listaDeCanciones.get(j + 1).duracion) {
                    Cancion temp = listaDeCanciones.get(j);
                    listaDeCanciones.set(j, listaDeCanciones.get(j + 1));
                    listaDeCanciones.set(j + 1, temp);
                }
            }
        }
    }

    public void InsertionSortPorDuracion() {
        int n = listaDeCanciones.size();
        for (int i = 1; i < n; ++i) {
            Cancion key = listaDeCanciones.get(i);
            int j = i - 1;

            while (j >= 0 && listaDeCanciones.get(j).duracion > key.duracion) {
                listaDeCanciones.set(j + 1, listaDeCanciones.get(j));
                j = j - 1;
            }
            listaDeCanciones.set(j + 1, key);
        }
    }

    public void MergeSortPorDuracion() {
        listaDeCanciones = mergeSortDuracion(listaDeCanciones);
    }

    private List<Cancion> mergeSortDuracion(List<Cancion> lista) {
        if (lista.size() <= 1) {
            return lista;
        }

        int mid = lista.size() / 2;
        List<Cancion> left = new ArrayList<>(lista.subList(0, mid));
        List<Cancion> right = new ArrayList<>(lista.subList(mid, lista.size()));

        left = mergeSortDuracion(left);
        right = mergeSortDuracion(right);

        return mergeDuracion(left, right);
    }

    private List<Cancion> mergeDuracion(List<Cancion> left, List<Cancion> right) {
        List<Cancion> resultado = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).duracion <= right.get(j).duracion) {
                resultado.add(left.get(i));
                i++;
            } else {
                resultado.add(right.get(j));
                j++;
            }
        }

        while (i < left.size()) {
            resultado.add(left.get(i));
            i++;
        }

        while (j < right.size()) {
            resultado.add(right.get(j));
            j++;
        }

        return resultado;
    }

    public void QuickSortPorDuracion() {
        quickSortDuracion(0, listaDeCanciones.size() - 1);
    }
    
    private void quickSortDuracion(int low, int high) {
        if (low < high) {
            int aux1 = partitionDuracion(listaDeCanciones, low, high);
            quickSortDuracion(low, aux1 - 1);
            quickSortDuracion(aux1 + 1, high);
        }
    }
    
    private int partitionDuracion(List<Cancion> lista, int low, int high) {
        Cancion aux2 = lista.get(high);
        int i = (low - 1);
    
        for (int j = low; j < high; j++) {
            if (lista.get(j).duracion <= aux2.duracion) {
                i++;
                Cancion temp = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, temp);
            }
        }
    
        Cancion temp = lista.get(i + 1);
        lista.set(i + 1, lista.get(high));
        lista.set(high, temp);
    
        return i + 1;
    }
    
}

public class Lab3 {

    public static void pr(String nombre, String artista, double duracion) 
    {
        System.out.printf("Nombre: %s, Artista: %s, Duración: %.2f%n", nombre, artista, duracion);
    }
    public static void main(String[] args) {
        String ruta = "C:\\Users\\alvar\\Desktop\\songs.csv";
        String line;
        BaseDeDatos baseDeDatos = new BaseDeDatos();
        int CountID = 1;
    
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) {
                    first = false;
                    continue;
                }
                String[] data = line.split("\\|");
                String nombre = data[0].trim();
                String artista = data[1].trim();
                double duracion = Double.parseDouble(data[2].trim());
    
                pr(nombre, artista, duracion);
                baseDeDatos.agregarCancion(new Cancion(CountID++, nombre, artista, duracion));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        baseDeDatos.crearArbol();

        System.out.println();
        System.out.println("=== TIEMPOS DE EJECUCION ===");
        System.out.println();

        if (!baseDeDatos.obtenerTodasLasCanciones().isEmpty()) {
            
            System.out.println("- Por Nombre: ");
            baseDeDatos.TiempoQuickSortPorNombre();
            baseDeDatos.TiempoBubbleSortPorNombre();
            baseDeDatos.TiempoInsertionSortPorNombre();
            baseDeDatos.TiempoMergeSortPorNombre();
            
            System.out.println();
            System.out.println("- Por Duracion: ");
            baseDeDatos.TiempoQuickSortPorDuracion();
            baseDeDatos.TiempoBubbleSortPorDuracion();
            baseDeDatos.TiempoInsertionSortPorDuracion();
            baseDeDatos.TiempoMergeSortPorDuracion();
    
        } else {
            System.out.println("La Base De Datos está vacia.");
        }

        System.out.println();
        System.out.println("=== BUSCAR CANCIONES ===");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre de la canción a buscar: ");
        String nombreCancionABuscar = scanner.nextLine();

        int idEncontrado = baseDeDatos.buscarCancion(nombreCancionABuscar);

        if (idEncontrado != -1) {
            System.out.println("Se encontró la canción con nombre '" + nombreCancionABuscar + "', ID: " + idEncontrado);
        } else {
            System.out.println("No se encontró ninguna canción con nombre '" + nombreCancionABuscar + "'");
        }

        System.out.println();

        // Solicitar al usuario que ingrese la duración de la canción a buscar
        System.out.print("Ingrese la duración de la canción a buscar (en milisegundos): ");
        double duracionCancionABuscar = scanner.nextDouble();
        scanner.close();

        List<Integer> idsEncontrados = baseDeDatos.buscarCancionesPorDuracion(duracionCancionABuscar);

        if (!idsEncontrados.isEmpty()) {
            System.out.println("Se encontraron las canciones con duración " + duracionCancionABuscar + " ms, IDs: " + idsEncontrados);
        } else {
            System.out.println("No se encontró ninguna canción con duración " + duracionCancionABuscar + " ms");
        }
    }
}
    
