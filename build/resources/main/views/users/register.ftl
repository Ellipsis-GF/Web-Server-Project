<#ftl encoding="utf-8">
<head>
    <title>S'inscrire</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<html xmlns="http://www.w3.org/1999/html">
<body>
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
      <h1>Inscription</h1>
      <form method="post" action="/register">
        <label for="pseudo">Pseudo :</label>
        <input type="text" id="pdeudo" name="pseudo" required>
        <br>
        <label for="firstname">Prénom :</label>
        <input type="text" id="firstname" name="firstname" required>
        <br>
        <label for="lastname">Nom :</label>
        <input type="text" id="lastname" name="lastname" required>
        <br>
        <label for="email">Email :</label>
        <input type="email" id="email" name="email" required>
        <br>
        <label for="password">Mot de passe :</label>
        <input type="password" id="password" name="password" required>
        <br>
        <input type="submit" value="S'inscrire">
      </form>
  </div>
  </body>
</html>
