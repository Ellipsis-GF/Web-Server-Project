<#ftl encoding="utf-8">
<html>
<head>
    <title>Succès</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <#if valide == "ok">
        <div>
            <p>Inscription réalisée avec succès !</p>
            <a href="/login">Se connecter</a>
        </div>
    <#else>
        <div>
            <p>Il y a eu un problème lors de l'inscription : ${valide}</p>
            <a href="/register">retourner à la page de connexion</a>
        </div>
    </#if>
</body>
</html>
