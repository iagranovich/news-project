<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<table class="table table-bordered table-hover">
    <thead>
        <tr class="table-dark" >  
            <th>Author</th>
            <th>Title</th>
            <th>Description</th>            
            <th>Tools</th> 
        </tr>
    </thead>
    <tbody>
        <c:forEach var="article" items="${articles}">
            <tr class="table-primary">                            
                <td><c:out value="${article.author}"/></td>
                <td><c:out value="${article.title}"/></td>
                <td><c:out value="${article.description}"/></td>               
                <td>
                    <a class="btn btn-danger" href="/news-project/admin/articles/delete/${article.id}.htm" role="button">Удалить</a>
                    <a class="btn btn-primary" href="/news-project/admin/articles/edit/${article.id}.htm" role="button">Редактировать</a>
                    <a class="btn btn-success" href="/news-project/articles/${article.title}.htm" role="button">Просмотреть</a>
                </td>
            </tr>
        </c:forEach> 
    </tbody>
</table>

<h4><a href="articles/add.htm">Добавить статью</a></h4>