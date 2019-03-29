# Programmation mobile - Song Chords

## Présentation 
Dans le cadre de la troisième année, nous avons dû réaliser une application mobile.
Etant une guitariste et pianiste amatriste, j'ai décidé de créer une application qui permettrait aux guitaristes d'avoir accés à des tablatures de leurs musiques préférées. J'ai également intégré des images des différents accords pour les guitaristes débutants. 

## Consignes et contraintes du projet:
Les consignes se résumaient en trois points essentiels:
* Avoir deux écrans : un écran avec une liste et un écran avec un détail de l’item.
* Effectuer un appel WebService à une API Rest.
* Stocker des données en caches
Il était également possible d'insérer dans son projet :
* une architecture ( MVC ou MVP ou MVVM )
*  l'utilisation du gitflow
* les notifications push
* et d'autres fonctionnalités

## L'application
### Deux écrans

Lors du lancement de l'application, on se retrouve sur l'écran avec les pochettes d'albums.

![](https://raw.githubusercontent.com/TolsyLaurenceESIEA/gif/master/recyclerview.gif)

Une fois que l'on a cliqué sur une chanson, on arrive sur une autre activité. Cette dernière correspond à la tablature de la chanson. On peut voir le titre de la chanson, l'artiste et la nécessité d'un capodastre ou non, les accords et la tablature avec les paroles.

![](https://raw.githubusercontent.com/TolsyLaurenceESIEA/gif/master/tabactivity.gif)
Si un joueur ne connais pas un accord il peut cliquer sur le nom des accords, cela le mènera à une troisième page qui affichera l'accord, accompagné d'un petit gif.

![](https://raw.githubusercontent.com/TolsyLaurenceESIEA/gif/master/chordactivity.gif)
### Api Rest
Afin de pouvoir utiliser cette application, j'ai créer un fichier json comme API.
<https://raw.githubusercontent.com/TolsyLaurenceESIEA/Songs/master/songsapi.json>

### Stockage en cache

J'ai implémenté une fonction de recherche, qui permettra à l'utilisateur de chercher une chanson en fonction du titre ou de l'artiste.
J'ai utilisé le stockage en cache afin de garder la dernière recherche de l'utilisateur.

![](https://raw.githubusercontent.com/TolsyLaurenceESIEA/gif/master/Researchactivity.gif)

### Autres fonctionnalités

* Les notifications push ont également été intégré dans le projet.
![](https://raw.githubusercontent.com/TolsyLaurenceESIEA/gif/master/notif.png)

* Vous consterez que ce projet comporte une architecture MVC, ainsi que l'utilisation du gitflow (branche master et developer)



