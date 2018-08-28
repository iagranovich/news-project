/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Article;
import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.commons.io.IOUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
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
    
    @RequestMapping(value={"/main", "/"})
    public String main(Model model){
        model.addAttribute("articles", articleService.findAll());
        return "main";
    }
    
    @RequestMapping("/categories/{category}")
    public String category(Model model, @PathVariable("category") String category) {
        model.addAttribute("articles", articleService.findByCategory(category));        
        return "main";
    }
    
    @RequestMapping("/articles/{slug}")
    public ResponseEntity<String> slug(@PathVariable("slug") String slug) throws IOException, URISyntaxException{
        Article article = articleService.findBySlug(slug);
        
        //news scraping        
        String url = article.getUrl();
        HttpGet request = new HttpGet(url);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity;
        entity = response.getEntity();        
        String encoding =  EntityUtils.getContentCharSet(entity);
        String mime = EntityUtils.getContentMimeType(entity);
        String responseText =  IOUtils.toString(entity.getContent(), encoding);//получаем html страницы - можно проще?
        
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", mime + "; charset=" + encoding);
        
        return new ResponseEntity<>(responseText, responseHeaders, HttpStatus.OK);        
    }
        
    @RequestMapping(value="/parse")
    public ResponseEntity<String> parse() {
        articleService.saveFromNewsApi();
        return new ResponseEntity<>("", HttpStatus.OK);
    }
    
    @RequestMapping(value="/admin")
    public String admin() {        
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
        
        return "redirect:/admin/articles";
    }
    
    @RequestMapping(value="/admin/articles/add")
    public String add(Model model){
        model.addAttribute(new Article());
        return "add";
    }    
    
    @RequestMapping(value="/admin/articles/delete/{id}")
    public String delete(@PathVariable("id") int id){
        articleService.deleteArticle(id);
        return "redirect:/admin/articles";
    }
    
    @RequestMapping("/admin/articles/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("article", articleService.findById(id));
        return "edit";
    }
}
