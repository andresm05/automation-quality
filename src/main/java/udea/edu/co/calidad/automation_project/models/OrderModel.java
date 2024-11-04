package udea.edu.co.calidad.automation_project.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderModel {
    @JsonProperty("product_name")
    private String productName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Double price;
    private String description;
    private Integer quantity;
    @JsonProperty("customer_id")
    private Long customerId;
    


    public OrderModel(String productName, LocalDate date, Double price, String description, Integer quantity, Long customerId) {
        
        this.productName = productName;
        this.date = date;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.customerId = customerId;
    }

    public String getProductName() {
        return productName;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getCustomer() {
        return customerId;
    }
}
