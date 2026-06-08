package lk.ijse.AAD.service;

import lk.ijse.AAD.dto.StudentDTO;
import lk.ijse.AAD.dto.UsersDTO;

import java.util.List;

public interface StudentService {
    StudentDTO saveStudent(StudentDTO studentDTO);

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentDetails(long id);

    StudentDTO updateStudent(StudentDTO studentDTO);


}
