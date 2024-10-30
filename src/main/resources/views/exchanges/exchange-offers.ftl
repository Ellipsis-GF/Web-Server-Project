<#ftl encoding="utf-8">
<head>
    <title>Offres d'échange</title>
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
<div>
<h1>Offres d'échange</h1>
    <table>
    <thead>
            <tr>
                <th>idDresseur</th>
                <th>Pseudo du Dresseur</th>
                <th>Je donne</th>
                <th>exp</th>
                <th>Je reçois</th>
                <th>exp</th>
                <th>Choix</th>
            </tr>
    </thead>
    <tbody>
        <#list exchanges as exchange>
        <tr>
            <td>${exchange.user1.id}</td>
            <td>${exchange.user1.pseudo}</td>
            <td>${exchange.pokemon2.name}</td>
            <td>${exchange.pokemon2.exp}</td>
            <td>${exchange.pokemon1.name}</td>
            <td>${exchange.pokemon1.exp}</td>
            <td>
                <form action="/exchange-offers" method="POST">
                    <input type="hidden" name="accept" value="true" required>
                    <input type="hidden" name="pokemon2" value="${exchange.pokemon2.id}" required>
                    <button type="submit">Accepter</button>
                </form>
                <form action="/exchange-offers" method="POST">
                    <input type="hidden" name="accept" value="false" required>
                    <input type="hidden" name="pokemon2" value="${exchange.pokemon2.id}" required>
                    <button type="submit">Décliner</button>
                </form>
            </td>
        </tr>
        </#list>
    </tbody>
    </table>
</div>

</body>

</html>