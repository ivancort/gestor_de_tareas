import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TaskManager {

    // Clase interna para representar una tarea
    static class Task {
        String description;
        boolean isImportant;
        boolean isUrgent;

        Task(String description, boolean isImportant, boolean isUrgent) {
            this.description = description;
            this.isImportant = isImportant;
            this.isUrgent = isUrgent;
        }
    }

    private List<Task> tasks = new ArrayList<>();

    // Método para añadir una tarea
    public void addTask(String description, boolean isImportant, boolean isUrgent) {
        tasks.add(new Task(description, isImportant, isUrgent));
    }

    // Método para categorizar tareas según la Matriz de Eisenhower
    public void categorizeTasks() {
        List<Task> quadrant1 = new ArrayList<>(); // Importante y Urgente
        List<Task> quadrant2 = new ArrayList<>(); // Importante pero No Urgente
        List<Task> quadrant3 = new ArrayList<>(); // No Importante pero Urgente
        List<Task> quadrant4 = new ArrayList<>(); // No Importante y No Urgente

        for (Task task : tasks) {
            if (task.isImportant && task.isUrgent) {
                quadrant1.add(task);
            } else if (task.isImportant) {
                quadrant2.add(task);
            } else if (task.isUrgent) {
                quadrant3.add(task);
            } else {
                quadrant4.add(task);
            }
        }

        // Mostrar tareas por categorías
        System.out.println("\nDo First (Urgente e Importante):");
        quadrant1.forEach(task -> System.out.println(" - " + task.description));

        System.out.println("\nSchedule (Importante pero No Urgente):");
        quadrant2.forEach(task -> System.out.println(" - " + task.description));

        System.out.println("\nDelegate (Urgente pero No Importante):");
        quadrant3.forEach(task -> System.out.println(" - " + task.description));

        System.out.println("\nDon't Do (No Urgente ni Importante):");
        quadrant4.forEach(task -> System.out.println(" - " + task.description));
    }

    // Método para enviar recordatorios de tareas urgentes
    public void scheduleReminders() {
        Timer timer = new Timer();
        for (Task task : tasks) {
            if (task.isUrgent) {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("¡Recordatorio! La tarea '" + task.description + "' es urgente y debe ser completada pronto.");
                    }
                }, 0, 10000); // Recordatorio cada 10 segundos (ajustable)
            }
        }
    }

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Añadir algunas tareas de prueba
        manager.addTask("Preparar propuesta de negocio", true, true);
        manager.addTask("Revisar correos electrónicos", false, true);
        manager.addTask("Planificar estrategia de marketing", true, false);
        manager.addTask("Ver una serie en TV", false, false);

        // Mostrar tareas categorizadas
        manager.categorizeTasks();

        // Programar recordatorios
        manager.scheduleReminders();
    }
}

