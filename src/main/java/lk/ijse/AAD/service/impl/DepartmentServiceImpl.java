package lk.ijse.AAD.service.impl;

import lk.ijse.AAD.dto.DepartmentDTO;
import lk.ijse.AAD.entity.Department;
import lk.ijse.AAD.repository.DepartmentRepository;
import lk.ijse.AAD.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {

        try{
            log.info("Attempting to save a new department to the database");
            Department department = new Department();
            department.setDepartmentName(departmentDTO.getDepartmentName());
            department.setDepartmentLocation(departmentDTO.getDepartmentLocation());

            Department savedDepartment = departmentRepository.save(department);
            log.info("Department saved with ID: {}", savedDepartment.getDepartmentId());


            return departmentDTO;
        } catch (Exception e) {
            log.error("Error occurred while saving department: {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<DepartmentDTO> getDepartments() {

        try {
            log.info("Fetching all departments from the database");
            List<Department> departments = departmentRepository.findAll();
            log.info("Total departments fetched: {}", departments.size());

            return departments.stream().map(
                    department -> {
                        DepartmentDTO departmentDTO = new DepartmentDTO();
                        departmentDTO.setDepartmentName(department.getDepartmentName());
                        departmentDTO.setDepartmentLocation(department.getDepartmentLocation());
                        return departmentDTO;
                    }
            ).toList();

        } catch (Exception e) {
            log.error("Error occurred while fetching departments: {}", e.getMessage());
            throw new RuntimeException(e);
        }


    }

    @Override
    public DepartmentDTO getDepartmentDetails(long id) {
        log.info("Filtering department with ID: {}", id);
        try {
            Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found with ID: " + id));
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setDepartmentName(department.getDepartmentName());
            departmentDTO.setDepartmentLocation(department.getDepartmentLocation());
            return departmentDTO;
        } catch (Exception e) {
            log.error("Error occurred while fetching department details: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) {
        log.info("Attempting to update department with ID: {}", departmentDTO.getDepartmentId());
        try {
            Department department = departmentRepository.findById(departmentDTO.getDepartmentId()).orElseThrow(() -> new RuntimeException("Department not found with ID: " + departmentDTO.getDepartmentId()));
            department.setDepartmentName(departmentDTO.getDepartmentName());
            department.setDepartmentLocation(departmentDTO.getDepartmentLocation());

            Department updatedDepartment = departmentRepository.save(department);
            log.info("Department updated with ID: {}", updatedDepartment.getDepartmentId());

            DepartmentDTO updatedDepartmentDTO = new DepartmentDTO();
            updatedDepartmentDTO.setDepartmentId(updatedDepartment.getDepartmentId());
            updatedDepartmentDTO.setDepartmentName(updatedDepartment.getDepartmentName());
            updatedDepartmentDTO.setDepartmentLocation(updatedDepartment.getDepartmentLocation());

            return departmentDTO;
        } catch (Exception e) {
            log.error("Error occurred while updating department: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteDepartment(long id) {
        log.info("Attempting to delete department with ID: {}", id);
        try {
            Optional<Department> optionalDepartment = departmentRepository.findById(id);
            if (!optionalDepartment.isPresent()) {
                throw new RuntimeException("Department not found with ID: " + id);
            }
            Department department = optionalDepartment.get();

        } catch (Exception e) {
            log.error("Error occurred while deleting department: {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }



}
