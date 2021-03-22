package belleza.studio.app.models.view;

import java.math.BigDecimal;

public class ServiceTypeViewModel {

    private String name;
    private BigDecimal price;

    public ServiceTypeViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
