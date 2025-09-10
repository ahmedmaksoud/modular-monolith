package ahmed.test.monolithic.monolithic_mod.students.internal.infrastructure.repositories;



import ahmed.test.monolithic.monolithic_mod.students.internal.infrastructure.db.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StudentRepository extends ListCrudRepository<StudentEntity, Integer> {


//    @Query("select first_Name, last_Name from Student")
//    public List<StudentService.Student2> getStudentRepository();
//
//    @Query("select first_Name, last_Name from Student")
//    public List<StudentService.Student2> getStudentRepository();


    Optional<StudentEntity> findByStudentId(Integer studentId);

    List<StudentEntity> findByFirstNameAndLastName(String firstName, String lastName);

    List<StudentEntity>  findByLastName(String lastName);

    List<StudentEntity>  findByFirstName(String firstName);


    @Query(value = "SELECT nextval('student_id_seq')", nativeQuery = true)
    public Integer getNextStudentId();


    List<StudentEntity> StudentId(Integer studentId);
}
