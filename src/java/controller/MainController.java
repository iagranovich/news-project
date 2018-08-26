/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ArticleService;

/**
 *
 * @author user129
 */

@Controller
public class MainController {
    
    @Autowired
    ArticleService articleService;    
    
    @RequestMapping(value="/main")
    public String Main(Model model){
        model.addAttribute("articles", articleService.findAll());
        return "main";
    }
    
//    @RequestMapping("/articles/{slug}")
//    public String Slug(@PathVariable("slug") String slug, Model model){
//        model.addAttribute("entry", articleService.getBySlug(slug));
//        return "entry";
//    }
    
    @RequestMapping(value="/admin")
    public String admin(){
        return "admin";
    }
    
    @RequestMapping(value="/admin/articles")
    public String articles(Model model){
        model.addAttribute("articles", articleService.findAll());
        return "articles";
    }
    
    @RequestMapping(value="/admin/articles/add", method=RequestMethod.POST)
    public String add(@ModelAttribute("article") Article art){
        if(art.getId() == 0){
            articleService.addArticle(art);    
        }else{
            articleService.updateArticle(art);
        }
        
        return "redirect:/admin/articles.htm";
    }
    
    @RequestMapping(value="/admin/articles/add")
    public String add(Model model){
        model.addAttribute(new Article());
        return "add";
    }    
    
    @RequestMapping(value="/admin/articles/delete/{id}")
    public String delete(@PathVariable("id") int id){
        articleService.deleteArticle(id);
        return "redirect:/admin/articles.htm";
    }
    
    @RequestMapping("/admin/articles/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("article", articleService.findById(id));
        return "edit";
    }
}
