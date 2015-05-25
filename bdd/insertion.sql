insert into Users values (449, 'Croft', 'Lara','lara.croft@golden.com',70,67,'Pomeranie', 'G','lara','lara');

\copy users from users.txt delimiter '|'
\copy fabriquant from fab.txt delimiter '|'
\copy apps from apps.txt delimiter '|'
\copy os from os.txt delimiter '|'
\copy info_paiement from info.txt delimiter '|'
\copy peripherique from periph.txt delimiter '|'
\copy droit from droit.txt delimiter '|'
\copy mot_cle from mot.txt delimiter '|'
\copy a_mot_cle from a.txt delimiter '|'
\copy demande from demande.txt delimiter '|'
\copy installe from installe.txt delimiter '|'
\copy maj from maj.txt delimiter '|'


SELECT setval('id_periph',600);
SELECT setval('id_apps',600);
SELECT setval('id_maj',600);



