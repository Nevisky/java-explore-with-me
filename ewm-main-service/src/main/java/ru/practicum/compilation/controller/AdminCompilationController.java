package ru.practicum.compilation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.compilation.dto.CompilationDto;
import ru.practicum.compilation.dto.NewCompilationDto;
import ru.practicum.compilation.mapper.CompilationMapper;
import ru.practicum.compilation.model.Compilation;
import ru.practicum.compilation.service.CompilationServiceImpl;
import ru.practicum.exception.ValidationException;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/compilations")
@Validated
@RequiredArgsConstructor
public class AdminCompilationController {

    private final CompilationServiceImpl compilationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto add(@Valid @RequestBody NewCompilationDto newCompilationDto) {
        if (newCompilationDto.getTitle() == null || newCompilationDto.getTitle().isBlank())
            throw new ValidationException("Название подборки не может быть пустым");
        Compilation compilation =
                compilationService.addCompilation(CompilationMapper.toCompilation(newCompilationDto));

        return CompilationMapper.toCompilationDto(compilation);
    }

    @PatchMapping("/{compilationId}")
    public CompilationDto patch(
            @PathVariable Long compilationId, @Valid @RequestBody NewCompilationDto newCompilationDto
    ) {
        Compilation compilation = compilationService
                .updateCompilation(compilationId, CompilationMapper.toCompilation(newCompilationDto));

        return CompilationMapper.toCompilationDto(compilation);
    }

    @DeleteMapping("/{compilationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long compilationId) {
        compilationService.removeCompilation(compilationId);
    }
}
