<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="Contrate DEV - Vagas">
  <jsp:body>
	<ul class="JobsList">
	  <c:forEach var="j" items="${jobs}">
      <li class="Job">
				<div class="Job__title transition">
					<h2>${j.name} na ${j.company.name}</h2>
				</div>
				<div class="Job__description">
					<p>${j.description} | ${j.salary}</p>
				</div>
				<div class="Job__button transition">
					<a href="u/vagas/editar?id=${j.id}">Candidatar-se</a>
				</div>  
	    </li>
    </c:forEach>
	</ul>
  </jsp:body>
</t:template>
