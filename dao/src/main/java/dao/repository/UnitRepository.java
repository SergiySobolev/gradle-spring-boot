package dao.repository;

import dao.domain.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UnitRepository extends JpaRepository<UnitEntity, Long> {

    @Modifying
    @Query(nativeQuery = true)
    void insertUnit(@Param("id")Long id,
                    @Param("code")String code,
                    @Param("name")String name);


}
