<#ftl encoding="utf-8">
<html>
<head>
    <title>Succès</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <#if valide == "ok">
        <div>
            <p>Connexion réalisée avec succès !</p>
            <ul> 
                <li><a href="/pokedex">Voir mes Pokémons</a></li>
                <li><a href="/newpokemon">Obtenir mon Pokémon quotidien</a></li>
                <li><a href="/training">Entrainer mes Pokémons</a></li>
                <li><a href="/exchange">Echanger des Pokémons</a></li>
                <li><a href="/exchange-offers">Voir les offres d'échange reçu</a></li>
            </ul>
        </div>
    <#else>
        <div>
            <p>Il y a eu un problème lors de la connexion : ${valide}</p>
            <a href="/register">retourner à la page de connexion</a>
        </div>
    </#if>
</body>
</html>
