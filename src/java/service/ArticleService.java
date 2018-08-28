/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.slugify.Slugify;
import dao.ArticleDao;
import entity.Article;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author user129
 */

@Service
@EnableScheduling
public class ArticleService {
    
    @Autowired    
    private ArticleDao articleDao;
    
    
    @Scheduled(cron = "0 */60 * * * ?")
    public void saveFromNewsApi(){
        String API_TOKEN = "b964ca5daf034cb18a8c4c1f1aa6a17c";
        Map<String,String> CATEGORIES = new HashMap<>();
        CATEGORIES.put("blockchain", "http://newsapi.org/v2/everything?q=blockchain&sortBy=popularity&apiKey=" + API_TOKEN);
        CATEGORIES.put("android", "http://newsapi.org/v2/everything?q=android&sortBy=popularity&apiKey=" + API_TOKEN);
        CATEGORIES.put("ios", "http://newsapi.org/v2/everything?q=ios&sortBy=popularity&apiKey=" + API_TOKEN);
        
        List<Article> articles = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for(Map.Entry category : CATEGORIES.entrySet()){
            articles = getFromNewsApi(category.getValue().toString());
            Slugify slg = new Slugify();
            articles.forEach(article -> {
                article.setCategory(category.getKey().toString());
                article.setSlug(slg.slugify(article.getTitle()));
            });
            titles = findAll().stream().map(Article::getTitle).collect(Collectors.toList());
            for (Article article : articles){
                if(!titles.contains(article.getTitle())){
                    addArticle(article);
                }
            }
        }
    }
    
    public List<Article> getFromNewsApi(String url){
        
        List<Article> articles = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        try {                
            String jsonString = mapper.readTree(new URL(url)).get("articles").toString();
            articles = mapper.readValue(jsonString, new TypeReference<List<Article>>(){});            
        } catch (Throwable e) {
            e.printStackTrace();
        } 
        return articles;
    }
    
    public List<Article> findAll(){
        return articleDao.findAll();
    }
    
    public Article findById(int id){
        return articleDao.findById(id);
    }
    
    public Article findBySlug(String slug){
        return articleDao.findBySlug(slug);
    }
    
    public List<Article> findByCategory(String category){
        return articleDao.findByCategory(category);
    }
    
    public void addArticle(Article art){
        articleDao.add(art);
    }    
    
    public void deleteArticle(int id){
        articleDao.delete(id);
    }
        
    public void updateArticle(Article art){
        articleDao.update(art);
    }
}
