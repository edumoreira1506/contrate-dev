<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="Contrate DEV - Editar">
	<jsp:body>
    <div class="Form">
	  <h2 class="Form__title">Editar perfil</h2>    
   	  <form action="${role}/editar-perfil" method="POST">
				<input value="${user.name}" type="text" class="Form__input" name="name" placeholder="Nome completo" required>
				<input value="${user.language}" type="text" class="Form__input" name="language" placeholder="Sua linguagem principal" required>
				<textarea name="description" class="Form__textarea" placeholder="Uma breve descrição sobre você" required>${user.description}</textarea>
				<input value="${user.email}" type="email" class="Form__input" name="email" placeholder="E-mail de acesso" required>
				<input value="${user.email}" type="hidden" name="id" required>
				<input value="${user.github}" type="text" name="github" class="Form__input" placeholder="Github" required>
				<input value="${user.cellphone}" type="text" class="Form__input" name="cellphone" placeholder="Telefone/Celular" required>
				<button class="Form__button transition" type="submit" class="transition">
        	Editar
        </button>
      </form>
    </div>
  </jsp:body>
</t:template>