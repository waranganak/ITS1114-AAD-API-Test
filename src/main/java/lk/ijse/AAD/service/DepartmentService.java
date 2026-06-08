package lk.ijse.AAD.service;

import lk.ijse.AAD.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);
    List<DepartmentDTO> getDepartments();
    DepartmentDTO getDepartmentDetails(long id);
    DepartmentDTO updateDepartment(DepartmentDTO departmentDTO);

    void deleteDepartment(long id);
}
