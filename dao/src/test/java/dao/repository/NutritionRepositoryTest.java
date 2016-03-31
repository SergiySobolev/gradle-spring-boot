package dao.repository;

import dao.DaoIT;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;


public class NutritionRepositoryTest extends DaoIT{

    @Autowired
    private NutritionRepository nutritionRepository;

    @Test
    public void baseTest(){
        assertNotNull(nutritionRepository);
    }

    @Test
    public void testGetNutritionCount() throws Exception {
        nutritionRepository.insertNutrition(1L,"buckwheat","buckwheat");
        nutritionRepository.insertNutrition(2L,"'rice'","rice");
        assertThat(nutritionRepository.getNutritionCount(), equalTo(2L));
    }

    @Test
    public void testInsertNutritionAndUnit() throws Exception {
        nutritionRepository.insertNutritionAndUnit();
        assertThat(nutritionRepository.getNutritionCount(), equalTo(1L));
    }

}