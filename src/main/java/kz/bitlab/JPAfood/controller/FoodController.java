package kz.bitlab.JPAfood.controller;


import kz.bitlab.JPAfood.entity.Food;
import kz.bitlab.JPAfood.repository.FoodRepository;
import kz.bitlab.JPAfood.repository.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class FoodController {

    private final FoodRepository foodRepository;
    private final ManufacturerRepository manufacturerRepository;


    @GetMapping("/")
    public String start(Model model,
                        @RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(value = "size", required = false, defaultValue = "8") int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Food> foodsPage = foodRepository.findAll(pageRequest);
        List<Food> foods = foodsPage.getContent();
        model.addAttribute("food", foods);

        // Номер текущей страницы
        int currentPageNumber = foodsPage.getNumber();
        model.addAttribute("currentPage", currentPageNumber);

        // Размер страницы
        model.addAttribute("pageSize", pageSize);

        // Общее количество страниц
        // Например, если у нас в базе данных имеется 8 машин, и мы хотим разместить по 4 машины на каждой странице, то общее количество страниц будет равно 2.
        int totalPages = foodsPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);

        // Список номеров страниц сгенерирован с помощью Stream API
        List<Integer> pages = IntStream.range(0, totalPages).boxed().toList();   // [0, 1, 2, …, N-1]
        model.addAttribute("pageNumbers", pages);

        return "start";
    }

    @GetMapping("/add-food")
    public String addPage(Model model) {
        List<Food> foods = foodRepository.findAll();
        model.addAttribute("food", foods);
        return "add-food";
    }

    @PostMapping("/add-food")
    public String addFood(@RequestParam("name") String name,
                          @RequestParam("calories") int calories,
                          @RequestParam("amounts") int amounts,
                          @RequestParam("price") int price) {
        Food food=Food.builder()
                .name(name)
                .calories(calories)
                .amounts(amounts)
                .price(price)
                .build();
        foodRepository.save(food);
        return "redirect:/";
    }


    @PostMapping("/delete-food")
    public String deleteTask(@RequestParam Long foodId) {
        foodRepository.deleteById(foodId);
        return "redirect:/";
    }
    @PostMapping("/food-edit/{id}")
    public String editFood(@PathVariable Long id,
                           @RequestParam("name") String name,
                           @RequestParam("calories") int calories,
                           @RequestParam("amounts") int amounts,
                           @RequestParam("price") int price) {
        Food food = foodRepository.findById(id).orElseThrow();
        food.setName(name);
        food.setCalories(calories);
        food.setAmounts(amounts);
        food.setPrice(price);
        foodRepository.save(food);
        return "redirect:/";
    }





}
