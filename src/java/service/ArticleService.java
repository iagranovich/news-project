/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ArticleDao;
import entity.Article;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author user129
 */
public class ArticleService {
    
    @Autowired
    ArticleDao tableOneDao;
    
    public List<Article> findAll(){
        return tableOneDao.findAll();
    }
    
    public Article findById(int id){
        return tableOneDao.findById(id);
    }
    
    public void addArticle(Article art){
        tableOneDao.add(art);
    }
    
//    public Article getBySlug(String slug){
//        return tableOneDao.getBySlug(slug);
//    }
    
    public void deleteArticle(int id){
        tableOneDao.delete(id);
    }
        
    public void updateArticle(Article art){
        tableOneDao.update(art);
    }
}
