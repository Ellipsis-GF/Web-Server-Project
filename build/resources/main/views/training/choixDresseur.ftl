<#ftl encoding="utf-8">

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
<h1>Echange</h1>
<table>
    <thead>
            <tr>
                <th>Dresseur</th>
                <th>Choisir</th>
            </tr>
    </thead>
    <tbody>
        <#list users as user>
            <tr>
                <td>${user.Pseudo}</td>
                <td>
                    <form action="/training" method="POST">
                        <input type="hidden" name="user" value="${user.id}" required>
                        <button type="submit">Choisir</button>
                    </form>
                </td>
            </tr>
        </#list>
    </tbody>
</table>

</body>

</html>
