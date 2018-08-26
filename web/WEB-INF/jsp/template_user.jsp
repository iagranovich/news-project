<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <title><tiles:getAsString name="title"/></title>
    </head>
    <body>        
        
        <tiles:insertAttribute name="content"/>
        
    </body>
</html>
