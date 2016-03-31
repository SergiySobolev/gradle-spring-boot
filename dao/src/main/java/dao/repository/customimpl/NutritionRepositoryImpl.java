package dao.repository.customimpl;

import dao.domain.NutritionEntity;
import dao.domain.UnitEntity;
import dao.repository.NutritionRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

@Repository
public class NutritionRepositoryImpl implements NutritionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void insertNutritionAndUnit() {
        NutritionEntity nutrition = new NutritionEntity();
        nutrition.setId(10L);
        nutrition.setCode("buckwheat");
        nutrition.setName("buckwheat");

        UnitEntity unit1 = new UnitEntity();
        unit1.setId(20L);
        unit1.setCode("lb");
        unit1.setName("pound");

        UnitEntity unit2 = new UnitEntity();
        unit2.setId(30L);
        unit2.setCode("kg");
        unit2.setName("kilogram");


        Set<UnitEntity> units = new HashSet<>();
        units.add(unit1);
        units.add(unit2);

        nutrition.setUnits(units);
        entityManager.persist(nutrition);
    }
}
