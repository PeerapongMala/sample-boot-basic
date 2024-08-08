package th.mfu.dto;

import java.util.List;

public class SaleOrderDTO {

    private Long id;
    private String notes;
    private CustomerDTO customer;
    private List<ProductDTO> products;
    public List<ProductDTO> getProducts() {
        return products;
    }
    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public CustomerDTO getCustomer() {
        return customer;
    }
    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }


}
