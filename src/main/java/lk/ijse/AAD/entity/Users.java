package lk.ijse.AAD.entity;

import jakarta.persistence.*;
import lk.ijse.AAD.enumaration.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data // gives setter, getter, toString, equals and hashcode methods
@Entity // this annotation is used to mark this class as an entity class which will be mapped to a table in the database
@Table(name = "users")
public class Users {

    @Id // this annotation is used to mark this field as the primary key of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private Date dob;

    @Enumerated(EnumType.STRING) // this annotation is used to specify that the enum values should be stored as strings in the database
    private UserStatus status;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserDepartment> userDepartment = new ArrayList<>();

}
