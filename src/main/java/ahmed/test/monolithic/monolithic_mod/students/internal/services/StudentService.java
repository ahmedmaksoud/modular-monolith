package ahmed.test.monolithic.monolithic_mod.students.internal.services;

import ahmed.test.monolithic.monolithic_mod.students.internal.domain.contracts.IStudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    private IStudentRepository iStudentRepository;

    public StudentService(IStudentRepository iStudentRepository) {
        this.iStudentRepository = iStudentRepository;
    }


    @Transactional
    public void updateStudent(int studentId,String firstName,  String lastName) {


    }



}
