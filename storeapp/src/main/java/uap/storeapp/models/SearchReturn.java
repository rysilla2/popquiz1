package uap.storeapp.models;

import java.util.List;

public class SearchReturn {
    String name;
    String address;
    List<SearchDTO> products ;

    Double total;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<SearchDTO> getProducts() {
        return products;
    }

    public void setProducts(List<SearchDTO> products) {
        this.products = products;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
