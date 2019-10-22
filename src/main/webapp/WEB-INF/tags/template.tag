<%-- 
    Document   : template
    Created on : Oct 21, 2019, 17:22:46 PM
    Author     : Eduardo Moreira
--%>

<%@tag description="Main template" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="title"%>


<html>
<head>
<title>${title}</title>
<base href="${pageContext.request.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="icon" type="image/png" href="favicon.png" />
<link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="resources/css/template.css"></link>
<link rel="stylesheet" href="resources/css/login.css"></link>

</head>
<body>
  <div class="Template">
    <div class="Template__menu">
      <div class="row">
        <div class="col-md-3">
          <h1 class="Template__menu__title">
            Contrate <span class="Template__menu__title--bold">DEV</span>
          </h1>
        </div>
        <div class="col-md-9">
          <ul class="Template__menu__links">
            <li class="Template__menu__links_link transition">
              <a href="#" class="transition">Link</a>            
            </li>
            <li class="Template__menu__links_link transition">
              <a href="#" class="transition">Link</a>            
            </li>
            <li class="Template__menu__links_link transition">
              <a href="#" class="transition">Link</a>            
            </li>
            <li class="Template__menu__links_link transition">
              <a href="#" class="transition">Link</a>            
            </li>
          </ul>
        </div>
      </div>
    </div>
    <jsp:doBody />
  </div>
</body>
</html>