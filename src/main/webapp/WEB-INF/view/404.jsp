<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:template title="Contrate DEV - Não encontrada">
	<jsp:body>
    <div class="not-found">
      <h2 class="not-found__status">
        404
      </h2>
      <h2 class="not-found__text">
        Página não encontrada
      </h2>
      <a href="/contrate-dev" class="not-found__link transition">
        Voltar a navegar
      </a>
    </div>
  </jsp:body>
</t:template>