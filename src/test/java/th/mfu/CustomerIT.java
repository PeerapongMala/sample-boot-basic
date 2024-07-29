package th.mfu;



import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;

import java.nio.Buffer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



import th.mfu.dto.CustomerDTO;

public class CustomerIT {
    private static Client client;

    @BeforeAll
    public static void createClient(){
        client = ClientBuilder.newClient();
    }

    @Test
    public void testCreateCustomer(){
        CustomerDTO customer = new CustomerDTO();
        customer.setName("John Cenaaaaaa");
        customer.setAddress("You can'T see me");
        customer.setEmail("JohnCenaaaaaa@hotmail.com");
        customer.setPhone("087xxx1234");

        Builder builder = client.target("http://localhost:8080/customers").request();
        Response response = builder.post(Entity.json(customer));

        assertEquals(201,response.getStatus());
    }
}
