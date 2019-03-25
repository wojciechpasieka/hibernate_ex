package hibernate.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "explorers")
public class Explorer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int explorerId;

    @Column(unique = true)
    private String firstName;
    private String lastName;
    private Date dob;
    private String email;
    private String address;
    private String city;
    private String zipCode;
    private String userName;
    private String password;
    private String tours;
    private String bio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stateId")
    private State stateID;
}
