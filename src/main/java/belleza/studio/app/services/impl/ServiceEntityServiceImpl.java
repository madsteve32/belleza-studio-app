package belleza.studio.app.services.impl;

import belleza.studio.app.models.entities.ServiceEntity;
import belleza.studio.app.models.entities.ServiceTypeEntity;
import belleza.studio.app.repositories.ServiceTypeRepository;
import belleza.studio.app.repositories.ServicesRepository;
import belleza.studio.app.services.ServiceEntityService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ServiceEntityServiceImpl implements ServiceEntityService {

    private final ServicesRepository servicesRepository;
    private final ServiceTypeRepository serviceTypeRepository;

    public ServiceEntityServiceImpl(ServicesRepository servicesRepository, ServiceTypeRepository serviceTypeRepository) {
        this.servicesRepository = servicesRepository;
        this.serviceTypeRepository = serviceTypeRepository;
    }

    @Override
    public void initServices() {
        if (servicesRepository.count() == 0) {
            ServiceEntity manicure = new ServiceEntity();
            manicure.setName("Manicure");

            ServiceEntity pedicure = new ServiceEntity();
            pedicure.setName("Pedicure");

            ServiceEntity makeup = new ServiceEntity();
            makeup.setName("Makeup");

            ServiceEntity hairstyle = new ServiceEntity();
            hairstyle.setName("Hairstyle");

            servicesRepository.saveAll(List.of(manicure, pedicure, makeup, hairstyle));

            ServiceTypeEntity frenchManicure = new ServiceTypeEntity();
            frenchManicure.setName("French manicure");
            frenchManicure.setPrice(BigDecimal.valueOf(25.00));
            frenchManicure.setService(manicure);

            ServiceTypeEntity gelPolish = new ServiceTypeEntity();
            gelPolish.setName("Gel Polish");
            gelPolish.setPrice(BigDecimal.valueOf(25.00));
            gelPolish.setService(manicure);

            ServiceTypeEntity threeDPolish = new ServiceTypeEntity();
            threeDPolish.setName("3D Polish");
            threeDPolish.setPrice(BigDecimal.valueOf(35.00));
            threeDPolish.setService(manicure);

            ServiceTypeEntity repairPolish = new ServiceTypeEntity();
            repairPolish.setName("Repair manicures");
            repairPolish.setPrice(BigDecimal.valueOf(15.00));
            repairPolish.setService(manicure);

            // Pedicure

            ServiceTypeEntity frenchPedicure = new ServiceTypeEntity();
            frenchPedicure.setName("French pedicure");
            frenchPedicure.setPrice(BigDecimal.valueOf(35.00));
            frenchPedicure.setService(pedicure);

            ServiceTypeEntity gelPolishPedicure = new ServiceTypeEntity();
            gelPolishPedicure.setName("Gel Polish pedicure");
            gelPolishPedicure.setPrice(BigDecimal.valueOf(35.00));
            gelPolishPedicure.setService(pedicure);

            ServiceTypeEntity threeDPolishPedicure = new ServiceTypeEntity();
            threeDPolishPedicure.setName("3D Polish pedicure");
            threeDPolishPedicure.setPrice(BigDecimal.valueOf(45.00));
            threeDPolishPedicure.setService(pedicure);

            ServiceTypeEntity repairPolishPedicure = new ServiceTypeEntity();
            repairPolishPedicure.setName("Repair pedicures");
            repairPolishPedicure.setPrice(BigDecimal.valueOf(20.00));
            repairPolishPedicure.setService(pedicure);

            // Makeups
            ServiceTypeEntity bridalMakeup = new ServiceTypeEntity();
            bridalMakeup.setName("Bridal makeup");
            bridalMakeup.setPrice(BigDecimal.valueOf(70.00));
            bridalMakeup.setService(makeup);

            ServiceTypeEntity smokeyMakeup = new ServiceTypeEntity();
            smokeyMakeup.setName("Smokey makeup");
            smokeyMakeup.setPrice(BigDecimal.valueOf(40.00));
            smokeyMakeup.setService(makeup);

            ServiceTypeEntity dailyMakeup = new ServiceTypeEntity();
            dailyMakeup.setName("Daily makeup");
            dailyMakeup.setPrice(BigDecimal.valueOf(30.00));
            dailyMakeup.setService(makeup);

            ServiceTypeEntity customMakeup = new ServiceTypeEntity();
            customMakeup.setName("Custom makeup with lashes");
            customMakeup.setPrice(BigDecimal.valueOf(55.00));
            customMakeup.setService(makeup);

            // Hairstyle
            ServiceTypeEntity haircut = new ServiceTypeEntity();
            haircut.setName("Haircut");
            haircut.setPrice(BigDecimal.valueOf(25.00));
            haircut.setService(hairstyle);

            ServiceTypeEntity haircutPlusBlowing = new ServiceTypeEntity();
            haircutPlusBlowing.setName("Haircut plus blowing");
            haircutPlusBlowing.setPrice(BigDecimal.valueOf(35.00));
            haircutPlusBlowing.setService(hairstyle);

            ServiceTypeEntity painting = new ServiceTypeEntity();
            painting.setName("Painting");
            painting.setPrice(BigDecimal.valueOf(40.00));
            painting.setService(hairstyle);

            serviceTypeRepository.saveAll(List.of(frenchManicure, gelPolish, threeDPolish, repairPolish,
                    frenchPedicure, gelPolishPedicure, threeDPolishPedicure, repairPolishPedicure,
                    bridalMakeup, smokeyMakeup, dailyMakeup, customMakeup,
                    haircut, haircutPlusBlowing, painting));
        }
    }
}
