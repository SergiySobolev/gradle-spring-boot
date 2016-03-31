package presentation.rest.resources;

public class MyMessage {

    private String message;
    private int id;
    private String user;

    public MyMessage() {

    }

    public MyMessage(int id, String message, String user) {
        this.id = id;
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
