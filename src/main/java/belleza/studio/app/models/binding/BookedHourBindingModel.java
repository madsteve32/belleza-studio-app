package belleza.studio.app.models.binding;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class BookedHourBindingModel {

    private String firstName;
    private String lastName;
    private LocalDateTime bookedDateAndTime;
    private String number;
    private String imageUrl;
    private Long serviceTypeId;

    public BookedHourBindingModel() {
    }

    @NotEmpty
    @Size(min = 3, message = "First name must be at least 3 characters.")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotEmpty
    @Size(min = 3, message = "Last name must be at least 3 characters.")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be in the past")
    public LocalDateTime getBookedDateAndTime() {
        return bookedDateAndTime;
    }

    public void setBookedDateAndTime(LocalDateTime bookedDateAndTime) {
        this.bookedDateAndTime = bookedDateAndTime;
    }

    @NotEmpty(message = "Telephone number cannot be empty.")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Long serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }
}
