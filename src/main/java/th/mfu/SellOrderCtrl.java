package th.mfu;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import th.mfu.repository.*;
import th.mfu.domain.*;
import th.mfu.dto.*;
import th.mfu.dto.Mapper.SellOrderMapper;
import th.mfu.security.*;

@RestController
public class SellOrderCtrl {

    @Autowired
    SellOrderMapper sellOrderMapper;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private SellOrderRepo sellOrderRepo;

    @PostMapping("customers/{cID}/orders")
    public ResponseEntity<String> createOrder(@PathVariable Long cID, @RequestBody SellOrderDTO sellOrder) {
        SellOrder newSellOrder = new SellOrder();
        sellOrderMapper.updateSellOrderFromDto(sellOrder, newSellOrder);
        if (!customerRepo.existsById(cID)) {
            return new ResponseEntity<String>("Customer not found", HttpStatus.NOT_FOUND);
        }
        Optional<Customer> optCustomer = customerRepo.findById(cID);

        newSellOrder.setCustomer(optCustomer.get());
        sellOrderRepo.save(newSellOrder);
        return new ResponseEntity<String>("Order created", HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity<Collection<SellOrder>> getAllOrder() {
        return new ResponseEntity<Collection<SellOrder>>(sellOrderRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("customers/orders/{id}")
    public ResponseEntity<List<SellOrderDTO>> getOrderByID(@PathVariable Long id) {
        if (customerRepo.existsById(id)) {
            List<SellOrder> orders = sellOrderRepo.findByCustomerId(id);

            List<SellOrderDTO> dto = new ArrayList<>();
            sellOrderMapper.updateSellOrderFromEntity(orders, dto);
            return new ResponseEntity<List<SellOrderDTO>>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<List<SellOrderDTO>>(HttpStatus.NOT_FOUND);
    }

    /*
     * @GetMapping("/orders/{name}")
     * public ResponseEntity<Collection<SellOrder>> getOrderByName(@PathVariable
     * String name) {
     * return new
     * ResponseEntity<Collection<SellOrder>>(sellOrderRepo.findBycustomerName(name),
     * HttpStatus.OK);
     * }
     */
}
