package hibernate.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "packages")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int packageid;

    @Column(unique = true)
    private String packagetitle;
    private String packagedescription;
    private String packagegraphic;

}
