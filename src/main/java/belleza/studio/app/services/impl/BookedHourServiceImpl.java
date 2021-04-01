package belleza.studio.app.services.impl;

import belleza.studio.app.models.entities.BookedHoursEntity;
import belleza.studio.app.models.service.BookedHourServiceModel;
import belleza.studio.app.models.view.BookedHoursViewModel;
import belleza.studio.app.repositories.BookedHoursRepository;
import belleza.studio.app.services.BookedHourService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public boolean isBooked(LocalDateTime bookedDateAndTime) {
        return bookedHoursRepository.findByBookedDateAndTime(bookedDateAndTime).isPresent();
    }

    @Override
    public List<BookedHoursViewModel> getAllBookedHours() {
        List<BookedHoursViewModel> viewModels = new ArrayList<>();

        List<BookedHoursEntity> bookedHoursEntities = bookedHoursRepository.findAll();

        bookedHoursEntities.forEach(bookedHour -> {
            BookedHoursViewModel bookedHoursViewModel = new BookedHoursViewModel();
            modelMapper.map(bookedHour, bookedHoursViewModel);

            viewModels.add(bookedHoursViewModel);
        });

        return viewModels;
    }

    @Transactional
    @Override
    public void completeHour(long id) {
        bookedHoursRepository.deleteById(id);
    }
}
