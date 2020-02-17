package dataviz.memory;

public class MemoryManager {
    private static MemoryManager instance;

    private MemoryManager() {
    }

    public static synchronized MemoryManager getInstance() {
        if (instance == null) {
            instance = new MemoryManager();
        }
        return instance;
    }
}