package belleza.studio.app.web;

import belleza.studio.app.models.binding.BookedHourBindingModel;
import belleza.studio.app.models.service.BookedHourServiceModel;
import belleza.studio.app.services.BookedHourService;
import belleza.studio.app.services.StudioFeatureService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/booking")
public class BookedHoursController {

    private final BookedHourService bookedHourService;
    private final StudioFeatureService studioFeatureService;
    private final ModelMapper modelMapper;

    public BookedHoursController(BookedHourService bookedHourService, StudioFeatureService studioFeatureService, ModelMapper modelMapper) {
        this.bookedHourService = bookedHourService;
        this.studioFeatureService = studioFeatureService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("bookedHourBindingModel")
    public BookedHourBindingModel bookedHourBindingModel() {
        return new BookedHourBindingModel();
    }

    @GetMapping("/hour")
    public String bookHour(Model model) {
        model.addAttribute("features", studioFeatureService.getAllFeatures());

        return "book-an-hour";
    }

    @PostMapping("/hour")
    public String bookHourConfirm(@Valid BookedHourBindingModel bookedHourBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bookedHourBindingModel", bookedHourBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bookedHourBindingModel", bindingResult);

            return "redirect:/booking/hour";
        }

        if (bookedHourService.isBooked(bookedHourBindingModel.getBookedDateAndTime())) {
            redirectAttributes.addFlashAttribute("bookedHourBindingModel", bookedHourBindingModel);
            redirectAttributes.addFlashAttribute("dateIsBookedError", true);

            return "redirect:/booking/hour";
        }

        BookedHourServiceModel bookedHourServiceModel = modelMapper
                .map(bookedHourBindingModel, BookedHourServiceModel.class);


        bookedHourService.saveHour(bookedHourServiceModel);

        return "redirect:/home";
    }
}
