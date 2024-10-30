<#ftl encoding="utf-8">
<head>
    <title>Se connecter</title>
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
      <h1>Connexion</h1>
      <form method="post" action="/login">
        <label for="pseudo">Pseudo ou email :</label>
        <input type="text" id="pdeudo" name="pseudo" required>
        <br>
        <label for="password">Mot de passe :</label>
        <input type="password" id="password" name="password" required>
        <br>
        <input type="submit" value="Se connecter">
      </form>
  </div>
  </body>
</html>
