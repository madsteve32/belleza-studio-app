package belleza.studio.app.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "service_type")
public class StudioFeatureTypeEntity extends BaseEntity {

    private String name;
    private BigDecimal price;
    private StudioFeatureEntity service;

    public StudioFeatureTypeEntity() {
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne
    public StudioFeatureEntity getService() {
        return service;
    }

    public void setService(StudioFeatureEntity service) {
        this.service = service;
    }
}
