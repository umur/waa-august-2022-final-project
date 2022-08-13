package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.interfaces.JobAdvertisementService;
import com.example.demo.service.interfaces.StudentService;
import com.example.demo.service.interfaces.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/charts")
@RequiredArgsConstructor
@CrossOrigin
public class ChartsController {
    private final TagService tagService;
    private final StudentService studentService;
    private final JobAdvertisementService jobAdvertisementService;

    @GetMapping("/tags")
    public ChartTagsModel tags() {
        ChartTagsModel result = new ChartTagsModel();
        var tags = tagService.findAll();
        var tagNames = tags.stream().map(e -> e.getTagName()).collect(Collectors.toList());
        var freq = tagNames.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        result.setAxis(freq.keySet().stream().toList());
        result.setData(freq.values().stream().toList());
        return result;
    }

    @GetMapping("/jobByLocation")
    public ChartJobByLocationModel jobByLocation() {
        ChartJobByLocationModel result = new ChartJobByLocationModel();
        var jobs = jobAdvertisementService.findAll();
        var jobCityNames = jobs.stream().map(e -> e.getCity()).collect(Collectors.toList());
        var freq = jobCityNames.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<ChartData> data = new ArrayList<>();
        Iterator<Map.Entry<String, Long>> itr = freq.entrySet().iterator();

        while(itr.hasNext())
        {
            Map.Entry<String, Long> entry = itr.next();
            data.add(new ChartData(entry.getKey(),entry.getValue()));
        }

        result.setData(data);
        return result;
    }

    @GetMapping("/studentsPerState")
    public ChartStudentPerStateModel studentsPerState() {
        ChartStudentPerStateModel result = new ChartStudentPerStateModel();
        var stds = studentService.findAll();
        var stdNames = stds.stream().map(e -> e.getProfile().getFirstName()).collect(Collectors.toList());
        List<ChartData> data = new ArrayList<>();
        data.add(new ChartData("IOWA", stdNames.size()));
        data.add(new ChartData("CHICAGO", 3));
        result.setData(data);
        return result;
    }

    @GetMapping("/studentsPerCity")
    public ChartStudentPerCityModel studentsPerCity() {
        ChartStudentPerCityModel result = new ChartStudentPerCityModel();
        var stds = studentService.findAll();
        var stdNames = stds.stream().map(e -> e.getProfile().getFirstName()).collect(Collectors.toList());
        result.setAxis(Arrays.asList("Fairfield", "Otumwa"));
        result.setData(Arrays.asList(stdNames.size(), 2));
        return result;
    }
}
