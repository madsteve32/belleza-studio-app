package belleza.studio.app.models.service;

import belleza.studio.app.models.entities.ServiceTypeEntity;

import java.time.LocalDateTime;

public class BookedHourServiceModel {

    private String firstName;
    private String lastName;
    private LocalDateTime bookedDateAndTime;
    private String number;
    private String imageUrl;

    public BookedHourServiceModel() {
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
}
