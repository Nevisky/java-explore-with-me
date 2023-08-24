package ru.practicum.category.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.service.CategoryService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/categories")
public class PublicCategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> findAllCategories(@RequestParam(name = "from", defaultValue = "0")
                                                   @PositiveOrZero int from,
                                               @RequestParam(name = "size", defaultValue = "10")
                                               @Positive int size) {

        return categoryService.findAllCategories(from,size);
    }

    @GetMapping({"/{catId}"})
    public CategoryDto findCategoryById(@PathVariable("catId") Long catId) {

        return categoryService.findCategoryById(catId);
    }
}
