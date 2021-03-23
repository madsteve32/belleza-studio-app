package belleza.studio.app.models.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;

@Entity
@Table(name = "booked_hours")
public class BookedHoursEntity extends BaseEntity {

    private String firstName;
    private String lastName;
    private LocalDateTime bookedDateAndTime;
    private String number;
    private String imageUrl;
    private StudioFeatureTypeEntity serviceType;

    public BookedHoursEntity() {
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "booked_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be in the past")
    public LocalDateTime getBookedDateAndTime() {
        return bookedDateAndTime;
    }

    public void setBookedDateAndTime(LocalDateTime bookedDateAndTime) {
        this.bookedDateAndTime = bookedDateAndTime;
    }

    @Column(name = "telephone_number", nullable = false)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @ManyToOne
    public StudioFeatureTypeEntity getServiceType() {
        return serviceType;
    }

    public void setServiceType(StudioFeatureTypeEntity serviceType) {
        this.serviceType = serviceType;
    }
}
