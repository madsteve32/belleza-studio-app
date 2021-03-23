package belleza.studio.app.models.view;

import java.util.ArrayList;
import java.util.List;

public class StudioFeatureViewModel {

    private String name;
    private List<StudioFeatureTypeViewModel> serviceTypes = new ArrayList<>();

    public StudioFeatureViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudioFeatureTypeViewModel> getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(List<StudioFeatureTypeViewModel> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public void addServiceType(StudioFeatureTypeViewModel studioFeatureTypeViewModel) {
       this.serviceTypes.add(studioFeatureTypeViewModel);
    }
}
