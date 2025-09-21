package ahmed.test.monolithic.monolithic_mod.integeration;

import ahmed.test.monolithic.monolithic_mod.students.internal.infrastructure.db.StudentEntity;
import ahmed.test.monolithic.monolithic_mod.students.internal.infrastructure.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RealDbUserRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void shouldWorkWithRealDatabase() {
        Optional<StudentEntity>  std = studentRepository.findByStudentId(60);


        assertNotNull (std.get());
    }
}