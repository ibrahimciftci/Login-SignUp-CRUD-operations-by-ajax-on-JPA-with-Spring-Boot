package com.SistasProject.Repositories;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.SistasProject.Entities.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{	
	Product findByProductId(Long productId);
}
