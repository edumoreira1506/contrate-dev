<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="Contrate DEV - Usuários">
  <jsp:body>
    <div class="Table">
      <h2 class="Table__title">USUÁRIOS CADASTRADOS</h2>
      <table>
        <tr class="Table__header">
          <td>Nome Completo</td>
          <td>Linguagem</td>
          <td>E-mail</td>
          <td>Telefone/Celular</td>
          <td>Ações</td>
        </tr>
        <c:forEach var="u" items="${users}">
          <c:if test="${not empty u.language}">
          	<tr class="Table__cell">
            <td>${u.name}</td>
            <td>${u.language}</td>
            <td>${u.email}</td>
            <td>${u.cellphone}</td>
            <td>
	            <a href="a/usuarios/remover?id=${u.email}" class="Table__action transition">
	              <img 
				    src="resources/icons/delete.svg" 
					class="Icon"
				   />
	            </a>
            </td>
           </tr>
          </c:if>
        </c:forEach>
      </table>
    </div>
  </jsp:body>
</t:template>
