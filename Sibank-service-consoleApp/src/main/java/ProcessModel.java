

public class ProcessModel {
    public String id;
    public String version;

    public ProcessModel() {
    }

    public ProcessModel(String id, String version) {
        this.id = id;
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }
}
