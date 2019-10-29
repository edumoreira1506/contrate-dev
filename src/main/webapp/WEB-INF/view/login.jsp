<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="Contrate DEV - Login">
	<jsp:body>
    <div class="Login">
      <div class="Login__area">
        <div class="Login__area__header">
          <h1 class="Login__area__header__title">
            Contrate <span class="Login__area__header__title--bold"">DEV</span>
          </h1>
          <h3 class="Login__area__header__description">
            Arranjar um emprego nunca foi tão simples!
          </h3>
        </div>
        <form class="Login__area__form" action="login" method="POST">
          <div class="Login__area__form__field">
            <input class="transition" type="email" name="email" required placeholder="e-mail">
          </div>
          <div class="Login__area__form__field">
            <input class="transition" type="password" name="password" required placeholder="senha">
          </div>
          <c:if test="${param.error != null}">
            <div class="Login__area__error">
              <h3 class="Login__area__error__text">E-mail ou senha inválidos</h3>
            </div>
		      </c:if>
          <div class="Login__area__form__button">
            <button type="submit" class="transition">
              Login
            </button>
          </div>
        </form>
        <div class="Login__area__form__register">
          <a href="registrar-se">
            Registrar-se
          </a>
        </div>
      </div>
    </div>
  </jsp:body>
</t:template>