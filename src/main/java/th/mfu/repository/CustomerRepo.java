package th.mfu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.repository.*;
import th.mfu.domain.*;
import th.mfu.dto.*;
import th.mfu.security.*;

public interface CustomerRepo extends CrudRepository<Customer, Long> {
    List<Customer> findAll();
    List<Customer> findByName(String name);
}
