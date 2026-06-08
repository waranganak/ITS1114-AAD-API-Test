package lk.ijse.AAD.controller;

import lk.ijse.AAD.dto.SchoolDTO;
import lk.ijse.AAD.service.SchoolService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/api/school")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public SchoolDTO saveSchool(@RequestBody SchoolDTO schoolDTO) {
        return schoolService.saveSchool(schoolDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/school-details/{id}")
    public SchoolDTO getSchoolDetails(@PathVariable long id) {
        return schoolService.getSchoolDetails(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/all")
    public List<SchoolDTO> getSchools() {
        return schoolService.getAllSchools();
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/update")
    public SchoolDTO updateSchool(@RequestBody SchoolDTO schoolDTO) {
        return schoolService.updateSchool(schoolDTO);
    }
}
