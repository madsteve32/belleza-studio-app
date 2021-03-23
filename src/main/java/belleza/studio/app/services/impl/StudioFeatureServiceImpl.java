package belleza.studio.app.services.impl;

import belleza.studio.app.models.entities.StudioFeatureEntity;
import belleza.studio.app.models.entities.StudioFeatureTypeEntity;
import belleza.studio.app.models.view.StudioFeatureTypeViewModel;
import belleza.studio.app.models.view.StudioFeatureViewModel;
import belleza.studio.app.repositories.ServiceTypeRepository;
import belleza.studio.app.repositories.ServicesRepository;
import belleza.studio.app.services.StudioFeatureService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudioFeatureServiceImpl implements StudioFeatureService {

    private final ServicesRepository servicesRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    private final ModelMapper modelMapper;

    public StudioFeatureServiceImpl(ServicesRepository servicesRepository, ServiceTypeRepository serviceTypeRepository, ModelMapper modelMapper) {
        this.servicesRepository = servicesRepository;
        this.serviceTypeRepository = serviceTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initServices() {
        if (servicesRepository.count() == 0) {
            StudioFeatureEntity manicure = new StudioFeatureEntity();
            manicure.setName("Manicure");

            StudioFeatureEntity pedicure = new StudioFeatureEntity();
            pedicure.setName("Pedicure");

            StudioFeatureEntity makeup = new StudioFeatureEntity();
            makeup.setName("Makeup");

            StudioFeatureEntity hairstyle = new StudioFeatureEntity();
            hairstyle.setName("Hairstyle");

            servicesRepository.saveAll(List.of(manicure, pedicure, makeup, hairstyle));

            StudioFeatureTypeEntity frenchManicure = new StudioFeatureTypeEntity();
            frenchManicure.setName("French manicure");
            frenchManicure.setPrice(BigDecimal.valueOf(25.00));
            frenchManicure.setService(manicure);

            StudioFeatureTypeEntity gelPolish = new StudioFeatureTypeEntity();
            gelPolish.setName("Gel Polish");
            gelPolish.setPrice(BigDecimal.valueOf(25.00));
            gelPolish.setService(manicure);

            StudioFeatureTypeEntity threeDPolish = new StudioFeatureTypeEntity();
            threeDPolish.setName("3D Polish");
            threeDPolish.setPrice(BigDecimal.valueOf(35.00));
            threeDPolish.setService(manicure);

            StudioFeatureTypeEntity repairPolish = new StudioFeatureTypeEntity();
            repairPolish.setName("Repair manicures");
            repairPolish.setPrice(BigDecimal.valueOf(15.00));
            repairPolish.setService(manicure);

            // Pedicure

            StudioFeatureTypeEntity frenchPedicure = new StudioFeatureTypeEntity();
            frenchPedicure.setName("French pedicure");
            frenchPedicure.setPrice(BigDecimal.valueOf(35.00));
            frenchPedicure.setService(pedicure);

            StudioFeatureTypeEntity gelPolishPedicure = new StudioFeatureTypeEntity();
            gelPolishPedicure.setName("Gel Polish pedicure");
            gelPolishPedicure.setPrice(BigDecimal.valueOf(35.00));
            gelPolishPedicure.setService(pedicure);

            StudioFeatureTypeEntity threeDPolishPedicure = new StudioFeatureTypeEntity();
            threeDPolishPedicure.setName("3D Polish pedicure");
            threeDPolishPedicure.setPrice(BigDecimal.valueOf(45.00));
            threeDPolishPedicure.setService(pedicure);

            StudioFeatureTypeEntity repairPolishPedicure = new StudioFeatureTypeEntity();
            repairPolishPedicure.setName("Repair pedicures");
            repairPolishPedicure.setPrice(BigDecimal.valueOf(20.00));
            repairPolishPedicure.setService(pedicure);

            // Makeups
            StudioFeatureTypeEntity bridalMakeup = new StudioFeatureTypeEntity();
            bridalMakeup.setName("Bridal makeup");
            bridalMakeup.setPrice(BigDecimal.valueOf(70.00));
            bridalMakeup.setService(makeup);

            StudioFeatureTypeEntity smokeyMakeup = new StudioFeatureTypeEntity();
            smokeyMakeup.setName("Smokey makeup");
            smokeyMakeup.setPrice(BigDecimal.valueOf(40.00));
            smokeyMakeup.setService(makeup);

            StudioFeatureTypeEntity dailyMakeup = new StudioFeatureTypeEntity();
            dailyMakeup.setName("Daily makeup");
            dailyMakeup.setPrice(BigDecimal.valueOf(30.00));
            dailyMakeup.setService(makeup);

            StudioFeatureTypeEntity customMakeup = new StudioFeatureTypeEntity();
            customMakeup.setName("Custom makeup with lashes");
            customMakeup.setPrice(BigDecimal.valueOf(55.00));
            customMakeup.setService(makeup);

            // Hairstyle
            StudioFeatureTypeEntity haircut = new StudioFeatureTypeEntity();
            haircut.setName("Haircut");
            haircut.setPrice(BigDecimal.valueOf(25.00));
            haircut.setService(hairstyle);

            StudioFeatureTypeEntity haircutPlusBlowing = new StudioFeatureTypeEntity();
            haircutPlusBlowing.setName("Haircut plus blowing");
            haircutPlusBlowing.setPrice(BigDecimal.valueOf(35.00));
            haircutPlusBlowing.setService(hairstyle);

            StudioFeatureTypeEntity painting = new StudioFeatureTypeEntity();
            painting.setName("Painting");
            painting.setPrice(BigDecimal.valueOf(40.00));
            painting.setService(hairstyle);

            serviceTypeRepository.saveAll(List.of(frenchManicure, gelPolish, threeDPolish, repairPolish,
                    frenchPedicure, gelPolishPedicure, threeDPolishPedicure, repairPolishPedicure,
                    bridalMakeup, smokeyMakeup, dailyMakeup, customMakeup,
                    haircut, haircutPlusBlowing, painting));
        }
    }

    @Override
    public List<StudioFeatureViewModel> getAllFeatures() {
        List<StudioFeatureViewModel> result = new ArrayList<>(); // final result

        List<StudioFeatureTypeEntity> allServiceTypes = serviceTypeRepository.findAll();

        allServiceTypes.forEach(serviceType -> {
            StudioFeatureEntity studioFeatureEntity = serviceType.getService();

            Optional<StudioFeatureViewModel> serviceViewModelOpt = findByName(result, studioFeatureEntity.getName());

            if (serviceViewModelOpt.isEmpty()) {
                StudioFeatureViewModel studioFeatureViewModel = new StudioFeatureViewModel();
                modelMapper.map(studioFeatureEntity, studioFeatureViewModel);
                result.add(studioFeatureViewModel);
                serviceViewModelOpt = Optional.of(studioFeatureViewModel);
            }

            StudioFeatureTypeViewModel studioFeatureTypeViewModel = new StudioFeatureTypeViewModel();
            modelMapper.map(serviceType, studioFeatureTypeViewModel);
            serviceViewModelOpt.get().addServiceType(studioFeatureTypeViewModel);
        });

        return result;
    }

    private Optional<StudioFeatureViewModel> findByName(List<StudioFeatureViewModel> allServices, String name) {
        return allServices.stream()
                .filter(s -> s.getName().equals(name)).findAny();
    }
}
