package ahmed.test.monolithic.monolithic_mod.students.internal.domain.model;

import ahmed.test.monolithic.monolithic_mod.shared.domain.model.ValueObject;

public record StudentSubjectsId(Integer value) implements ValueObject {



    public StudentSubjectsId(Integer value) {
        if (value <= 0) {
            throw new IllegalArgumentException("subjectId id must be greater than 0");
        }
        this.value = value;
    }
}
