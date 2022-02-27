# S3projetTut
AlgoPars - Projet tutoré du semestre 3

AlgoPars est un interpréteur de pseudo-code écrit en Java dans le but fictif de vendre un outil pour les enseignants du département informatique de l'université.
Projet réalisé en 2 semaines.

**Comment ça marche ?**

Tout d'abord, pensez à importer le dossier `src/AlgoPars/ressources` contenant les .jar dans les variables d'environnement de votre ordinateur.
Compiler manuellement : `javac "@compile.list"`


### Windows :
- Se placer dans le dossier /bin puis exécuter `cd .. && javac "@compile.list" & cd bin` pour compiler.
- Exécuter le programme `java AlgoPars.Main` (toujours depuis le dossier /bin) et placer un paramètre comme `Exemple 1` pour Exemple1.algo
- All in one (toujours depuis le dossier /bin) : `cd .. && javac "@compile.list" & cd bin && java AlgoPars.Main Exemple1`

### IDE :
Ce projet est basé sur Eclipse, vous n'aurez donc pas de mal à l'importer en tant que projet, que ce soit dans Eclipse, VS Code (avec modules Java) ou IntelliJ IDEA, vous pourrez modifier et compiler le code de manière très simple.

(Attention à l'UTF-8 cependant sur IDE)

Code spaghetti au niveau de l'Interpréteur par manque de temps mais tranquille. :3
(Peut-être qu'un jour je reprendrai le projet pour améliorer tout ça)
