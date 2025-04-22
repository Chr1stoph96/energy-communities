package com.example.energy_communities;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/energy")
public class EnergyController {

    @GetMapping("/current")
    public EnergyData getCurrent() {
        EnergyData data = new EnergyData();
        data.setHour(LocalDateTime.now().withMinute(0).withSecond(0).withNano(0));
        data.setCommunityDepleted(87.5); // Beispielwert
        data.setGridPortion(12.6); // Beispielwert
        return data;
    }

    @GetMapping("/historical")
    public List<EnergyData> getHistorical(@RequestParam String start, @RequestParam String end) {
        List<EnergyData> list = new ArrayList<>();
        LocalDateTime from = LocalDateTime.parse(start);
        LocalDateTime to = LocalDateTime.parse(end);

        // Beispieldaten
        for (LocalDateTime hour = from; hour.isBefore(to); hour = hour.plusHours(1)) {
            EnergyData d = new EnergyData();
            d.setHour(hour);
            d.setCommunityProduced(10 + Math.random() * 5);
            d.setCommunityUsed(10 + Math.random() * 5);
            d.setGridUsed(Math.random() * 3);
            list.add(d);
        }

        return list;
    }

}
