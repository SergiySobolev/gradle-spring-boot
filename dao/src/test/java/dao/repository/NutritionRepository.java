package dao.repository;

import dao.domain.NutritionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NutritionRepository extends JpaRepository<NutritionEntity, Long> {

    @Query("select count(e) from NutritionEntity e")
    long getNutritionCount();
}
