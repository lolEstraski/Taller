package org.example;
import java.util.*;

public class Grafo26 {
    private Map<Municipio, List<Arista>> adyacencias;

    public Grafo26() {
        this.adyacencias = new HashMap<>();
    }

    public void agregarMunicipio(Municipio municipio) {
        adyacencias.put(municipio, new ArrayList<>());
    }

    public void agregarArista(Municipio origen, Municipio destino, double peso) {
        adyacencias.get(origen).add(new Arista(origen, destino, peso));
        adyacencias.get(destino).add(new Arista(destino, origen, peso)); // Grafo dirigido
    }

    public List<Arista> obtenerAdyacencias(Municipio municipio) {
        return adyacencias.get(municipio);
    }

    class MST {
        public Set<Arista> prim(Grafo26 grafo, Municipio nodoInicial) {
            Set<Arista> mst = new HashSet<>();
            PriorityQueue<Arista> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a.peso));
            Set<Municipio> visitados = new HashSet<>();

            // Agregar aristas que parten del nodo inicial a la cola de prioridad
            for (Arista arista : grafo.obtenerAdyacencias(nodoInicial)) {
                pq.add(arista);
            }

            visitados.add(nodoInicial);

            while (!pq.isEmpty()) {
                Arista aristaActual = pq.poll(); // Eliminar la arista con menor peso

                Municipio destino = aristaActual.destino;

                if (!visitados.contains(destino)) {
                    mst.add(aristaActual);
                    visitados.add(destino);

                    for (Arista arista : grafo.obtenerAdyacencias(destino)) {
                        if (!visitados.contains(arista.destino)) {
                            pq.add(arista);
                        }
                    }
                }
            }

            return mst; // Retornar el árbol recubridor mínimo (MST)
        }
    }

    public static void main(String[] args) {
        // Crear el grafo
        Grafo26 grafo = new Grafo26();

        // Agregar los municipios del Quindío
        Municipio armenia = new Municipio(1, "Armenia");
        Municipio calarca = new Municipio(2, "Calarca");
        Municipio circasia = new Municipio(3, "Circasia");
        Municipio cordoba = new Municipio(4, "Córdoba");
        Municipio filandia = new Municipio(5, "Filandia");
        Municipio la_tebaida = new Municipio(6, "La Tebaida");
        Municipio montenegro = new Municipio(7, "Montenegro");
        Municipio pijao = new Municipio(8, "Pijao");
        Municipio salento = new Municipio(9, "Salento");

        grafo.agregarMunicipio(armenia);
        grafo.agregarMunicipio(calarca);
        grafo.agregarMunicipio(circasia);
        grafo.agregarMunicipio(cordoba);
        grafo.agregarMunicipio(filandia);
        grafo.agregarMunicipio(la_tebaida);
        grafo.agregarMunicipio(montenegro);
        grafo.agregarMunicipio(pijao);
        grafo.agregarMunicipio(salento);

        // Agregar las aristas con sus pesos (distancias)
        grafo.agregarArista(armenia, calarca, 10.5);
        grafo.agregarArista(armenia, circasia, 15.2);
        grafo.agregarArista(armenia, montenegro, 22.3);
        grafo.agregarArista(armenia, la_tebaida, 7.8);
        grafo.agregarArista(calarca, cordoba, 12.1);
        grafo.agregarArista(calarca, filandia, 18.4);
        grafo.agregarArista(calarca, la_tebaida, 6.5);
        grafo.agregarArista(calarca, montenegro, 14.7);
        grafo.agregarArista(calarca, pijao, 21.5);
        grafo.agregarArista(calarca, salento, 25.3);
        grafo.agregarArista(circasia, filandia, 11.8);
        grafo.agregarArista(circasia, la_tebaida, 9.2);
        grafo.agregarArista(circasia, montenegro, 17.9);
        grafo.agregarArista(circasia, pijao, 24.7);
        grafo.agregarArista(circasia, salento, 28.5);
        grafo.agregarArista(cordoba, filandia, 8.3);
        grafo.agregarArista(cordoba, la_tebaida, 13.6);
        grafo.agregarArista(cordoba, montenegro, 21.2);
        grafo.agregarArista(cordoba, pijao, 18.0);
        grafo.agregarArista(cordoba, salento, 21.8);
        grafo.agregarArista(filandia, la_tebaida, 5.7);
        grafo.agregarArista(filandia, montenegro, 13.4);
        grafo.agregarArista(filandia, pijao, 20.2);
        grafo.agregarArista(filandia, salento, 16.0);
        grafo.agregarArista(la_tebaida, montenegro, 16.1);
        grafo.agregarArista(la_tebaida, pijao, 12.9);
        grafo.agregarArista(la_tebaida, salento, 19.7);
        grafo.agregarArista(montenegro, pijao, 9.0);

        // Ejecutar el algoritmo MST para encontrar el árbol recubridor mínimo
        Grafo26.MST mst = grafo.new MST();
        Set<Arista> rutaOptima = mst.prim(grafo, armenia);

        // Imprimir la ruta óptima
        System.out.println("Ruta óptima:");
        for (Arista arista : rutaOptima) {
            System.out.println(arista.origen.nombre + " -> " + arista.destino.nombre + ": " + arista.peso + " km");
        }
    }
}

class Municipio {
    int id;
    String nombre;

    public Municipio(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}

class Arista {
    Municipio origen;
    Municipio destino;
    double peso; // Distancia en kilómetros

    public Arista(Municipio origen, Municipio destino, double peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }
}
