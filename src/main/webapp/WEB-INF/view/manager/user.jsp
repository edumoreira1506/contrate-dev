<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="Contrate DEV - Usuário">
  <jsp:body>
	<div class="center">
	  <div class="User">
        <h2 class="User__name">${user.name}</h2>
        <span class="User__language">Especialista em ${user.language}</span>
        <p class="User__description">${user.description}</p>
        <h4 class="User__contact">Email: ${user.email}</h4>
        <h4 class="User__contact">Telefone/Celular: ${user.cellphone}</h4>
      </div>
	</div>
  </jsp:body>
</t:template>
