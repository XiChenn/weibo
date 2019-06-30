package com.yellowbooklet.shopsvc.repository;

import com.yellowbooklet.shopsvc.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
