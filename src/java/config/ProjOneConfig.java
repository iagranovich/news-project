/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import controller.MainController;
import dao.ArticleDao;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import service.ArticleService;

/**
 *
 * @author user129
 */

@Configuration
public class ProjOneConfig {
        
    @Bean
    public ArticleService articleService(){
        return new ArticleService();
    }
    
    @Bean
    public MainController mainController(){
        return new MainController();
    }
    
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/newsdb");
        dataSource.setUsername("admin");
        dataSource.setPassword("admin");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource;
    }
        
    @Bean    
    public JdbcTemplate jdbcTemplate(){        
        return new JdbcTemplate(dataSource());
    }
    
    @Bean
    public ArticleDao articleDao(){
        return new ArticleDao(jdbcTemplate());
    }
}
