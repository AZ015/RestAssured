package config;

public final class Endpoints {

    public static final String GET_USERS_BY_PAGE = "users";
    public static final String GET_SINGLE_USER_BY_ID = "users/{userId}";
    public static final String GET_SINGLE_USER_NOT_FOUND_BY_ID= "users/{userId}";
    public static final String GET_LIST_OF_USERS_INFO = "unknown";
    public static final String GET_USER_INFO_BY_ID = "unknown/{userId}";
    public static final String CREATE_USER = "users";
    public static final String DELETE_USER_BY_ID = "users/{userId}";
    public static final String LOGIN = "login";
    public static final String REGISTER= "register";
    public static final String OAUTH_1_TEST = "http://term.ie/oauth/example/request_token.php";

}
