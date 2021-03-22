package belleza.studio.app.services;

import belleza.studio.app.models.view.ServiceViewModel;

import java.util.List;

public interface ServiceEntityService {
    void initServices();

    List<ServiceViewModel> getAllServices();
}
