package th.mfu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.repository.*;
import th.mfu.domain.*;
import th.mfu.dto.*;
import th.mfu.security.*;

public interface SellOrderRepo extends CrudRepository<SellOrder,Long> {
    public List<SellOrder> findAll();

    public List<SellOrder> findByCustomerId(Long customerId);

    public List<SellOrder> findBycustomerName(String name);
}
