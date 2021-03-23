package belleza.studio.app.services.impl;

import belleza.studio.app.models.entities.BookedHoursEntity;
import belleza.studio.app.models.service.BookedHourServiceModel;
import belleza.studio.app.repositories.BookedHoursRepository;
import belleza.studio.app.services.BookedHourService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BookedHourServiceImpl implements BookedHourService {

    private final BookedHoursRepository bookedHoursRepository;
    private final ModelMapper modelMapper;

    public BookedHourServiceImpl(BookedHoursRepository bookedHoursRepository, ModelMapper modelMapper) {
        this.bookedHoursRepository = bookedHoursRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveHour(BookedHourServiceModel bookedHourServiceModel) {
        BookedHoursEntity bookedHoursEntity = modelMapper
                .map(bookedHourServiceModel, BookedHoursEntity.class);

        bookedHoursRepository.save(bookedHoursEntity);
    }
}
