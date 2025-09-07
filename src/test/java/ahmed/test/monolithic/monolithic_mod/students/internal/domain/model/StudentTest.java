package ahmed.test.monolithic.monolithic_mod.students.internal.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void addStudendSubjects() {

    }

    @Test
    void registerStudent() {
       Student student =  Student.registerStudent(new StudentProp(
                100,
                "Ahmed", "Mak", null
        ));
       assert(student != null);
       assert (student instanceof Student);
       assert ( student.getStudentId().value().equals(100));

    }

    @Test
    void studentEquals() {
        Student studentOne =  Student.registerStudent(new StudentProp(
                100,
                "Ahmed", "Mak", null
        ));

        Student studenTwo =  Student.registerStudent(new StudentProp(
                100,
                "Ahmed", "Mak", null
        ));


        assert ( studentOne.equals(studenTwo));

    }

    @Test
    void create() {
    }
}