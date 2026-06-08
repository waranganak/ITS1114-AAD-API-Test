package lk.ijse.AAD.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long departmentId;
    private String departmentName;
    private String departmentLocation;

    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserDepartment> userDepartments = new ArrayList<>();
}
