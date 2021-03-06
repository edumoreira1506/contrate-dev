<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="Contrate DEV - Vaga">
  <jsp:body>
    <div class="Modal__background hidden" id="modal-github">
      <div class="MyModal">        
        <div class="Modal__header">
          <span id="close-modal">
            X
          </span>
          <div class="row">
            <div class="col-md-4">
              <img id="user-image" id="modal-image" class="Modal__image">
            </div>
            <div class="col-md-8">
              <ul class="Modal__list" id="modal-list"></ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="Table">
      <h2 class="Table__title">CANDIDATOS</h2>
      <table>
        <tr class="Table__header">
          <td>Nome Completo</td>
          <td>Linguagem</td>
          <td>E-mail</td>
          <td>Telefone/Celular</td>
          <td>Ações</td>
        </tr>
        <c:forEach var="c" items="${job.candidates}">
          <tr class="Table__cell">
            <td>${c.name}</td>
            <td>${c.language}</td>
            <td>${c.email}</td>
            <td>${c.cellphone}</td>
            <td>
	            <a href="g/usuarios/visualizar?id=${c.email}" class="Table__action transition">
                <img 
                  src="resources/icons/user.svg" 
                  class="Icon"
                />
              </a>
              <button href="#" onclick="consultGithub('${c.github}')" class="Table__action transition">
                <img 
                  src="resources/icons/github.svg" 
                  class="Icon"
                />
	            </button>
            </td>
           </tr>
        </c:forEach>
      </table>
    </div>
  </jsp:body>
</t:template>
