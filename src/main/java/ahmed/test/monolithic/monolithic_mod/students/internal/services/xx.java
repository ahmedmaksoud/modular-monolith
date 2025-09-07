package ahmed.test.monolithic.monolithic_mod.students.internal.services;

import org.springframework.data.annotation.Id;

public class xx {
    record Student(@Id int studentId, String firstName, String lastName) {
    }
}