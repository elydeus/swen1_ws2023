package at.technikum.server.http;

public enum HttpStatus {
    OK (200, "OK"),
    BAD_REQUEST (400, "Bad Request"),
    NOT_FOUND (404, "Not Found"),
    CREATED (201, "Created"),
    ACCEPTED (202, "Accepted"),
    UNAUTHORIZED (401, "Unauthorized"),
    FORBIDDEN (403, "Forbidden"),
    NOT_ALLOWED (405, "Not Allowed"),
    CONFLICT (409, "Conflict"),
    INTERNAL_SERVER_ERROR (500, "Internal Server Error"),
    INSUFFICIENT_COINS (402, "Payment Required - Insufficient Coins"),
    DECK_INCOMPLETE (422, "Unprocessable Content - Incomplete Deck");
    private final int code;
    private final String message;

    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
