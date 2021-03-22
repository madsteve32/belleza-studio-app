package belleza.studio.app.web;

import belleza.studio.app.models.binding.BookedHourBindingModel;
import belleza.studio.app.services.BookedHourService;
import belleza.studio.app.services.ServiceEntityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/booking")
public class BookedHoursController {

    private final BookedHourService bookedHourService;
    private final ServiceEntityService serviceEntityService;

    public BookedHoursController(BookedHourService bookedHourService, ServiceEntityService serviceEntityService) {
        this.bookedHourService = bookedHourService;
        this.serviceEntityService = serviceEntityService;
    }

    @ModelAttribute("bookedHourBindingModel")
    public BookedHourBindingModel bookedHourBindingModel() {
        return new BookedHourBindingModel();
    }

    @GetMapping("/hour")
    public String bookHour(Model model) {
        model.addAttribute("services", serviceEntityService.getAllServices());

        return "book-an-hour";
    }

//    @PostMapping("/hour")
}
