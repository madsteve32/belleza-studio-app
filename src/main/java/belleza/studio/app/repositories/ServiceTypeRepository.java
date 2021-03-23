package belleza.studio.app.repositories;

import belleza.studio.app.models.entities.StudioFeatureTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepository extends JpaRepository<StudioFeatureTypeEntity, Long> {
}
