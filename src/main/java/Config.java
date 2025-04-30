import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // <-- Ignore extra unknown fields
public class Config {

    @JsonProperty("Secure_ChannelUserName")
    public String secure_ChannelUserName;

    @JsonProperty("Secure_ChannelPassword")
    public String secure_ChannelPassword;

    @JsonProperty("mobileNumber")
    public String mobileNumber;

    @JsonProperty("initiator")
    public String initiator;

    @JsonProperty("deviceId")
    public String deviceId;

    @JsonProperty("deviceModel")
    public String deviceModel;

    @JsonProperty("clientIp")
    public String clientIp;

    @JsonProperty("SERVER_IP")
    public String server_IP; // <-- JSON is SERVER_IP

    @JsonProperty("PROTOCOL")
    public String protocol; // <-- JSON is PROTOCOL

    @JsonProperty("PORT")
    public String port; // <-- JSON is PORT

    @JsonProperty("WEB_SERVICE_PATH")
    public String web_SERVICE_PATH; // <-- JSON is WEB_SERVICE_PATH

    @JsonProperty("CONNECTION_BRANCH")
    public String connection_BRANCH; // <-- JSON is CONNECTION_BRANCH

    @JsonProperty("SPECIFIC_KEY")
    public String specific_KEY; // <-- JSON is SPECIFIC_KEY

    @JsonProperty("userContext")
    public UserContext userContext; // nested object

}
