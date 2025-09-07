package ahmed.test.monolithic.monolithic_mod.students.internal.domain.model;

import ahmed.test.monolithic.monolithic_mod.shared.domain.model.ValueObject;

public record StudentId (Integer value)  implements ValueObject {

    public StudentId {
        if (value <= 0) {
            throw new IllegalArgumentException("Student id must be greater than 0");
        }
    }

    public static void main(String[] args) {
        StudentId c1 = new StudentId(Integer.valueOf(10));
        StudentId c2 = new StudentId(Integer.valueOf(10));
        System.out.println(c1.equals(c2));

    }
}
