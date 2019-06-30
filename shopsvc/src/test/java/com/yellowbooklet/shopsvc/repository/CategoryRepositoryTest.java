package com.yellowbooklet.shopsvc.repository;

import com.yellowbooklet.shopsvc.entity.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    //@Transactional
    public void save() {
        Category category = new Category();
        //category.setId(1);
        category.setName("Kids");
        category.setType(119);
        category.setLanguageCode("en-US");
        Category result  = repository.save(category);
        Assert.assertNotNull(result);
    }

    @Test
    public void findAll() {
        List<Category> categories = repository.findAll();
        System.out.println("*************");
        for (Category category : categories) {
            System.out.println(category.toString());
//            category.setLanguageCode("en-US");
//            repository.save(category);
        }
    }
}