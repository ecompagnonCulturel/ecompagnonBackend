package uqtr.ecompagnon.dto;

public class FcmRequestDTO {
    private String title;
    private String message;
    private String topic;
    private String token;
    private String  color="#FF0000";
    private Integer nbreQuest;
    private String types;
    private Integer userId;


    public FcmRequestDTO() {
        super();
    }




    public FcmRequestDTO(String title, String message, String topic, String token, Integer nbreQuest, String types, Integer userId) {
        super();

        this.title = title;
        this.message = message;
        this.topic = topic;
        this.token = token;
        this.color="#FF0000";
        this.nbreQuest=nbreQuest;
        this.types=types;
        this.userId=userId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMessage() {
        return message;
    }
    public String getColor() {return color;}
    public void setMessage(String message) {
        this.message = message;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Integer getNbreQuest() {return nbreQuest;}
    public String getTypes() {return types;}
    public Integer getUserId() {return userId;}
    public void setColor(String color) {this.color = color;}
    public void setNbreQuest(Integer nbreQuest) {this.nbreQuest = nbreQuest;}
    public void setTypes(String types) {this.types = types;}
    public void setUserId(Integer userId) {this.userId = userId;}
}
