package lk.ijse.AAD.controller;

import lk.ijse.AAD.dto.DepartmentDTO;
import lk.ijse.AAD.dto.UsersDTO;
import lk.ijse.AAD.service.DepartmentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/api/departments")
public class DepartmentController {

    DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveDepartment(@RequestBody DepartmentDTO departmentDTO){
        departmentService.saveDepartment(departmentDTO);

        return "Department save successfully";
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,value = "/all")
    public List<DepartmentDTO> getDepartment() {
        return departmentService.getDepartments();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,value = "/department-details/{id}")
    public DepartmentDTO getDepartmentDetails(@PathVariable long id) {
        return departmentService.getDepartmentDetails(id);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,value = "/update")
    public DepartmentDTO updateDepartment(@RequestBody DepartmentDTO departmentDTO) {
        return departmentService.updateDepartment(departmentDTO);
    }




}
