package ahmed.test.monolithic.monolithic_mod.students.internal.domain.model;

public enum StudentStatus {
    REGISTERED(1),
    ACTIVE(2),
    GRADUATED(3),
    SUSPENDED(4);

    private final int code;

    StudentStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static StudentStatus fromCode(int code) {
        for (StudentStatus status : StudentStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }

    public boolean canTransitionTo(StudentStatus target) {
        return switch (this) {
            case REGISTERED, SUSPENDED -> target == ACTIVE;
            case ACTIVE     -> (target == GRADUATED || target == SUSPENDED);
            case GRADUATED -> false; // terminal
        };
    }
}
