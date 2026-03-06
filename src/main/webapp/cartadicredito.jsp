<!-- cartadicredito.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Pagamento Carta di Credito</title>
</head>

<body>

<h2>Pagamento con Carta di Credito</h2>

<form action="tentativopagamento" method="post">
    Nome titolare:<br>
    <input type="text" name="nome"><br><br>

    Numero carta:<br>
    <input type="text" name="numero"><br><br>

    Data di scadenza:<br>
    <input type="month" name="scadenza"><br><br>

    CVV:<br>
    <input type="password" name="cvv"><br><br>

    <input type="hidden" name="quantita" value="${sessionScope.quantita}">
    <input type="submit" value="Paga">

</form>

</body>
</html>