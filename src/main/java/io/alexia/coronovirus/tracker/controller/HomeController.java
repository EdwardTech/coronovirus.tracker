package io.alexia.coronovirus.tracker.controller;

import io.alexia.coronovirus.tracker.models.LocationStats;
import io.alexia.coronovirus.tracker.services.VirusDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    VirusDataService virusDataService;

    @GetMapping("/")
    public String home(Model model) {

        List<LocationStats> allStats = virusDataService.getAllStats();

        int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();

        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalCases);
        return "home";
    }
}
