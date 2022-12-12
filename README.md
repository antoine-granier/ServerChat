# Chat group server

## Lancement

> Pour commencer, il est necessaire de lancer la classe Server qui va créer un ServerSocket tournant sur le port 2000 en local.
> Une fois cela effectué, il suffit de lancer plusieurs instances de la classe Client pour ajouter une nouvelle personne au chat de groupe.
> Ainsi, lorsqu'une des personnes enverra une message, les autres le recevront avec le nom de la personne qui l'a envoyé. (Au lancement d'un client, un nom lui est demandé)

## Serveur

> Le serveur attend que des clients viennent se connecter. A chaque nouvelle connexion, le serveur attribut au client un thread (classe Connection) et le stock dans une liste.

## Client

> La classe client initialise la connexion avec le serveur, et attend les messages de l'utilisateur pour les envoyer au serveur. Chaque client possède un thread s'occupant de lire les messages envoyés par le serveur.
