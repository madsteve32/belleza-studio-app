package belleza.studio.app.repositories;

import belleza.studio.app.models.entities.StudioFeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends JpaRepository<StudioFeatureEntity, Long> {
}
