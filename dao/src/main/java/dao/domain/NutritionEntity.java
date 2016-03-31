package dao.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedNativeQuery(name="NutritionEntity.insertNutrition",
        query = "Insert into Nutrition(id, code, name) values (:id, :code, :name )")
@Table(name = "nutrition")
public class NutritionEntity extends BaseEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name="code")
    private String code;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "nutrition_unit",
            joinColumns = {@JoinColumn(name = "nutrition_ID", nullable = false, updatable = false)},
            inverseJoinColumns = { @JoinColumn(name = "unit_id", nullable = false, updatable = false) }
    )
    private Set<UnitEntity> units = new HashSet<>(0);


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UnitEntity> getUnits() {
        return units;
    }

    public void setUnits(Set<UnitEntity> units) {
        this.units = units;
    }
}
