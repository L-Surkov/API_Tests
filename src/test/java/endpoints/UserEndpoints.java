package endpoints;

public enum UserEndpoints {

    LIST_USERS("/users"),
    SINGLE_USER("/users/{id}"),
    CREATE_USER("/users"),
    REGISTER_USER("/register"),
    DELETE_USER("/users/{id}");

    private final String endpoint;

    UserEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
