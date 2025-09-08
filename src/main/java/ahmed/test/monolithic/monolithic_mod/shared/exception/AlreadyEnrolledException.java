package ahmed.test.monolithic.monolithic_mod.shared.exception;

public class AlreadyEnrolledException extends RuntimeException {
    public AlreadyEnrolledException(String description) {
        super("Not Found: " + (description != null ? description : ""));

    }
}
