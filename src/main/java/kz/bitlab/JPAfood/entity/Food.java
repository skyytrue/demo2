package kz.bitlab.JPAfood.entity;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="calories")
    private int calories;

    @Column(name="price")
    private int price;

    @Column(name="amounts")
    private int amounts;

    @JoinColumn(name="country_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Manufacturer manufacturer;


}
