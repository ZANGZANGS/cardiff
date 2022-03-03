package cardiff.admin.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MobilityController {

    @GetMapping(value = "/mobility/brand")
    public String brandHtmlForm(){

        return "brand";
    }

    @GetMapping(value = "/mobility/vehicle")
    public String vehicleHtmlForm(){

        return "vehicle";
    }

    @GetMapping(value = "/mobility/option")
    public String optionHtmlForm(){

        return "option";
    }
}
