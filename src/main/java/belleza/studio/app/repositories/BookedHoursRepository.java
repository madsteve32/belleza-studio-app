package belleza.studio.app.repositories;

import belleza.studio.app.models.entities.BookedHoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookedHoursRepository extends JpaRepository<BookedHoursEntity, Long> {
}
