package dataviz.locking;

public class LockingManager {
    private static LockingManager instance;

    private LockingManager() {
    }

    public static LockingManager getInstance() {
        if (instance == null) {
            instance = new LockingManager();
        }
        return instance;
    }
}