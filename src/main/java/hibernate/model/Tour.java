package hibernate.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tours")
@Data
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tourid")
    private int id;
    @Column(name = "tourname")
    private String name;
    private String blurb;
    private String description;
    private BigDecimal price;
    private String difficulty;
    private String graphic;
    private int length;
    private String region;
    private String keywords;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "packageid")
    private Package packageId;

}