package lk.ijse.AAD.service;

import lk.ijse.AAD.dto.SchoolDTO;
import lk.ijse.AAD.dto.StudentDTO;

import java.util.List;

public interface SchoolService {
    SchoolDTO saveSchool(SchoolDTO schoolDTO);

    List<SchoolDTO> getAllSchools();

    SchoolDTO getSchoolDetails(long id);

    SchoolDTO updateSchool(SchoolDTO schoolDTO);
}
