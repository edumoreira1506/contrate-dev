<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="Contrate DEV - Empresas">
  <jsp:body>
    <div class="Table">
      <h2 class="Table__title">EMPRESAS CADASTRADAS</h2>
      <table>
        <tr class="Table__header">
          <td>Nome da Empresa</td>
          <td>Gerente responsável</td>
          <td>Ações</td>
        </tr>
        <c:forEach var="c" items="${companies}">
          <tr class="Table__cell">
            <td>${c.name}</td>
            <td>${c.manager.name}</td>
            <td>
	            <a href="a/empresas/remover?id=${c.id}" class="Table__action transition">
				  <img 
				    src="resources/icons/delete.svg" 
					class="Icon"
				  />
	            </a>
            </td>
           </tr>
        </c:forEach>
      </table>
    </div>
  </jsp:body>
</t:template>
