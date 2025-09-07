package ahmed.test.monolithic.monolithic_mod.students.internal.domain.contracts;

import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.Student;
import ahmed.test.monolithic.monolithic_mod.students.internal.domain.model.StudentId;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository  {


//    @Query("select first_Name, last_Name from Student")
//    public List<StudentService.Student2> getStudentRepository();
//
//    @Query("select first_Name, last_Name from Student")
//    public List<StudentService.Student2> getStudentRepository();


    Optional<Student> findByStudentId(StudentId studentId);

    List<Student> findByFirstNameAndLastName(String firstName, String lastName);

    List<Student>  findByLastName(String lastName);

    List<Student>  findByFirstName(String firstName);

    Integer getNextStudentId();

    Student saveStudent(Student student);




}