package belleza.studio.app.services;

import belleza.studio.app.models.view.StudioFeatureViewModel;

import java.util.List;

public interface StudioFeatureService {
    void initServices();

    List<StudioFeatureViewModel> getAllFeatures();
}
