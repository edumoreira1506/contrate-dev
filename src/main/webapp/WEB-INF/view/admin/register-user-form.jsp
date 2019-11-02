<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="Contrate DEV - Novo usuário">
  <jsp:body>
    <div class="Form">
	  <h2 class="Form__title">Novo usuário</h2>    
   	  <form action="a/usuarios/cadastrar" method="POST">
				<input type="text" class="Form__input" name="name" placeholder="Nome" required>
				<input type="text" class="Form__input" name="language" placeholder="Linguagem" required>
				<input type="text" class="Form__input" name="github" placeholder="Github" required>
				<textarea name="description" class="Form__textarea" placeholder="Descrição" required></textarea>
				<input type="email" class="Form__input--half" name="email" placeholder="E-mail" required>
				<input type="text" class="Form__input--half" name="cellphone" placeholder="Celular/Telefone" required>
				<input type="password" class="Form__input--half" name="password" placeholder="Senha" required>
				<input type="password" class="Form__input--half" name="confirm_password" placeholder="Confirmação de senha" required>
				<select name="gender" required class="Form__select">
					<option>Selecionar...</option>
					<option value="M">Homem</option>
					<option value="F">Mulher</option>
				</select>
				<select name="role" required class="Form__select">
					<option>Selecionar...</option>
					<option value="admin">Admin</option>
					<option value="user">Comum</option>
				</select>
				<button class="Form__button transition" type="submit" class="transition">
          Cadastrar
        </button>
      </form>
    </div>
  </jsp:body>
</t:template>
