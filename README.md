# Lab7

L'application créee permet de

1-convertir un text introduit par l'utilisateur en voix; là on a appelée la fonction Texttospeech -installée déjà sur Android studio-, initialisé un champ pour introduir le text et un bouton pour éxecuter la conversion.
En initialisant texttosp et ajoutant des différentes conditions liées à son statut et on a personnalisé le pitch et la vitesse de parole.
On a crée Speak(); la procéédure permettant d'effectuer la conversion qui a pris le text du champs.
Finalement; on ajoute chaque variable à son rôle dédiée, bouton à btnspeak et edt Speak au champ.

2-Convertir le son en text;
On a ajouté le champs sur lequel on va visualiser le resultat final, aussi le bouton pour initialisé cette conversion.
On a importé la librairie intent avec laquelle on a codé un systém de reconnaissance vocale entrée par l'utilisateur.
Egalement on ajouté à intent le pack de la langue, le model, tout en mettant une condition si l'utilisateur parle une autre langue ou quelque chose non-compréhensible.


La procédure Ondestroy est responsable sur la fermuture de l'application

Vidéo pour la démo: https://streamable.com/rrg2zq
