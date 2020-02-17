package dataviz.process;

public class ProcessManager {
    private static ProcessManager instance;

    private ProcessManager() {
    }

    public static synchronized ProcessManager getInstance() {
        if (instance == null) {
            instance = new ProcessManager();
        }
        return instance;
    }
}