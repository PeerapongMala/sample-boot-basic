/*package th.mfu;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;

import org.apache.coyote.Response;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class CustomerCtrlTest {

    // @Autowired
    public CustomerCtrl controller;// = new CustomerCtrl()
    //Long temp = (long) 0;

    @Test
    public void createAndGet() {
        controller = new CustomerCtrl();

        Customer customer = new Customer();
        customer.setName("Jame");
        customer.setAddress("JJJJJame");
        customer.setEmail("Jameeee");
        customer.setPhone("0622222222");

        ResponseEntity<String> response = controller.createCustomer(customer);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        ResponseEntity<Customer> returnCust = controller.getCustomer(temp);
        assertEquals(HttpStatus.OK, returnCust.getStatusCode());
        assertEquals("Jame", returnCust.getBody().getName());
    }

    @Test
    public void delAndNoFound() {
        controller = new CustomerCtrl();

        Customer customer = new Customer();
        customer.setName("Jame");
        customer.setAddress("JJJJJame");
        customer.setEmail("Jameeee");
        customer.setPhone("0622222222");

        controller.createCustomer(customer);

        ResponseEntity<String> response = controller.deleteCustomer(temp);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        ResponseEntity<Customer> returnCust = controller.getCustomer(temp);
        assertEquals(HttpStatus.NOT_FOUND, returnCust.getStatusCode());

    }
}
*/