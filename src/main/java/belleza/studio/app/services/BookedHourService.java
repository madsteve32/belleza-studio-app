package belleza.studio.app.services;

import belleza.studio.app.models.service.BookedHourServiceModel;
import belleza.studio.app.models.view.BookedHoursViewModel;

import java.time.LocalDateTime;
import java.util.List;

public interface BookedHourService {
    void saveHour(BookedHourServiceModel bookedHourServiceModel);

    boolean isBooked(LocalDateTime bookedDateAndTime);

    List<BookedHoursViewModel> getAllBookedHours();

    void completeHour(long id);
}
