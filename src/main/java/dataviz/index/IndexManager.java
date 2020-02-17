package dataviz.index;

public class IndexManager {
    private static IndexManager instance;

    private IndexManager() {
    }

    public static synchronized IndexManager getInstance() {
        if (instance == null) {
            instance = new IndexManager();
        }
        return instance;
    }
}