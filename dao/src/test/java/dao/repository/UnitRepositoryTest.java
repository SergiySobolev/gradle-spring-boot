package dao.repository;

import dao.DaoIT;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class UnitRepositoryTest extends DaoIT{

    @Autowired
    private UnitRepository unitRepository;

    @Test
    public void testInsertUnit() throws Exception {
        unitRepository.insertUnit(1L, "kg", "kilogram");
        assertThat(unitRepository.findAll().size(), equalTo(1));
    }


}