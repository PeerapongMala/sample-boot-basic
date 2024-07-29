package th.mfu;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import th.mfu.domain.Customer;
import th.mfu.domain.Product;
import th.mfu.dto.CustomerDTO;
import th.mfu.dto.ProductDTO;
import th.mfu.dto.Mapper.ProductMapper;
import th.mfu.repository.ProductRepo;

@RestController
public class ProductCtrl {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        if (!productRepo.existsById(id)) {
            return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
        }
        Optional<Product> product = productRepo.findById(id);
        ProductDTO dto = new ProductDTO();
        productMapper.updateProductFromEntity(product.get(), dto);
        return new ResponseEntity<ProductDTO>(dto, HttpStatus.OK);
    }

    @GetMapping("/products/all")
    public ResponseEntity<Collection> getAllProduct() {
        return new ResponseEntity<Collection>(productRepo.findAll(), HttpStatus.OK);
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductDTO customerDTO) {
        if (!productRepo.existsById(id))
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        Optional<Product> productEnt = productRepo.findById(id);
        Product product = productEnt.get();
        productMapper.updateProductFromDto(customerDTO, product);
        productRepo.save(product);
        return new ResponseEntity<String>("Product updated", HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@RequestBody ProductDTO product) {
        Product newProduct = new Product();
        productMapper.updateProductFromDto(product, newProduct);
        if (productRepo.findBypName(product.getpName()).isEmpty()) {
            productRepo.save(newProduct);
            return new ResponseEntity<String>("Product created", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Product has already exist.", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productRepo.deleteById(id);
        return new ResponseEntity<String>("Product deleted", HttpStatus.NO_CONTENT);
    }
}
