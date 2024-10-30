<#ftl encoding="utf-8">
<head>
    <title>Pokedex</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body xmlns="http://www.w3.org/1999/html">
    <nav>
        <ul> 
            <li><a href="/pokedex">Voir mes Pokémons</a></li>
            <li><a href="/newpokemon">Obtenir mon Pokémon quotidien</a></li>
            <li><a href="/training">Entrainer mes Pokémons</a></li>
            <li><a href="/exchange">Echanger des Pokémons</a></li>
            <li><a href="/exchange-offers">Voir les offres d'échange reçu</a></li>
        </ul>
    </nav>
    <h1>Pokedex</h1>
    <table>
        <thead>
                <tr>
                    <th>idPokemon</th>
                    <th>Pokemon</th>
                    <th>exp</th>
                </tr>
        </thead>
        <tbody>
            <#list pokemons as pokemon>
                <tr>
                    <td>${pokemon.id}</td>
                    <td>${pokemon.name}</td>
                    <td>${pokemon.exp}</td>
                </tr>
            </#list>
        </tbody>
    </table>

</body>

</html>
