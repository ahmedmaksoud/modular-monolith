package ahmed.test.monolithic.monolithic_mod.shared.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String description) {
        super("Not Found: " + (description != null ? description : ""));
    }
}