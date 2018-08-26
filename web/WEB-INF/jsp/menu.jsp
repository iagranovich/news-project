<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>
    <form id="menu" method="post" action="/news-project/logout">
    Меню:
    <a href="/news-project/admin.htm">Главная</a> | 
    <a href="/news-project/admin/articles.htm">Статьи</a> |
    
    <input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" />     
    <a href ="#" onclick="document.forms['menu'].submit()">Выход</a>  
    </form>
</h2>