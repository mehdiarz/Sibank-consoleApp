import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessListResponse {
    public List<ProcessModel> data;
    public int total;
    public int totalPage;
    public int start;
    public int size;
    public int pageNumber;
}
