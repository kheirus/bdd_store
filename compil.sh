export CLASSPATH=postgresql-9.2-1002.jdbc4.jar:$CLASSPATH

javac Interface.java

echo "
"
echo "----------------------------------------------------"
echo "             SUPPRESSION DES TABLE EXISTANTES"
echo "----------------------------------------------------"
echo "
"
psql prjbd6 -f drop.sql -q

echo "
"
echo "----------------------------------------------------"
echo "               SUPPRESSION EFFECTUÉE     "
echo "----------------------------------------------------"
echo "
"
read -p "Voulez vous créer les tables ? Appuyez sur entrée pour confirmer "

echo "
"
echo "----------------------------------------------------"
echo "            CREATION DE LA BASE                      "
echo "----------------------------------------------------"
echo "
"
psql prjbd6 -f create.sql -q

echo "
"
echo "----------------------------------------------------"
echo "            CREATION EFFECTUÉE                      "
echo "----------------------------------------------------"
echo "
"

read -p "Voulez vous inserer les données ? Appuyez sur entrée pour confirmer "


psql prjbd6 -f insertion.sql -q

echo "
"
echo "----------------------------------------------------"
echo "             INSERTION DES DONNÉES EFFECTUÉE"
echo "----------------------------------------------------"
echo "
"

read -p "Pour lancer l'interface appuyez sur entrée "
echo"
"
clear

java Interface


