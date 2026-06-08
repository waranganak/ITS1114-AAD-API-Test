package lk.ijse.AAD.service.impl;

import lk.ijse.AAD.dto.StudentDTO;
import lk.ijse.AAD.entity.School;
import lk.ijse.AAD.entity.Student;
import lk.ijse.AAD.repository.SchoolRepository;
import lk.ijse.AAD.repository.StudentRepository;
import lk.ijse.AAD.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;
    SchoolRepository schoolRepository;


        public StudentServiceImpl(StudentRepository studentRepository, SchoolRepository schoolRepository) {

            this.studentRepository = studentRepository;
            this.schoolRepository = schoolRepository;
        }

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        log.info("Saving student: {}", studentDTO);
        try {
            Optional<School> optionalSchool = schoolRepository.findById(studentDTO.getSchoolId());
            if (!optionalSchool.isPresent()) {
                throw new RuntimeException("School not found with ID: " + studentDTO.getSchoolId());
            }

            Student student = new Student();
            student.setFName(studentDTO.getFName());
            student.setLName(studentDTO.getLName());
            student.setDob(studentDTO.getDob());
            student.setAddress(studentDTO.getAddress());

            School school = optionalSchool.get();

            student.setSchool(school);

            Student savedStudent = studentRepository.save(student);

            StudentDTO savedStudentDTO = new StudentDTO();
            savedStudentDTO.setId(savedStudent.getId());
            savedStudentDTO.setFName(savedStudent.getFName());
            savedStudentDTO.setLName(savedStudent.getLName());
            savedStudentDTO.setDob(savedStudent.getDob());
            savedStudentDTO.setAddress(savedStudent.getAddress());

            savedStudentDTO.setSchoolId(savedStudent.getSchool().getId());

            log.info("Student saved successfully: {}", savedStudentDTO);
            return savedStudentDTO;
        } catch (Exception e) {
            log.error("Error occurred while saving student: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        log.info("Fetching all students from the database");
        try {
            List<Student> students = studentRepository.findAll();
            log.info("Total students fetched: {}", students.size());

            return students.stream().map(student -> {
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setId(student.getId());
                studentDTO.setFName(student.getFName());
                studentDTO.setLName(student.getLName());
                studentDTO.setDob(student.getDob());
                studentDTO.setAddress(student.getAddress());
                return studentDTO;
            }).toList();
        } catch (Exception e) {
            log.error("Error occurred while fetching students: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public StudentDTO getStudentDetails(long id) {
        log.info("Fetching student details for ID: {}", id);
        try {
            Optional<Student> optionalStudent = studentRepository.findById(id);
            if (!optionalStudent.isPresent()) {
                throw new RuntimeException("Student not found with ID: " + id);
            }
            Student student = optionalStudent.get();
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(student.getId());
            studentDTO.setFName(student.getFName());
            studentDTO.setLName(student.getLName());
            studentDTO.setDob(student.getDob());
            studentDTO.setAddress(student.getAddress());

            log.info("Student details fetched successfully: {}", studentDTO);
            return studentDTO;
        } catch (Exception e) {
            log.error("Error occurred while fetching student details: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        log.info("Updating student: {}", studentDTO);
        try {
            Optional<Student> optionalStudent = studentRepository.findById(studentDTO.getId());
            if (!optionalStudent.isPresent()) {
                throw new RuntimeException("Student not found with ID: " + studentDTO.getId());
            }

            Student student = optionalStudent.get();
            student.setFName(studentDTO.getFName());
            student.setLName(studentDTO.getLName());
            student.setDob(studentDTO.getDob());
            student.setAddress(studentDTO.getAddress());

            Student updatedStudent = studentRepository.save(student);

            StudentDTO updatedStudentDTO = new StudentDTO();
            updatedStudentDTO.setId(updatedStudent.getId());
            updatedStudentDTO.setFName(updatedStudent.getFName());
            updatedStudentDTO.setLName(updatedStudent.getLName());
            updatedStudentDTO.setDob(updatedStudent.getDob());
            updatedStudentDTO.setAddress(updatedStudent.getAddress());

            log.info("Student updated successfully: {}", updatedStudentDTO);
            return updatedStudentDTO;
        } catch (Exception e) {
            log.error("Error occurred while updating student: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
