<#ftl encoding="utf-8">
<head>
    <title>Offres d'échange</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body xmlns="http://www.w3.org/1999/html">
<headers>Offres d'échange</headers>
<nav>
    <ul> 
        <li><a href="/pokedex">Voir mes Pokémons</a></li>
        <li><a href="/newpokemon">Obtenir mon Pokémon quotidien</a></li>
        <li><a href="/training">Entrainer mes Pokémons</a></li>
        <li><a href="/exchange">Echanger des Pokémons</a></li>
        <li><a href="/exchange-offers">Voir les offres d'échange reçu</a></li>
    </ul>
</nav>
<div>
    <h1>Echange</h1>
    <form action="/exchange" method="GET">
        <input type="text" name="search" placeholder="Rechercher..." required>
        <button type="submit">Rechercher</button>
    </form>
    <hr>
    <h2>Résultat recherche</h2>
    <#if searched>
        <table>
            <thead>
                    <tr>
                        <th>idPokemon</th>
                        <th>Pokemon</th>
                        <th>exp</th>
                        <th>Dresseur</th>
                    </tr>
            </thead>
            <tbody>
                <#list pokemons2 as pokemon2>
                    <tr>
                        <td>${pokemon2.id}</td>
                        <td>${pokemon2.name}</td>
                        <td>${pokemon2.exp}</td>
                        <td>${pokemon2.user.pseudo}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
    <#else>
        Aucun résultat trouvé
    </#if>
    <hr>
    <h2>Mes Pokémons</h2>
    <table>
        <thead>
                <tr>
                    <th>idPokemon</th>
                    <th>Pokemon</th>
                    <th>exp</th>
                </tr>
        </thead>
        <tbody>
            <#list pokemons1 as pokemon1>
                <tr>
                    <td>${pokemon1.id}</td>
                    <td>${pokemon1.name}</td>
                    <td>${pokemon1.exp}</td>
                </tr>
            </#list>
        </tbody>
    </table>
    <hr>
    <form action="/exchange" method="POST">
        <label for="idPokemon1">Id du pokémon que vous souhaitez donner</label>
        <input type="number" name="idPokemon1" required> <br>
        <label for="idPokemon2">Id du pokémon que vous souhaitez recevoir</label>
        <input type="number" name="idPokemon2" required> <br>
        <button type="submit">Proposer l'échange</button>
    </form>
</div>

</body>

</html>