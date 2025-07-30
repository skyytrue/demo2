package kz.bitlab.JPAfood.controller;

import kz.bitlab.JPAfood.entity.Manufacturer;
import kz.bitlab.JPAfood.repository.ManufacturerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class ManufacturerController {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerController(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @GetMapping("/add-country")
    public String addCountry() {
        return "add-country";
    }

    @PostMapping("/add-country")
    public String addCountry(@RequestParam String name,
                             @RequestParam String code) {
        Manufacturer manufacturer=new Manufacturer();
        manufacturer.setName(name);
        manufacturer.setCode(code);
        manufacturerRepository.save(manufacturer);

        return "redirect:/";

    }


}
