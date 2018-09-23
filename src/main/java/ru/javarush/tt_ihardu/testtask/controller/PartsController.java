package ru.javarush.tt_ihardu.testtask.controller;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javarush.tt_ihardu.testtask.entity.PartPC;
import ru.javarush.tt_ihardu.testtask.repository.RepositoryPartPC;
import ru.javarush.tt_ihardu.testtask.service.ConnectDB;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class PartsController {

    @Autowired
    private RepositoryPartPC repoPartPC;

    @Autowired
    private ConnectDB connectDB = ConnectDB.getConnection();

    @GetMapping("/about")
    public String about(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Model model
    ) {
        model.addAttribute("name", name);
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
            @RequestParam(name="page", required = false) Long page,
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
                return "redirect:/"+((page==null)?"":"?page="+page);
            } catch (Exception e) {
                return "redirect:/edit?error=An error occurred while adding data!!!";
            }
        }
    }

    @GetMapping("/delete")
    public String delete(
            @RequestParam(name="id") Long id,
            @RequestParam(name="page", required = false) Long page
    ) {
        if (id!=null)
            if (id.intValue()>0 )
                repoPartPC.deleteById(id);

        return "redirect:/"+((page==null)?"":"?page="+page);
    }

    @GetMapping
    public String main(
            @RequestParam(name="name_filter", required = false, defaultValue = "") String name_filter,
            @RequestParam(name="radio_box", required = false, defaultValue = "all") String radio_box,
            @PageableDefault(size=10, sort = { "name", "needs" }, direction = Sort.Direction.ASC) Pageable pageable,
            Model model
    ){
        try {
            boolean isFilterName = name_filter != null && !name_filter.isEmpty();
            boolean isFilterType = radio_box != null && (radio_box.equals("yes") || radio_box.equals("no"));
            Boolean isFilter = isFilterName || isFilterType;
            //--- Элемент с минималным кол-вом, который нужен при сборке
            List<PartPC> listParts = repoPartPC.findByNeedsTrue();
            PartPC partMin = (listParts==null || listParts.size()<=0) ? (null) : (listParts.stream()
                    .min(Comparator.comparing(PartPC::getCount))
                    .get());
            //--- Список, согласно фильтра и пейдингу
            Page<PartPC> pageParts = (isFilter)
                    ? (isFilterType
                    ? (isFilterName
                    ? (repoPartPC.findByNameContainingAndNeeds(name_filter, radio_box.equals("yes"), pageable))
                    : (repoPartPC.findByNeeds(radio_box.equals("yes"), pageable))
            )
                    : (repoPartPC.findByNameContaining(name_filter, pageable))
            )
                    : repoPartPC.findAll(pageable);
            //---
            model.addAttribute("url", "");
            model.addAttribute("page_parts", pageParts);
            model.addAttribute("name_filter", name_filter);
            model.addAttribute("radio_box", radio_box);
            model.addAttribute("is_filter", isFilter);
            model.addAttribute("count_pc", (partMin == null) ? "0" : partMin.getCount().toString());
        } catch (NoSuchElementException e){
            model.addAttribute("error", "There's something wrong with the data! Try to update them via Script!");
        }
       //---
        return "main";
    }

    @GetMapping("/script")
    public String script(
            @RequestParam(name="message", required=false) String message,
            @RequestParam(name="error", required=false) String error,
            Model model
    ) {
        model.addAttribute("message",message);
        model.addAttribute("error",error);
        return "script";
    }

    @PostMapping("/script")
    public String script() {
        if ( connectDB.goScript() )
            return "redirect:/script?message=Congratulations! The data has been successfully loaded, you can see them in the listing.";
        else
            return "redirect:/script?error=Something went wrong! Check the log file for errors!";
    }
}
