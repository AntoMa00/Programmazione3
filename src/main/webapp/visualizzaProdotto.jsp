<%--
  Created by IntelliJ IDEA.
  User: mikel
  Date: 24/02/2026
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Visualizza Prodotto</title>
</head>
<body>



<div>

    <ul>
        <c:forEach var="prodotto" items="${listaProdotti}">
            <li>
                    ${prodotto.nome} - ${prodotto.descrizione} - ${prodotto.quantita} -
                    ${prodotto.prezzoBase} - ${prodotto.categoria} - ${prodotto.sconto} -
                    ${prodotto.prezzoScontato}
                <form action="carrello" method="post">
                    <input type="hidden" name="codice" value="${prodotto.codice}">
                    <input type="hidden" name="nome" value="${prodotto.nome}">
                    <input type="hidden" name="descrizione" value="${prodotto.descrizione}">
                    <input type="hidden" name="quantita" value="${prodotto.quantita}">
                    <input type="hidden" name="prezzoBase" value="${prodotto.prezzoBase}">
                    <input type="hidden" name="categoria" value="${prodotto.categoria}">
                    <input type="hidden" name="sconto" value="${prodotto.sconto}">
                    <input type="hidden" name="prezzoScontato" value="${prodotto.prezzoScontato}">
                    <button>Aggiungi al carrello</button>
                </form>

            </li>
        </c:forEach>
    </ul>

</div>

Prodotti Consigliati:

<div>
    <p>Numero prodotti consigliati: <c:out value="${listaProdottiConsigliati.size()}"/></p>


    <ul>
        <c:forEach var="prodotto" items="${listaProdottiConsigliati}">
            <li>
                    ${prodotto.nome} - ${prodotto.descrizione} - ${prodotto.quantita} -
                    ${prodotto.prezzoBase} - ${prodotto.categoria} - ${prodotto.sconto} -
                    ${prodotto.prezzoScontato}
                <form action="carrello" method="post">
                    <input type="hidden" name="codice" value="${prodotto.codice}">
                    <input type="hidden" name="nome" value="${prodotto.nome}">
                    <input type="hidden" name="descrizione" value="${prodotto.descrizione}">
                    <input type="hidden" name="quantita" value="${prodotto.quantita}">
                    <input type="hidden" name="prezzoBase" value="${prodotto.prezzoBase}">
                    <input type="hidden" name="categoria" value="${prodotto.categoria}">
                    <input type="hidden" name="sconto" value="${prodotto.sconto}">
                    <input type="hidden" name="prezzoScontato" value="${prodotto.prezzoScontato}">
                    <button>Aggiungi al carrello</button>
                </form>

            </li>
        </c:forEach>
    </ul>
</div>

</body>
</html>
