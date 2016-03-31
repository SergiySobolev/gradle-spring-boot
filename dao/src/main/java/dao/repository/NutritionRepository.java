package dao.repository;

import dao.domain.NutritionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface NutritionRepository extends JpaRepository<NutritionEntity, Long> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true)
    void insertNutrition(@Param("id")Long id,
                         @Param("code")String code,
                         @Param("name")String name);

    @Query("select count(e) from NutritionEntity e")
    long getNutritionCount();
}
