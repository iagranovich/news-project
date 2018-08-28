/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import entity.Article;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author user129
 */
public class ArticleMapper implements RowMapper<Article>{

    @Override
    public Article mapRow(ResultSet rs, int i) throws SQLException {
        Article entry = new Article();
        entry.setId(rs.getInt("id"));
        entry.setAuthor(rs.getString("author"));
        entry.setTitle(rs.getString("title"));
        entry.setDescription(rs.getString("description"));
        entry.setUrl(rs.getString("url"));
        entry.setImage(rs.getString("image"));
        entry.setDate(rs.getDate("date"));
        entry.setCategory(rs.getString("category"));
        entry.setSlug(rs.getString("slug"));
        return entry;
    }
    
}
