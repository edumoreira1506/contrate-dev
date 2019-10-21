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

</head>
<body>
  <h1>template!</h1>
  <jsp:doBody />
</body>
</html>