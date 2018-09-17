package ru.javarush.tt_ihardu.testtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javarush.tt_ihardu.testtask.entity.PartPC;
import ru.javarush.tt_ihardu.testtask.repository.RepositoryPartPC;

import java.util.Map;

@Controller
public class PartsController {

    @Autowired
    private RepositoryPartPC repoPartPC;

    @GetMapping("/test")
    public String test(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "test";
    }

    @GetMapping
    public String main(@RequestParam(required = false, defaultValue = "") String name_filter, Model model) {
        Boolean isFilter = name_filter!=null && !name_filter.isEmpty();
        Iterable<PartPC> parts = (isFilter)
                ? repoPartPC.findByNameLike(name_filter)
                : repoPartPC.findAll();
        model.addAttribute("parts", parts);
        model.addAttribute("name_filter",name_filter);
        model.addAttribute("is_filter",isFilter);

        return "main";
    }

    @PostMapping
    public String add(@RequestParam String name,
                      @RequestParam(name="needs", required = false, defaultValue = "false") Boolean needs,
                      @RequestParam Long count,
                      Map<String, Object> model
    ) {
        PartPC partPC = new PartPC(name, (needs==null)?false:needs, count);

        repoPartPC.save(partPC);

        Iterable<PartPC> parts = repoPartPC.findAll();
        model.put("parts", parts);

        return "main";
    }
}
