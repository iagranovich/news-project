/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Article;
import java.util.List;
import mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author user129
 */
public class ArticleDao {
    
    public final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public ArticleDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<Article> findAll(){
        String sql = "SELECT * FROM articles";
        return jdbcTemplate.query(sql, new ArticleMapper());
    }
    
    public Article findById(int id){
        String sql = "SELECT * FROM articles WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new ArticleMapper(), id);
    }    
        
//    public Article getBySlug(String slug){
//        String sql = "SELECT * FROM table1 WHERE slug=?";
//        return jdbcTemplate.queryForObject(sql, new ArticleMapper(), slug);
//    }
    
    public void add(Article art){
        String sql ="INSERT INTO articles (author,title,description,url,image,category) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, art.getAuthor(), art.getTitle(), art.getDescription(), art.getUrl(), art.getImage(), art.getCategory());
    }
        
    public void delete(int id){
        String sql = "DELETE FROM articles WHERE id=?";
        jdbcTemplate.update(sql, id);
    } 
        
    public void update(Article art){
        String sql = "UPDATE articles SET title=?, description=?, url=?, image=? WHERE id=?";
        jdbcTemplate.update(sql, art.getTitle(), art.getDescription(), art.getUrl(), art.getImage(), art.getId());
    }
}
