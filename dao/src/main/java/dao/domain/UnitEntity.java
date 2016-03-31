package dao.domain;

import javax.persistence.*;

@Entity
@NamedNativeQuery(name = "UnitEntity.insertUnit",
        query="Insert into Unit(id, code, name) values (:id, :code, :name )"
)
@Table(name = "unit")
public class UnitEntity extends BaseEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name="code")
    private String code;

    @Column(name = "name")
    private String name;

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
}
