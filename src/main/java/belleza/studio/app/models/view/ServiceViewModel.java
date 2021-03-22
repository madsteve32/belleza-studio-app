package belleza.studio.app.models.view;

import java.util.ArrayList;
import java.util.List;

public class ServiceViewModel {

    private String name;
    private List<ServiceTypeViewModel> serviceTypes = new ArrayList<>();

    public ServiceViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ServiceTypeViewModel> getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(List<ServiceTypeViewModel> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public void addServiceType(ServiceTypeViewModel serviceTypeViewModel) {
       this.serviceTypes.add(serviceTypeViewModel);
    }
}
