<!-- contanti.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Pagamento Contanti</title>
</head>

<body>

<h2>Pagamento in Contanti</h2>

<form action="tentativopagamento" method="post">
    Nome cliente:<br>
    <input type="text" name="nome"><br><br>

    <input type="checkbox" name="conferma"> Pagamento ricevuto<br><br>

    <input type="hidden" name="quantita" value="${sessionScope.quantita}">
    <input type="submit" value="Registra pagamento">

</form>

</body>
</html>