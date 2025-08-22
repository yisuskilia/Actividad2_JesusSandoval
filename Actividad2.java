import java.util.*;

// Clase Nodo
class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

// Lista ligada genérica
class LinkedList<T> {
    protected Node<T> head;
    protected Node<T> tail;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    // Insertar al final
    public void insertLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Insertar al inicio (para pila)
    public void insertFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    // Eliminar primero
    public T removeFirst() {
        if (head == null) return null;
        T data = head.data;
        head = head.next;
        if (head != null) head.prev = null;
        else tail = null;
        return data;
    }

    // Eliminar último
    public T removeLast() {
        if (tail == null) return null;
        T data = tail.data;
        tail = tail.prev;
        if (tail != null) tail.next = null;
        else head = null;
        return data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // Ver primero
    public T peekFirst() {
        return (head != null) ? head.data : null;
    }

    // Ver último
    public T peekLast() {
        return (tail != null) ? tail.data : null;
    }

    // Mostrar elementos
    public void display() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}

// Implementación de Pila con lista ligada
class Pila<T> {
    private LinkedList<T> lista;

    public Pila() {
        lista = new LinkedList<>();
    }

    public void push(T data) {
        lista.insertFirst(data);
    }

    public T pop() {
        return lista.removeFirst();
    }

    public T peek() {
        return lista.peekFirst();
    }

    public boolean isEmpty() {
        return lista.isEmpty();
    }

    public void display() {
        System.out.print("Historial de comandos: ");
        lista.display();
    }
}

// Implementación de Cola con lista ligada
class Cola<T> {
    private LinkedList<T> lista;

    public Cola() {
        lista = new LinkedList<>();
    }

    public void enqueue(T data) {
        lista.insertLast(data);
    }

    public T dequeue() {
        return lista.removeFirst();
    }

    public T peek() {
        return lista.peekFirst();
    }

    public boolean isEmpty() {
        return lista.isEmpty();
    }

    public void display() {
        System.out.print("Programas en ejecución: ");
        lista.display();
    }
}

// Clase Main simulando sistema operativo
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pila<String> historial = new Pila<>();
        Cola<String> programas = new Cola<>();

        while (true) {
            System.out.println("\n--- Spotify ---");
            System.out.println("1. Ejecutar comando (Push en historial)");
            System.out.println("2. Deshacer último comando (Pop de historial)");
            System.out.println("3. Ver último comando ejecutado (Peek historial)");
            System.out.println("4. Mostrar historial de comandos");
            System.out.println("5. Iniciar programa (Enqueue en cola)");
            System.out.println("6. Finalizar programa (Dequeue de cola)");
            System.out.println("7. Ver programa en ejecución (Peek cola)");
            System.out.println("8. Mostrar programas en ejecución");
            System.out.println("9. Salir");
            System.out.print("Opción: ");
            int op = sc.nextInt(); sc.nextLine();

            switch(op) {
                case 1 -> {
                    System.out.print("Ingrese comando: ");
                    String cmd = sc.nextLine();
                    historial.push(cmd);
                    System.out.println("Comando ejecutado: " + cmd);
                }
                case 2 -> {
                    String undone = historial.pop();
                    if (undone != null)
                        System.out.println("Se deshizo el comando: " + undone);
                    else
                        System.out.println("Historial vacío.");
                }
                case 3 -> {
                    System.out.println("Último comando: " + historial.peek());
                }
                case 4 -> historial.display();
                case 5 -> {
                    System.out.print("Ingrese programa: ");
                    String prog = sc.nextLine();
                    programas.enqueue(prog);
                    System.out.println("Programa iniciado: " + prog);
                }
                case 6 -> {
                    String finished = programas.dequeue();
                    if (finished != null)
                        System.out.println("Programa finalizado: " + finished);
                    else
                        System.out.println("No hay programas en ejecución.");
                }
                case 7 -> {
                    System.out.println("Programa en ejecución: " + programas.peek());
                }
                case 8 -> programas.display();
                case 9 -> {
                    System.out.println("Saliendo del sistema...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}
