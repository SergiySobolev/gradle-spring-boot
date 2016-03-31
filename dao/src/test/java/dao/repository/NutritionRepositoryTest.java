package dao.repository;

import dao.DaoTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DaoTestApplication.class)
public class NutritionRepositoryTest {

    @Autowired
    private NutritionRepository repository;

    @Test
    public void baseTest(){
        assertNotNull(repository);
    }

    @Test
    public void testGetNutritionCount() throws Exception {
        assertThat(repository.getNutritionCount(), equalTo(2L));
    }


}