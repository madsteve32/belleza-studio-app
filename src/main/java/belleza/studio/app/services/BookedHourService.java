package belleza.studio.app.services;

import belleza.studio.app.models.service.BookedHourServiceModel;

import java.time.LocalDateTime;

public interface BookedHourService {
    void saveHour(BookedHourServiceModel bookedHourServiceModel);

    boolean isBooked(LocalDateTime bookedDateAndTime);
}
