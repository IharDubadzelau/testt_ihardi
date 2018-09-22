package ru.javarush.tt_ihardu.testtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javarush.tt_ihardu.testtask.entity.PartPC;
import ru.javarush.tt_ihardu.testtask.repository.RepositoryPartPC;

import javax.sound.midi.Soundbank;
import java.util.Map;
@Controller
public class PartsController {

    @Autowired
    private RepositoryPartPC repoPartPC;

    @GetMapping("/about")
    public String about(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "about";
    }

    @GetMapping("/edit")
    public String edit(
            @RequestParam(name="id", required=false) Long id,
            @RequestParam(name="message", required=false) String message,
            @RequestParam(name="error", required=false) String error,
            Model model
    ) {
        model.addAttribute("part",  (id==null)?null:repoPartPC.findById(id).get());
        model.addAttribute("message",message);
        model.addAttribute("error",error);

        return "edit";
    }

    @PostMapping("/edit")
    public String edit(
            @RequestParam(name="id", required = false, defaultValue = "" ) String id,
            @RequestParam("name") String name,
            @RequestParam("count") Long count,
            @RequestParam(name="needs", required = false) Boolean needs
    ) {
        long validateId = 0;
        try {
            validateId = Long.parseLong(id);
        } catch (Exception e){}

        String error = "";
        if ( name==null || name.length()<=0 )
            error = "- Part name must be set! ";
        if ( count==null || count.intValue()<0 )
            error += "- Part count must have a value of 0 or more!";

        if ( error.length()>0 )
            return "redirect:/edit?error=Data entry error !!! "+error;

        PartPC partPC;
        if ( validateId<=0 ) {
            partPC = new PartPC(name, (needs == null) ? false : needs, count);
            try {
                repoPartPC.save(partPC);
                return "redirect:/edit?message=Data was successfully added to the database!!! "+name+", "+count+", "+partPC.isNeeds();
            } catch (Exception e) {
                return "redirect:/edit?error=An error occurred while adding data!!!";
            }
        }
        else {
            partPC = repoPartPC.findById(validateId).get();
            partPC.setName(name);
            partPC.setCount(count);
            partPC.setNeeds((needs==null)?false:needs);

            try {
                repoPartPC.save(partPC);
                return "redirect:/main";
            } catch (Exception e) {
                return "redirect:/edit?error=An error occurred while adding data!!!";
            }
        }
    }

    @GetMapping("/delete")
    public String delete(
            @RequestParam("id") Long id
    ) {
        if (id!=null)
            if (id.intValue()>0 )
                repoPartPC.deleteById(id);

        return "redirect:/main";
    }

    @GetMapping
    public String main(
            @RequestParam(required = false, defaultValue = "") String name_filter,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC ) Pageable pageable
    ) {
        Boolean isFilter = name_filter!=null && !name_filter.isEmpty();
        Page<PartPC> pageParts = (isFilter)
                ? repoPartPC.findByNameLike(name_filter, pageable)
                : repoPartPC.findAll(pageable);

        model.addAttribute("url", "");
        model.addAttribute("page_parts", pageParts);
        model.addAttribute("name_filter",name_filter);
        model.addAttribute("is_filter",isFilter);

        return "main";
    }
}
