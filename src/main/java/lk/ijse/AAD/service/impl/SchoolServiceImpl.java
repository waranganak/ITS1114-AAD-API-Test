package lk.ijse.AAD.service.impl;

import lk.ijse.AAD.dto.SchoolDTO;
import lk.ijse.AAD.dto.StudentDTO;
import lk.ijse.AAD.entity.School;
import lk.ijse.AAD.repository.SchoolRepository;
import lk.ijse.AAD.service.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SchoolServiceImpl implements SchoolService {

    SchoolRepository schoolRepository;
    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public SchoolDTO saveSchool(SchoolDTO schoolDTO) {
        try {
            log.info("Attempting to save a new school to the database");
            School school = new School();
            school.setLocation(schoolDTO.getLocation());
            school.setName(schoolDTO.getName());

            School savedSchool = schoolRepository.save(school);
            log.info("School saved with ID: {}", savedSchool.getId());

            SchoolDTO savedSchoolDTO = new SchoolDTO();
            savedSchoolDTO.setId(savedSchool.getId());
            savedSchoolDTO.setName(savedSchool.getName());
            savedSchoolDTO.setLocation(savedSchool.getLocation());

            return savedSchoolDTO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SchoolDTO> getAllSchools() {
        try {
            log.info("Fetching all schools from the database");
            List<School> schools = schoolRepository.findAll();
            log.info("Total schools fetched: {}", schools.size());

            return schools.stream().map(
                    school -> {
                        SchoolDTO schoolDTO = new SchoolDTO();
                        schoolDTO.setId(school.getId());
                        schoolDTO.setName(school.getName());
                        schoolDTO.setLocation(school.getLocation());
                        return schoolDTO;
                    }
            ).toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SchoolDTO getSchoolDetails(long id) {
        try {
            log.info("Fetching school details for ID: {}", id);
            Optional<School> optionalSchool = schoolRepository.findById(id);
            if (!optionalSchool.isPresent()) {
                throw new RuntimeException("School not found with ID: " + id);
            }
            log.info("School found: {}", optionalSchool);
            School school = optionalSchool.get();
            SchoolDTO schoolDTO = new SchoolDTO();
            schoolDTO.setId(school.getId());
            schoolDTO.setName(school.getName());
            schoolDTO.setLocation(school.getLocation());

            return schoolDTO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SchoolDTO updateSchool(SchoolDTO schoolDTO) {
        try {
            log.info("Attempting to update school with ID: {}", schoolDTO.getId());
            Optional<School> optionalSchool = schoolRepository.findById(schoolDTO.getId());
            if (!optionalSchool.isPresent()) {
                throw new RuntimeException("School not found with ID: " + schoolDTO.getId());
            }

            School school = optionalSchool.get();
            school.setName(schoolDTO.getName());
            school.setLocation(schoolDTO.getLocation());

            School updatedSchool = schoolRepository.save(school);
            log.info("School updated with ID: {}", updatedSchool.getId());

            SchoolDTO updatedSchoolDTO = new SchoolDTO();
            updatedSchoolDTO.setId(updatedSchool.getId());
            updatedSchoolDTO.setName(updatedSchool.getName());
            updatedSchoolDTO.setLocation(updatedSchool.getLocation());

            return updatedSchoolDTO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
