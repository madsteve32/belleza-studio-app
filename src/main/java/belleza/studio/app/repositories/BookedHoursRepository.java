package belleza.studio.app.repositories;

import belleza.studio.app.models.entities.BookedHoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface BookedHoursRepository extends JpaRepository<BookedHoursEntity, Long> {

    @Query("SELECT b FROM BookedHoursEntity b WHERE b.bookedDateAndTime = :bookedDate")
    Optional<BookedHoursEntity> findByBookedDateAndTime(@Param("bookedDate") LocalDateTime bookedDate);
}
