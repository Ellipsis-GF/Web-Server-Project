<#ftl encoding="utf-8">
<head>
    <title>Pokemon Quotidient</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body xmlns="http://www.w3.org/1999/html">
<headers>
    <h1>Pokemon Quotidien</h1>
</headers>
<nav>
    <ul> 
        <li><a href="/pokedex">Voir mes Pokémons</a></li>
        <li><a href="/newpokemon">Obtenir mon Pokémon quotidien</a></li>
        <li><a href="/training">Entrainer mes Pokémons</a></li>
        <li><a href="/exchange">Echanger des Pokémons</a></li>
        <li><a href="/exchange-offers">Voir les offres d'échange reçu</a></li>
    </ul>
</nav>
<article>
    <#if available>
        Nouveau pokemon disponible : <br>
        ${name} - ${exp}
    <#else>
        ${erreur}
    </#if>
</article>
</body>

</html>
