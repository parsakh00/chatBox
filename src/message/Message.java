package message;

import com.google.gson.Gson;

public class Message {

    private String name;
    private final String authToken;

    public Message(String name, String authToken) {
        this.name = name;
        this.authToken = authToken;
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Message jsonToMessage(String json){
        Gson gson1 = new Gson();
        return gson1.fromJson(json, Message.class);
    }

    public String getName() {
        return name;
    }

    public String getAuthToken() {
        return authToken;
    }
}
