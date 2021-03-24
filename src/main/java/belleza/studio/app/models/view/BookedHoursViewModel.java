package belleza.studio.app.models.view;

import belleza.studio.app.models.entities.StudioFeatureTypeEntity;

import java.time.LocalDateTime;

public class BookedHoursViewModel {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime bookedDateAndTime;
    private String number;
    private String imageUrl;
    private StudioFeatureTypeEntity serviceType;

    public BookedHoursViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getBookedDateAndTime() {
        return bookedDateAndTime;
    }

    public void setBookedDateAndTime(LocalDateTime bookedDateAndTime) {
        this.bookedDateAndTime = bookedDateAndTime;
    }

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

    public StudioFeatureTypeEntity getServiceType() {
        return serviceType;
    }

    public void setServiceType(StudioFeatureTypeEntity serviceType) {
        this.serviceType = serviceType;
    }
}
