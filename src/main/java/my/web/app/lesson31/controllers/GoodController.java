package my.web.app.lesson31.controllers;

import my.web.app.lesson31.models.Good;
import my.web.app.lesson31.repo.IGoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GoodController {
    @Autowired
    private IGoodRepository goodRepository;

    @GetMapping ("/form")
    public String showForm(){
        return "form";
    }



    @GetMapping("/goods")
    public String showGoods(Model model){
        Iterable<Good> goods = goodRepository.findAll(); //получаем все записи из таблицы товаров
        model.addAttribute("goods", goods);
        return "goods";
    }

    @PostMapping("/good/add")
    public String addGood(
            @RequestParam String name,
            @RequestParam String info,
            @RequestParam int price
    ){
        var good = new Good(name,info,price);
        goodRepository.save(good); // выполняется операция вставки
        return "redirect:/goods";
    }

    @GetMapping("/good/{id}")
    public String showCard(@PathVariable(value = "id") long id, Model model){
        var good = goodRepository.findById(id).get();
        model.addAttribute("good", good);
        return "card";
    }

    @GetMapping("/good/delete/{id}")
    public String deleteGood(@PathVariable(value = "id") long id, Model model){
        goodRepository.deleteById(id);
        return "redirect:/goods";
    }

    @GetMapping("/good/edit/{id}")
    public String editGood(@PathVariable(value = "id") long id, Model model){
        Good good = goodRepository.findById(id).get();
        model.addAttribute("good", good);
        return "form_edit";
    }

    @PostMapping("/good/edit/{id}")
    public String updateGood(@PathVariable(value = "id") long id,
                             @RequestParam String name,
                             @RequestParam String info,
                             @RequestParam int price
                             ){
        Good good = goodRepository.findById(id).get();
        good.setName(name);
        good.setInfo(info);
        good.setPrice(price);
        goodRepository.save(good);
        return "redirect:/goods";
    }
}
