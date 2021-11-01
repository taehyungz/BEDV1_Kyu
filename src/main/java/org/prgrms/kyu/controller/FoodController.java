package org.prgrms.kyu.controller;

import lombok.RequiredArgsConstructor;
import org.prgrms.kyu.commons.S3Uploader;
import org.prgrms.kyu.dto.FoodRequest;
import org.prgrms.kyu.service.FoodService;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;
    private final S3Uploader s3Uploader;

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

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("data") MultipartFile multipartFile) throws IOException {
        return s3Uploader.upload(multipartFile, "static");
    }

}
