package org.prgrms.kyu.controller;

import lombok.RequiredArgsConstructor;
import org.prgrms.kyu.dto.FoodRequest;
import org.prgrms.kyu.service.FoodService;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping("new-food")
    public String newFoodPage(Model model) {
        model.addAttribute("foodForm", new FoodRequest());
        return "food/new-food";
    }

    @PostMapping("/foods")
    public String newFood(@ModelAttribute("foodForm") FoodRequest request) {
        foodService.save(request, 6L);
        return "redirect:/user/myShop";
    }
}
