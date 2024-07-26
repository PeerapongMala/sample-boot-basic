package th.mfu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.Product;

public interface ProductRepo extends CrudRepository<Product, Long>{
    List<Product> findAll();

    
}
