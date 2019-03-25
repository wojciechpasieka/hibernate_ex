package hibernate.model;

import lombok.Data;

import javax.persistence.*;

@Data
@NamedQuery(name = "Admin_findByUsername", query = "from Admin where username =: username")
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;
    private String password;
}