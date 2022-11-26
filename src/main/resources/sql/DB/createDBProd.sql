CREATE DATABASE "FiguriProd"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;


CREATE TABLE IF NOT EXISTS "ESTADOS_PUBLICACIONES"
(
    id_estado_publicacion text NOT NULL,
    PRIMARY KEY (id_estado_publicacion)
);

CREATE TABLE IF NOT EXISTS "ESTADOS_FIGURITAS"
(
    id_estado_figurita text NOT NULL,
    PRIMARY KEY (id_estado_figurita)
);

CREATE TABLE IF NOT EXISTS "ESTADOS_OFERTAS"
(
    id_estado_ofertas text NOT NULL,
    PRIMARY KEY (id_estado_ofertas)
);

CREATE TABLE IF NOT EXISTS "USUARIOS"
(
    ci integer NOT NULL,
    nombre text NOT NULL,
    apellido text NOT NULL,
    telefono integer NOT NULL,
    contrasenia text NOT NULL,
    PRIMARY KEY ("ci")
);

CREATE TABLE IF NOT EXISTS "FIGURITAS_EXISTENTES"
(
    id_figurita_existente integer NOT NULL,
    tipo text NOT NULL,
    descripcion text NOT NULL,
	pais text,
    PRIMARY KEY (id_figurita_existente)
);

CREATE TABLE IF NOT EXISTS "FIGURITAS_USUARIOS"
(
    id_figurita_usuario text NOT NULL,
    id_figurita_existente integer NOT NULL,
    id_estado_figurita  text NOT NULL,
    id_usuario integer NOT NULL,
    PRIMARY KEY (id_figurita_usuario),
    FOREIGN KEY (id_figurita_existente) REFERENCES "FIGURITAS_EXISTENTES"(id_figurita_existente),
    FOREIGN KEY (id_estado_figurita) REFERENCES "ESTADOS_FIGURITAS"(id_estado_figurita),
    FOREIGN KEY (id_usuario) REFERENCES "USUARIOS"(ci)
);

CREATE TABLE IF NOT EXISTS "PUBLICACIONES"
(
    id_publicacion text NOT NULL,
    id_figurita_usuario text NOT NULL,
    id_estado_publicacion text NOT NULL,
    id_figurita_existente_1 integer,
    id_figurita_existente_2 integer,
    id_figurita_existente_3 integer,
    fecha date NOT NULL,
    FOREIGN KEY (id_figurita_usuario) REFERENCES "FIGURITAS_USUARIOS"(id_figurita_usuario),
    FOREIGN KEY (id_estado_publicacion) REFERENCES "ESTADOS_PUBLICACIONES"(id_estado_publicacion),
    FOREIGN KEY (id_figurita_existente_1) REFERENCES "FIGURITAS_EXISTENTES"(id_figurita_existente),
    FOREIGN KEY (id_figurita_existente_2) REFERENCES "FIGURITAS_EXISTENTES"(id_figurita_existente),
    FOREIGN KEY (id_figurita_existente_3) REFERENCES "FIGURITAS_EXISTENTES"(id_figurita_existente),
    PRIMARY KEY (id_publicacion)
);

CREATE TABLE IF NOT EXISTS "OFERTAS"
(
    id_oferta text NOT NULL,
    id_publicacion text NOT NULL,
    id_publicacion1 text NOT NULL, 
    id_publicacion2 text, 
    id_publicacion3 text,
    fecha date NOT NULL,
    FOREIGN KEY (id_publicacion) REFERENCES "PUBLICACIONES"(id_publicacion),
    FOREIGN KEY (id_publicacion1) REFERENCES "PUBLICACIONES"(id_publicacion),
    FOREIGN KEY (id_publicacion2) REFERENCES "PUBLICACIONES"(id_publicacion),
    FOREIGN KEY (id_publicacion3) REFERENCES "PUBLICACIONES"(id_publicacion),
    PRIMARY KEY (id_oferta)
);


ALTER TABLE IF EXISTS "USUARIOS"
    OWNER to postgres;
ALTER TABLE IF EXISTS "FIGURITAS_USUARIOS"
    OWNER to postgres;
ALTER TABLE IF EXISTS "FIGURITAS_EXISTENTES"
    OWNER to postgres;
ALTER TABLE IF EXISTS "PUBLICACIONES"
    OWNER to postgres;
ALTER TABLE IF EXISTS "OFERTAS"
    OWNER to postgres;
ALTER TABLE IF EXISTS "ESTADOS_PUBLICACIONES"
    OWNER to postgres;
ALTER TABLE IF EXISTS "ESTADOS_FIGURITAS"
    OWNER to postgres;
ALTER TABLE IF EXISTS "ESTADOS_OFERTAS"
    OWNER to postgres;

INSERT INTO "ESTADOS_PUBLICACIONES"(id_estado_publicacion)
VALUES  ('Activa'),
        ('Inactiva'),
        ('Finalizada'),
        ('Caducada');

INSERT INTO "ESTADOS_FIGURITAS"(id_estado_figurita)
VALUES  ('Excelente'),
        ('Bueno'),
        ('Malo'),
        ('Pésimo');

INSERT INTO "ESTADOS_OFERTAS"(id_estado_ofertas)
VALUES  ('Rechazada'),
        ('Aceptada'),
        ('Ofertada'),
        ('Contraofertada');

INSERT INTO "FIGURITAS_EXISTENTES"(id_figurita_existente, tipo, descripcion, pais)
VALUES  (1,'Especial','FIFA', NULL),
        (2,'Especial','Logo Panini', NULL),
        (3,'Especial','Trophy 2/2', NULL),
        (4,'Especial','Trophy 1/2', NULL),
        (5,'Especial','Mascot 2/2', NULL),
        (6,'Especial','Mascot 1/2', NULL),
        (7,'Especial','Logo 2/2', NULL),
        (8,'Especial','Logo 1/2', NULL),
        (9,'Especial','Al Janoub Stadium', NULL),
        (10,'Especial','Ahmad Bin Ali Stadium', NULL),
        (11,'Especial','Education City Stadium', NULL),
        (12,'Especial','Al Thumama Stadium', NULL),
        (13,'Especial','Stadium 974', NULL),
        (14,'Especial','Khalifa International Stadium', NULL),
        (15,'Especial','Al Bayt Stadium Interior', NULL),
        (16,'Especial','Al Bayt Stadium Exterior', NULL),
        (17,'Especial','Lusail Stadium Interior', NULL),
        (18,'Especial','Lusail Stadium Exterior', NULL),
        (19,'Cuadro','Team Photo','Catar'),
        (20,'Especial','Official Ball', NULL),
        (21,'Jugador','Saad Al Sheeb','Catar'),
        (22,'Escudo','Emblem','Catar'),
        (23,'Jugador','Homam Ahmed','Catar'),
        (24,'Jugador','Meshaal Barsham','Catar'),
        (25,'Jugador','Abdulkarim Hassan','Catar'),
        (26,'Jugador','Bassam Alrawi','Catar'),
        (27,'Jugador','Boualem Khoukhi','Catar'),
        (28,'Jugador','Musaab Khidir','Catar'),
        (29,'Jugador','Tarek Salman','Catar'),
        (30,'Jugador','Pedro Miguel','Catar'),
        (31,'Jugador','Abdulaziz Hatem','Catar'),
        (32,'Jugador','Karim Boudiaf','Catar'),
        (33,'Jugador','Yousuf Abdurisag','Catar'),
        (34,'Jugador','Assim Omer Madibo','Catar'),
        (35,'Jugador','Ahmad Alaaeldin','Catar'),
        (36,'Jugador','Akram Hassan Afif','Catar'),
        (37,'Jugador','Almoez Ali','Catar'),
        (38,'Jugador','Hasan Al-Haydos','Catar'),
        (39,'Cuadro','Team Photo','Ecuador'),
        (40,'Jugador','Mohammed Muntari','Catar'),
        (41,'Jugador','Hernán Galíndez','Ecuador'),
        (42,'Escudo','Emblem','Ecuador'),
        (43,'Jugador','Robert Arboleda','Ecuador'),
        (44,'Jugador','Alexander Domínguez','Ecuador'),
        (45,'Jugador','Pervis Estupiñán','Ecuador'),
        (46,'Jugador','Byron Castillo','Ecuador'),
        (47,'Jugador','Ángelo Preciado','Ecuador'),
        (48,'Jugador','Piero Hincapié','Ecuador'),
        (49,'Jugador','Moisés Caicedo','Ecuador'),
        (50,'Jugador','Félix Torres','Ecuador'),
        (51,'Jugador','Carlos Gruezo','Ecuador'),
        (52,'Jugador','Alan Franco','Ecuador'),
        (53,'Jugador','Jeremy Sarmiento','Ecuador'),
        (54,'Jugador','Jhegson Méndez','Ecuador'),
        (55,'Jugador','Ángel Mena','Ecuador'),
        (56,'Jugador','Michael Estrada','Ecuador'),
        (57,'Jugador','Ayrton Preciado','Ecuador'),
        (58,'Jugador','Gonzalo Plata','Ecuador'),
        (59,'Cuadro','Team Photo','Senegal'),
        (60,'Jugador','Enner Valencia','Ecuador'),
        (61,'Jugador','Édouard Mendy','Senegal'),
        (62,'Escudo','Emblem','Senegal'),
        (63,'Jugador','Saliou Ciss','Senegal'),
        (64,'Jugador','Alfred Gomis','Senegal'),
        (65,'Jugador','Abdou Diallo','Senegal'),
        (66,'Jugador','Pape Abou Cissé','Senegal'),
        (67,'Jugador','Ibrahima Mbaye','Senegal'),
        (68,'Jugador','Kalidou Koulibaly','Senegal'),
        (69,'Jugador','Krépin Diatta','Senegal'),
        (70,'Jugador','Bouna Sarr','Senegal'),
        (71,'Jugador','Pape Gueye','Senegal'),
        (72,'Jugador','Idrissa Gueye','Senegal'),
        (73,'Jugador','Nampalys Mendy','Senegal'),
        (74,'Jugador','Cheikhou Kouyaté','Senegal'),
        (75,'Jugador','Famara Diédhiou','Senegal'),
        (76,'Jugador','Boulaye Dia','Senegal'),
        (77,'Jugador','Sadio Mané','Senegal'),
        (78,'Jugador','Bamba Dieng','Senegal'),
        (79,'Cuadro','Team Photo','Holanda'),
        (80,'Jugador','Ismaïla Sarr','Senegal'),
        (81,'Jugador','Justin Bijlow','Holanda'),
        (82,'Escudo','Emblem','Holanda'),
        (83,'Jugador','Daley Blind','Holanda'),
        (84,'Jugador','Jasper Cillessen','Holanda'),
        (85,'Jugador','Stefan de Vrij','Holanda'),
        (86,'Jugador','Matthijs de Ligt','Holanda'),
        (87,'Jugador','Virgil van Dijk','Holanda'),
        (88,'Jugador','Denzel Dumfries','Holanda'),
        (89,'Jugador','Frenkie de Jong','Holanda'),
        (90,'Jugador','Steven Berghuis','Holanda'),
        (91,'Jugador','Davy Klaassen','Holanda'),
        (92,'Jugador','Ryan Gravenberch','Holanda'),
        (93,'Jugador','Georginio Wijnaldum','Holanda'),
        (94,'Jugador','Teun Koopmeiners','Holanda'),
        (95,'Jugador','Arnaut Danjuma','Holanda'),
        (96,'Jugador','Steven Bergwijn','Holanda'),
        (97,'Jugador','Cody Gakpo','Holanda'),
        (98,'Jugador','Memphis Depay','Holanda'),
        (99,'Cuadro','Team Photo','Inglaterra'),
        (100,'Jugador','Donyell Malen','Holanda'),
        (101,'Jugador','Jordan Pickford','Inglaterra'),
        (102,'Escudo','Emblem','Inglaterra'),
        (103,'Jugador','Trent Alexander-Arnold','Inglaterra'),
        (104,'Jugador','Aaron Ramsdale','Inglaterra'),
        (105,'Jugador','Harry Maguire','Inglaterra'),
        (106,'Jugador','Conor Coady','Inglaterra'),
        (107,'Jugador','John Stones','Inglaterra'),
        (108,'Jugador','Luke Shaw','Inglaterra'),
        (109,'Jugador','Jude Bellingham','Inglaterra'),
        (110,'Jugador','Kyle Walker','Inglaterra'),
        (111,'Jugador','Jordan Henderson','Inglaterra'),
        (112,'Jugador','Jack Grealish','Inglaterra'),
        (113,'Jugador','Kalvin Phillips','Inglaterra'),
        (114,'Jugador','Mason Mount','Inglaterra'),
        (115,'Jugador','Phil Foden','Inglaterra'),
        (116,'Jugador','Declan Rice','Inglaterra'),
        (117,'Jugador','Bukayo Saka','Inglaterra'),
        (118,'Jugador','Harry Kane','Inglaterra'),
        (119,'Cuadro','Team Photo','Iran'),
        (120,'Jugador','Raheem Sterling','Inglaterra'),
        (121,'Jugador','Amir Abedzadeh','Iran'),
        (122,'Escudo','Emblem','Iran'),
        (123,'Jugador','Ehsan Hajsafi','Iran'),
        (124,'Jugador','Alireza Beiranvand','Iran'),
        (125,'Jugador','Hossein Kanaani','Iran'),
        (126,'Jugador','Majid Hosseini','Iran'),
        (127,'Jugador','Milad Mohammadi','Iran'),
        (128,'Jugador','Shoja Khalilzadeh','Iran'),
        (129,'Jugador','Omid Noorafkan','Iran'),
        (130,'Jugador','Sadegh Moharrami','Iran'),
        (131,'Jugador','Saeid Ezatolahi','Iran'),
        (132,'Jugador','Vahid Amiri','Iran'),
        (133,'Jugador','Alireza Jahanbakhsh','Iran'),
        (134,'Jugador','Ali Gholizadeh','Iran'),
        (135,'Jugador','Karim Ansarifard','Iran'),
        (136,'Jugador','Ahmad Nourollahi','Iran'),
        (137,'Jugador','Saman Ghoddos','Iran'),
        (138,'Jugador','Sardar Azmoun','Iran'),
        (139,'Cuadro','Team Photo','Estados Unidos'),
        (140,'Jugador','Mehdi Taremi','Iran'),
        (141,'Jugador','Matt Turner','Estados Unidos'),
        (142,'Escudo','Emblem','Estados Unidos'),
        (143,'Jugador','Sergiño Dest','Estados Unidos'),
        (144,'Jugador','Zack Steffen','Estados Unidos'),
        (145,'Jugador','Chris Richards','Estados Unidos'),
        (146,'Jugador','Aaron Long','Estados Unidos'),
        (147,'Jugador','DeAndre Yedlin','Estados Unidos'),
        (148,'Jugador','Antonee Robinson','Estados Unidos'),
        (149,'Jugador','Brenden Aaronson','Estados Unidos'),
        (150,'Jugador','Walker Zimmerman','Estados Unidos'),
        (151,'Jugador','Tyler Adams','Estados Unidos'),
        (152,'Jugador','Kellyn Acosta','Estados Unidos'),
        (153,'Jugador','Yunus Musah','Estados Unidos'),
        (154,'Jugador','Weston McKennie','Estados Unidos'),
        (155,'Jugador','Ricardo Pepi','Estados Unidos'),
        (156,'Jugador','Jesús Ferreira','Estados Unidos'),
        (157,'Jugador','Giovanni Reyna','Estados Unidos'),
        (158,'Jugador','Christian Pulisic','Estados Unidos'),
        (159,'Cuadro','Team Photo','Gales'),
        (160,'Jugador','Timothy Weah','Estados Unidos'),
        (161,'Jugador','Danny Ward','Gales'),
        (162,'Escudo','Emblem','Gales'),
        (163,'Jugador','Ethan Ampadu','Gales'),
        (164,'Jugador','Wayne Hennessey','Gales'),
        (165,'Jugador','Chris Gunter','Gales'),
        (166,'Jugador','Ben Davies','Gales'),
        (167,'Jugador','Connor Roberts','Gales'),
        (168,'Jugador','Chris Mepham','Gales'),
        (169,'Jugador','Neco Williams','Gales'),
        (170,'Jugador','Joe Rodon','Gales'),
        (171,'Jugador','Joe Morrell','Gales'),
        (172,'Jugador','Joe Allen','Gales'),
        (173,'Jugador','Jonathan Williams','Gales'),
        (174,'Jugador','Aaron Ramsey','Gales'),
        (175,'Jugador','Gareth Bale','Gales'),
        (176,'Jugador','Harry Wilson','Gales'),
        (177,'Jugador','Brennan Johnson','Gales'),
        (178,'Jugador','Daniel James','Gales'),
        (179,'Cuadro','Team Photo','Argentina'),
        (180,'Jugador','Kieffer Moore','Gales'),
        (181,'Jugador','Emiliano Martínez','Argentina'),
        (182,'Escudo','Emblem','Argentina'),
        (183,'Jugador','Marcos Acuña','Argentina'),
        (184,'Jugador','Franco Armani','Argentina'),
        (185,'Jugador','Nicolás Otamendi','Argentina'),
        (186,'Jugador','Nahuel Molina','Argentina'),
        (187,'Jugador','Cristian Romero','Argentina'),
        (188,'Jugador','Germán Pezzella','Argentina'),
        (189,'Jugador','Ángel Di María','Argentina'),
        (190,'Jugador','Rodrigo De Paul','Argentina'),
        (191,'Jugador','Leandro Paredes','Argentina'),
        (192,'Jugador','Giovani Lo Celso','Argentina'),
        (193,'Jugador','Julián Álvarez','Argentina'),
        (194,'Jugador','Guido Rodríguez','Argentina'),
        (195,'Jugador','Alejandro Gómez','Argentina'),
        (196,'Jugador','Joaquín Correa','Argentina'),
        (197,'Jugador','Lautaro Martínez','Argentina'),
        (198,'Jugador','Nicolás González','Argentina'),
        (199,'Cuadro','Team Photo','Arabia Saudita'),
        (200,'Jugador','Lionel Messi','Argentina'),
        (201,'Jugador','Mohammed Al-Owais','Arabia Saudita'),
        (202,'Escudo','Emblem','Arabia Saudita'),
        (203,'Jugador','Abdulelah Al-Amri','Arabia Saudita'),
        (204,'Jugador','Mohammed Al-Rubaie','Arabia Saudita'),
        (205,'Jugador','Mohammed Al-Burayk','Arabia Saudita'),
        (206,'Jugador','Ali Al-Boleahi','Arabia Saudita'),
        (207,'Jugador','Yasser Al-Shahrani','Arabia Saudita'),
        (208,'Jugador','Sultan Al-Ghannam','Arabia Saudita'),
        (209,'Jugador','Abdullah Madu','Arabia Saudita'),
        (210,'Jugador','Hassan Al-Tambakti','Arabia Saudita'),
        (211,'Jugador','Abdulelah Al-Malki','Arabia Saudita'),
        (212,'Jugador','Salman Al-Faraj','Arabia Saudita'),
        (213,'Jugador','Hattan Bahebri','Arabia Saudita'),
        (214,'Jugador','Sami Al-Najei','Arabia Saudita'),
        (215,'Jugador','Abdullah Otayf','Arabia Saudita'),
        (216,'Jugador','Mohamed Kanno','Arabia Saudita'),
        (217,'Jugador','Salem Al-Dawsari','Arabia Saudita'),
        (218,'Jugador','Firas Al-Buraikan','Arabia Saudita'),
        (219,'Cuadro','Team Photo','México'),
        (220,'Jugador','Khalid Al-Ghannam','Arabia Saudita'),
        (221,'Jugador','Guillermo Ochoa','México'),
        (222,'Escudo','Emblem','México'),
        (223,'Jugador','Néstor Araújo','México'),
        (224,'Jugador','Alfredo Talavera','México'),
        (225,'Jugador','César Montes','México'),
        (226,'Jugador','Jesús Gallardo','México'),
        (227,'Jugador','Luis Romo','México'),
        (228,'Jugador','Héctor Moreno','México'),
        (229,'Jugador','Edson Álvarez','México'),
        (230,'Jugador','Jorge Sánchez','México'),
        (231,'Jugador','Andrés Guardado','México'),
        (232,'Jugador','Jesús Manuel Corona','México'),
        (233,'Jugador','Héctor Herrera','México'),
        (234,'Jugador','Érick Gutiérrez','México'),
        (235,'Jugador','Carlos Rodríguez','México'),
        (236,'Jugador','Diego Lainez','México'),
        (237,'Jugador','Raúl Jiménez','México'),
        (238,'Jugador','Rogelio Funes Mori','México'),
        (239,'Cuadro','Team Photo','Polonia'),
        (240,'Jugador','Hirving Lozano','México'),
        (241,'Jugador','Wojciech Szczęsny','Polonia'),
        (242,'Escudo','Emblem','Polonia'),
        (243,'Jugador','Jan Bednarek','Polonia'),
        (244,'Jugador','Łukasz Skorupski','Polonia'),
        (245,'Jugador','Matty Cash','Polonia'),
        (246,'Jugador','Bartosz Bereszyński','Polonia'),
        (247,'Jugador','Tymoteusz Puchacz','Polonia'),
        (248,'Jugador','Kamil Glik','Polonia'),
        (249,'Jugador','Kamil Jóźwiak','Polonia'),
        (250,'Jugador','Maciej Rybus','Polonia'),
        (251,'Jugador','Grzegorz Krychowiak','Polonia'),
        (252,'Jugador','Mateusz Klich','Polonia'),
        (253,'Jugador','Sebastian Szymański','Polonia'),
        (254,'Jugador','Jakub Moder','Polonia'),
        (255,'Jugador','Robert Lewandowski','Polonia'),
        (256,'Jugador','Piotr Zieliński','Polonia'),
        (257,'Jugador','Krzysztof Piątek','Polonia'),
        (258,'Jugador','Arkadiusz Milik','Polonia'),
        (259,'Cuadro','Team Photo','Francia'),
        (260,'Jugador','Karol Świderski','Polonia'),
        (261,'Jugador','Hugo Lloris','Francia'),
        (262,'Escudo','Emblem','Francia'),
        (263,'Jugador','Lucas Hernández','Francia'),
        (264,'Jugador','Mike Maignan','Francia'),
        (265,'Jugador','Presnel Kimpembe','Francia'),
        (266,'Jugador','Theo Hernández','Francia'),
        (267,'Jugador','Benjamin Pavard','Francia'),
        (268,'Jugador','Jules Koundé','Francia'),
        (269,'Jugador','N’Golo Kanté','Francia'),
        (270,'Jugador','Raphaël Varane','Francia'),
        (271,'Jugador','Adrien Rabiot','Francia'),
        (272,'Jugador','Paul Pogba','Francia'),
        (273,'Jugador','Wissam Ben Yedder','Francia'),
        (274,'Jugador','Aurélien Tchouaméni','Francia'),
        (275,'Jugador','Kingsley Coman','Francia'),
        (276,'Jugador','Karim Benzema','Francia'),
        (277,'Jugador','Kylian Mbappé','Francia'),
        (278,'Jugador','Antoine Griezmann','Francia'),
        (279,'Cuadro','Team Photo','Australia'),
        (280,'Jugador','Christopher Nkunku','Francia'),
        (281,'Jugador','Mathew Ryan','Australia'),
        (282,'Escudo','Emblem','Australia'),
        (283,'Jugador','Aziz Behich','Australia'),
        (284,'Jugador','Andrew Redmayne','Australia'),
        (285,'Jugador','Rhyan Grant','Australia'),
        (286,'Jugador','Miloš Degenek','Australia'),
        (287,'Jugador','Trent Sainsbury','Australia'),
        (288,'Jugador','Joel King','Australia'),
        (289,'Jugador','Ajdin Hrustić','Australia'),
        (290,'Jugador','Harry Souttar','Australia'),
        (291,'Jugador','James Jeggo','Australia'),
        (292,'Jugador','Jackson Irvine','Australia'),
        (293,'Jugador','Aaron Mooy','Australia'),
        (294,'Jugador','Awer Mabil','Australia'),
        (295,'Jugador','Mitchell Duke','Australia'),
        (296,'Jugador','Martin Boyle','Australia'),
        (297,'Jugador','Mathew Leckie','Australia'),
        (298,'Jugador','Craig Goodwin','Australia'),
        (299,'Cuadro','Team Photo','Dinamarca'),
        (300,'Jugador','Adam Taggart','Australia'),
        (301,'Jugador','Kasper Schmeichel','Dinamarca'),
        (302,'Escudo','Emblem','Dinamarca'),
        (303,'Jugador','Andreas Christensen','Dinamarca'),
        (304,'Jugador','Frederik Rønnow','Dinamarca'),
        (305,'Jugador','Joakim Mæhle','Dinamarca'),
        (306,'Jugador','Simon Kjær','Dinamarca'),
        (307,'Jugador','Jannik Vestergaard','Dinamarca'),
        (308,'Jugador','Jens Stryger Larsen','Dinamarca'),
        (309,'Jugador','Thomas Delaney','Dinamarca'),
        (310,'Jugador','Mikkel Damsgaard','Dinamarca'),
        (311,'Jugador','Pierre Emile Højbjerg','Dinamarca'),
        (312,'Jugador','Christian Eriksen','Dinamarca'),
        (313,'Jugador','Daniel Wass','Dinamarca'),
        (314,'Jugador','Christian Nørgaard','Dinamarca'),
        (315,'Jugador','Kasper Dolberg','Dinamarca'),
        (316,'Jugador','Martin Braithwaite','Dinamarca'),
        (317,'Jugador','Andreas Skov Olsen','Dinamarca'),
        (318,'Jugador','Yussuf Poulsen','Dinamarca'),
        (319,'Cuadro','Team Photo','Túnez'),
        (320,'Jugador','Jonas Wind','Dinamarca'),
        (321,'Jugador','Bechir Ben Saïd','Túnez'),
        (322,'Escudo','Emblem','Túnez'),
        (323,'Jugador','Dylan Bronn','Túnez'),
        (324,'Jugador','Farouk Ben Mustapha','Túnez'),
        (325,'Jugador','Bilel Ifa','Túnez'),
        (326,'Jugador','Mohamed Dräger','Túnez'),
        (327,'Jugador','Hamza Mathlouthi','Túnez'),
        (328,'Jugador','Ali Maâloul','Túnez'),
        (329,'Jugador','Montassar Talbi','Túnez'),
        (330,'Jugador','Yassine Meriah','Túnez'),
        (331,'Jugador','Wahbi Khazri','Túnez'),
        (332,'Jugador','Mohamed Ali Ben Romdhane','Túnez'),
        (333,'Jugador','Ferjani Sassi','Túnez'),
        (334,'Jugador','Aïssa Laïdouni','Túnez'),
        (335,'Jugador','Anis Slimane','Túnez'),
        (336,'Jugador','Ellyes Skhiri','Túnez'),
        (337,'Jugador','Youssef Msakni','Túnez'),
        (338,'Jugador','Seifeddine Jaziri','Túnez'),
        (339,'Cuadro','Team Photo','España'),
        (340,'Jugador','Naïm Sliti','Túnez'),
        (341,'Jugador','Unai Simón','España'),
        (342,'Escudo','Emblem','España'),
        (343,'Jugador','César Azpilicueta','España'),
        (344,'Jugador','Robert Sánchez','España'),
        (345,'Jugador','Jordi Alba','España'),
        (346,'Jugador','Eric García','España'),
        (347,'Jugador','Pau Torres','España'),
        (348,'Jugador','Aymeric Laporte','España'),
        (349,'Jugador','Koke','España'),
        (350,'Jugador','Gavi','España'),
        (351,'Jugador','Pedri','España'),
        (352,'Jugador','Marcos Llorente','España'),
        (353,'Jugador','Sergio Busquets','España'),
        (354,'Jugador','Rodri','España'),
        (355,'Jugador','Ansu Fati','España'),
        (356,'Jugador','Dani Olmo','España'),
        (357,'Jugador','Álvaro Morata','España'),
        (358,'Jugador','Ferran Torres','España'),
        (359,'Cuadro','Team Photo','Costa Rica'),
        (360,'Jugador','Pablo Sarabia','España'),
        (361,'Jugador','Keylor Navas','Costa Rica'),
        (362,'Escudo','Emblem','Costa Rica'),
        (363,'Jugador','Ricardo Blanco','Costa Rica'),
        (364,'Jugador','Leonel Moreira','Costa Rica'),
        (365,'Jugador','Óscar Duarte','Costa Rica'),
        (366,'Jugador','Francisco Calvo','Costa Rica'),
        (367,'Jugador','Bryan Oviedo','Costa Rica'),
        (368,'Jugador','Keysher Fuller','Costa Rica'),
        (369,'Jugador','Kendall Waston','Costa Rica'),
        (370,'Jugador','Juan Pablo Vargas','Costa Rica'),
        (371,'Jugador','Orlando Galo','Costa Rica'),
        (372,'Jugador','Celso Borges','Costa Rica'),
        (373,'Jugador','Yeltsin Tejeda','Costa Rica'),
        (374,'Jugador','Bryan Ruiz','Costa Rica'),
        (375,'Jugador','Joel Campbell','Costa Rica'),
        (376,'Jugador','Jewison Bennette','Costa Rica'),
        (377,'Jugador','Gerson Torres','Costa Rica'),
        (378,'Jugador','Anthony Contreras','Costa Rica'),
        (379,'Cuadro','Team Photo','Alemania'),
        (380,'Jugador','Johan Venegas','Costa Rica'),
        (381,'Jugador','Manuel Neuer','Alemania'),
        (382,'Escudo','Emblem','Alemania'),
        (383,'Jugador','Matthias Ginter','Alemania'),
        (384,'Jugador','Marc-André ter Stegen','Alemania'),
        (385,'Jugador','Thilo Kehrer','Alemania'),
        (386,'Jugador','Robin Gosens','Alemania'),
        (387,'Jugador','Antonio Rüdiger','Alemania'),
        (388,'Jugador','David Raum','Alemania'),
        (389,'Jugador','Leon Goretzka','Alemania'),
        (390,'Jugador','Niklas Süle','Alemania'),
        (391,'Jugador','Kai Havertz','Alemania'),
        (392,'Jugador','İlkay Gündoğan','Alemania'),
        (393,'Jugador','Joshua Kimmich','Alemania'),
        (394,'Jugador','Jonas Hofmann','Alemania'),
        (395,'Jugador','Thomas Müller','Alemania'),
        (396,'Jugador','Serge Gnabry','Alemania'),
        (397,'Jugador','Leroy Sané','Alemania'),
        (398,'Jugador','Marco Reus','Alemania'),
        (399,'Cuadro','Team Photo','Japón'),
        (400,'Jugador','Timo Werner','Alemania'),
        (401,'Jugador','Shuichi Gonda','Japón'),
        (402,'Escudo','Emblem','Japón'),
        (403,'Jugador','Yuto Nagatomo','Japón'),
        (404,'Jugador','Eiji Kawashima','Japón'),
        (405,'Jugador','Takehiro Tomiyasu','Japón'),
        (406,'Jugador','Yuta Nakayama','Japón'),
        (407,'Jugador','Maya Yoshida','Japón'),
        (408,'Jugador','Miki Yamane','Japón'),
        (409,'Jugador','Genki Haraguchi','Japón'),
        (410,'Jugador','Wataru Endo','Japón'),
        (411,'Jugador','Gaku Shibasaki','Japón'),
        (412,'Jugador','Hidemasa Morita','Japón'),
        (413,'Jugador','Takuma Asano','Japón'),
        (414,'Jugador','Ao Tanaka','Japón'),
        (415,'Jugador','Junya Ito','Japón'),
        (416,'Jugador','Kyogo Furuhashi','Japón'),
        (417,'Jugador','Kaoru Mitoma','Japón'),
        (418,'Jugador','Takumi Minamino','Japón'),
        (419,'Cuadro','Team Photo','Bélgica'),
        (420,'Jugador','Yuya Osako','Japón'),
        (421,'Jugador','Thibaut Courtois','Bélgica'),
        (422,'Escudo','Emblem','Bélgica'),
        (423,'Jugador','Toby Alderweireld','Bélgica'),
        (424,'Jugador','Simon Mignolet','Bélgica'),
        (425,'Jugador','Jason Denayer','Bélgica'),
        (426,'Jugador','Timothy Castagne','Bélgica'),
        (427,'Jugador','Jan Vertonghen','Bélgica'),
        (428,'Jugador','Thomas Meunier','Bélgica'),
        (429,'Jugador','Kevin De Bruyne','Bélgica'),
        (430,'Jugador','Yannick Carrasco','Bélgica'),
        (431,'Jugador','Thorgan Hazard','Bélgica'),
        (432,'Jugador','Charles De Ketelaere','Bélgica'),
        (433,'Jugador','Hans Vanaken','Bélgica'),
        (434,'Jugador','Youri Tielemans','Bélgica'),
        (435,'Jugador','Jérémy Doku','Bélgica'),
        (436,'Jugador','Axel Witsel','Bélgica'),
        (437,'Jugador','Romelu Lukaku','Bélgica'),
        (438,'Jugador','Eden Hazard','Bélgica'),
        (439,'Cuadro','Team Photo','Canadá'),
        (440,'Jugador','Dries Mertens','Bélgica'),
        (441,'Jugador','Milan Borjan','Canadá'),
        (442,'Escudo','Emblem','Canadá'),
        (443,'Jugador','Samuel Adekugbe','Canadá'),
        (444,'Jugador','Maxime Crépeau','Canadá'),
        (445,'Jugador','Alistair Johnston','Canadá'),
        (446,'Jugador','Doneil Henry','Canadá'),
        (447,'Jugador','Kamal Miller','Canadá'),
        (448,'Jugador','Richie Laryea','Canadá'),
        (449,'Jugador','Tajon Buchanan','Canadá'),
        (450,'Jugador','Steven Vitória','Canadá'),
        (451,'Jugador','Stephen Eustáquio','Canadá'),
        (452,'Jugador','Alphonso Davies','Canadá'),
        (453,'Jugador','Mark-Anthony Kaye','Canadá'),
        (454,'Jugador','Atiba Hutchinson','Canadá'),
        (455,'Jugador','Samuel Piette','Canadá'),
        (456,'Jugador','Jonathan Osorio','Canadá'),
        (457,'Jugador','David Junior Hoilett','Canadá'),
        (458,'Jugador','Jonathan David','Canadá'),
        (459,'Cuadro','Team Photo','Marruecos'),
        (460,'Jugador','Cyle Larin','Canadá'),
        (461,'Jugador','Yassine Bounou','Marruecos'),
        (462,'Escudo','Emblem','Marruecos'),
        (463,'Jugador','Nayef Aguerd','Marruecos'),
        (464,'Jugador','Munir Mohamedi','Marruecos'),
        (465,'Jugador','Adam Masina','Marruecos'),
        (466,'Jugador','Achraf Hakimi','Marruecos'),
        (467,'Jugador','Romain Saïss','Marruecos'),
        (468,'Jugador','Samy Mmaee','Marruecos'),
        (469,'Jugador','Sofyan Amrabat','Marruecos'),
        (470,'Jugador','Selim Amallah','Marruecos'),
        (471,'Jugador','Ilias Chair','Marruecos'),
        (472,'Jugador','Aymen Barkok','Marruecos'),
        (473,'Jugador','Sofiane Boufal','Marruecos'),
        (474,'Jugador','Imrân Louza','Marruecos'),
        (475,'Jugador','Youssef En-Nesyri','Marruecos'),
        (476,'Jugador','Ayoub El Kaabi','Marruecos'),
        (477,'Jugador','Munir','Marruecos'),
        (478,'Jugador','Ryan Mmaee','Marruecos'),
        (479,'Cuadro','Team Photo','Croacia'),
        (480,'Jugador','Tarik Tissoudali','Marruecos'),
        (481,'Jugador','Dominik Livaković','Croacia'),
        (482,'Escudo','Emblem','Croacia'),
        (483,'Jugador','Duje Ćaleta-Car','Croacia'),
        (484,'Jugador','Ivica Ivušić','Croacia'),
        (485,'Jugador','Josip Juranović','Croacia'),
        (486,'Jugador','Joško Gvardiol','Croacia'),
        (487,'Jugador','Borna Sosa','Croacia'),
        (488,'Jugador','Dejan Lovren','Croacia'),
        (489,'Jugador','Marcelo Brozović','Croacia'),
        (490,'Jugador','Domagoj Vida','Croacia'),
        (491,'Jugador','Luka Modrić','Croacia'),
        (492,'Jugador','Mateo Kovačić','Croacia'),
        (493,'Jugador','Ivan Perišić','Croacia'),
        (494,'Jugador','Mario Pašalić','Croacia'),
        (495,'Jugador','Josip Brekalo','Croacia'),
        (496,'Jugador','Nikola Vlašić','Croacia'),
        (497,'Jugador','Marko Livaja','Croacia'),
        (498,'Jugador','Andrej Kramarić','Croacia'),
        (499,'Cuadro','Team Photo','Brasil'),
        (500,'Jugador','Mislav Oršić','Croacia'),
        (501,'Jugador','Alisson','Brasil'),
        (502,'Escudo','Emblem','Brasil'),
        (503,'Jugador','Alex Sandro','Brasil'),
        (504,'Jugador','Ederson','Brasil'),
        (505,'Jugador','Éder Militão','Brasil'),
        (506,'Jugador','Danilo','Brasil'),
        (507,'Jugador','Thiago Silva','Brasil'),
        (508,'Jugador','Marquinhos','Brasil'),
        (509,'Jugador','Philippe Coutinho','Brasil'),
        (510,'Jugador','Casemiro','Brasil'),
        (511,'Jugador','Fred','Brasil'),
        (512,'Jugador','Fabinho','Brasil'),
        (513,'Jugador','Antony','Brasil'),
        (514,'Jugador','Lucas Paquetá','Brasil'),
        (515,'Jugador','Neymar Jr','Brasil'),
        (516,'Jugador','Gabriel Jesus','Brasil'),
        (517,'Jugador','Richarlison','Brasil'),
        (518,'Jugador','Raphinha','Brasil'),
        (519,'Cuadro','Team Photo','Serbia'),
        (520,'Jugador','Vinícius Jr','Brasil'),
        (521,'Jugador','Predrag Rajković','Serbia'),
        (522,'Escudo','Emblem','Serbia'),
        (523,'Jugador','Nikola Milenković','Serbia'),
        (524,'Jugador','Vanja Milinković-Savić','Serbia'),
        (525,'Jugador','Miloš Veljković','Serbia'),
        (526,'Jugador','Strahinja Pavlović','Serbia'),
        (527,'Jugador','Nemanja Gudelj','Serbia'),
        (528,'Jugador','Filip Đuričić','Serbia'),
        (529,'Jugador','Darko Lazović','Serbia'),
        (530,'Jugador','Filip Kostić','Serbia'),
        (531,'Jugador','Nemanja Maksimović','Serbia'),
        (532,'Jugador','Saša Lukić','Serbia'),
        (533,'Jugador','Nemanja Radonjić','Serbia'),
        (534,'Jugador','Sergej Milinković-Savić','Serbia'),
        (535,'Jugador','Luka Jović','Serbia'),
        (536,'Jugador','Andrija Živković','Serbia'),
        (537,'Jugador','Dušan Tadić','Serbia'),
        (538,'Jugador','Aleksandar Mitrović','Serbia'),
        (539,'Cuadro','Team Photo','Suiza'),
        (540,'Jugador','Dušan Vlahović','Serbia'),
        (541,'Jugador','Yann Sommer','Suiza'),
        (542,'Escudo','Emblem','Suiza'),
        (543,'Jugador','Manuel Akanji','Suiza'),
        (544,'Jugador','Gregor Kobel','Suiza'),
        (545,'Jugador','Kevin Mbabu','Suiza'),
        (546,'Jugador','Nico Elvedi','Suiza'),
        (547,'Jugador','Fabian Schär','Suiza'),
        (548,'Jugador','Ricardo Rodríguez','Suiza'),
        (549,'Jugador','Remo Freuler','Suiza'),
        (550,'Jugador','Silvan Widmer','Suiza'),
        (551,'Jugador','Djibril Sow','Suiza'),
        (552,'Jugador','Xherdan Shaqiri','Suiza'),
        (553,'Jugador','Denis Zakaria','Suiza'),
        (554,'Jugador','Granit Xhaka','Suiza'),
        (555,'Jugador','Breel Embolo','Suiza'),
        (556,'Jugador','Steven Zuber','Suiza'),
        (557,'Jugador','Haris Seferović','Suiza'),
        (558,'Jugador','Noah Okafor','Suiza'),
        (559,'Cuadro','Team Photo','Cameroon'),
        (560,'Jugador','Ruben Vargas','Suiza'),
        (561,'Jugador','André Onana','Cameroon'),
        (562,'Escudo','Emblem','Cameroon'),
        (563,'Jugador','Jean-Charles Castelletto','Cameroon'),
        (564,'Jugador','Devis Epassy','Cameroon'),
        (565,'Jugador','Olivier Mbaizo','Cameroon'),
        (566,'Jugador','Collins Fai','Cameroon'),
        (567,'Jugador','Michael Ngadeu','Cameroon'),
        (568,'Jugador','Harold Moukoudi','Cameroon'),
        (569,'Jugador','Martin Hongla','Cameroon'),
        (570,'Jugador','Nouhou','Cameroon'),
        (571,'Jugador','James Léa Siliki','Cameroon'),
        (572,'Jugador','Pierre Kunde','Cameroon'),
        (573,'Jugador','André-Frank Zambo Anguissa','Cameroon'),
        (574,'Jugador','Samuel Oum Gouet','Cameroon'),
        (575,'Jugador','Stéphane Bahoken','Cameroon'),
        (576,'Jugador','Vincent Aboubakar','Cameroon'),
        (577,'Jugador','Nicolas Moumi Ngamaleu','Cameroon'),
        (578,'Jugador','Eric Maxim Choupo-Moting','Cameroon'),
        (579,'Cuadro','Team Photo','Portugal'),
        (580,'Jugador','Karl Toko Ekambi','Cameroon'),
        (581,'Jugador','Diogo Costa','Portugal'),
        (582,'Escudo','Emblem','Portugal'),
        (583,'Jugador','João Cancelo','Portugal'),
        (584,'Jugador','Rui Patrício','Portugal'),
        (585,'Jugador','Nuno Mendes','Portugal'),
        (586,'Jugador','José Fonte','Portugal'),
        (587,'Jugador','Raphaël Guerreiro','Portugal'),
        (588,'Jugador','Pepe','Portugal'),
        (589,'Jugador','Bernardo Silva','Portugal'),
        (590,'Jugador','Rúben Dias','Portugal'),
        (591,'Jugador','Danilo Pereira','Portugal'),
        (592,'Jugador','Bruno Fernandes','Portugal'),
        (593,'Jugador','Renato Sanches','Portugal'),
        (594,'Jugador','João Moutinho','Portugal'),
        (595,'Jugador','André Silva','Portugal'),
        (596,'Jugador','Rúben Neves','Portugal'),
        (597,'Jugador','Diogo Jota','Portugal'),
        (598,'Jugador','Cristiano Ronaldo','Portugal'),
        (599,'Cuadro','Team Photo','Ghana'),
        (600,'Jugador','Gonçalo Guedes','Portugal'),
        (601,'Jugador','Joe Wollacott','Ghana'),
        (602,'Escudo','Emblem','Ghana'),
        (603,'Jugador','Daniel Amartey','Ghana'),
        (604,'Jugador','Richard Ofori','Ghana'),
        (605,'Jugador','Alexander Djiku','Ghana'),
        (606,'Jugador','Abdul-Rahman Baba','Ghana'),
        (607,'Jugador','Jonathan Mensah','Ghana'),
        (608,'Jugador','Gideon Mensah','Ghana'),
        (609,'Jugador','Iddrisu Baba','Ghana'),
        (610,'Jugador','Andy Yiadom','Ghana'),
        (611,'Jugador','Daniel-Kofi Kyereh','Ghana'),
        (612,'Jugador','Mohammed Kudus','Ghana'),
        (613,'Jugador','Mubarak Wakaso','Ghana'),
        (614,'Jugador','Thomas Partey','Ghana'),
        (615,'Jugador','André Ayew','Ghana'),
        (616,'Jugador','Felix Afena-Gyan','Ghana'),
        (617,'Jugador','Issahaku Abdul Fatawu','Ghana'),
        (618,'Jugador','Jordan Ayew','Ghana'),
        (619,'Cuadro','Team Photo','Uruguay'),
        (620,'Jugador','Kamaldeen Sulemana','Ghana'),
        (621,'Jugador','Fernando Muslera','Uruguay'),
        (622,'Escudo','Emblem','Uruguay'),
        (623,'Jugador','Ronald Araújo','Uruguay'),
        (624,'Jugador','Sergio Rochet','Uruguay'),
        (625,'Jugador','José María Giménez','Uruguay'),
        (626,'Jugador','Martín Cáceres','Uruguay'),
        (627,'Jugador','Mathías Olivera','Uruguay'),
        (628,'Jugador','Diego Godín','Uruguay'),
        (629,'Jugador','Rodrigo Bentancur','Uruguay'),
        (630,'Jugador','Matías Viña','Uruguay'),
        (631,'Jugador','Nicolás De La Cruz','Uruguay'),
        (632,'Jugador','Giorgian De Arrascaeta','Uruguay'),
        (633,'Jugador','Federico Valverde','Uruguay'),
        (634,'Jugador','Lucas Torreira','Uruguay'),
        (635,'Jugador','Edinson Cavani','Uruguay'),
        (636,'Jugador','Matías Vecino','Uruguay'),
        (637,'Jugador','Facundo Pellistri','Uruguay'),
        (638,'Jugador','Darwin Núñez','Uruguay'),
        (639,'Cuadro','Team Photo','South Korea'),
        (640,'Jugador','Luis Suárez','Uruguay'),
        (641,'Jugador','Seung-gyu Kim','South Korea'),
        (642,'Escudo','Emblem','South Korea'),
        (643,'Jugador','Chul Hong','South Korea'),
        (644,'Jugador','Hyeon-woo Jo','South Korea'),
        (645,'Jugador','Min-jae Kim','South Korea'),
        (646,'Jugador','Tae-hwan Kim','South Korea'),
        (647,'Jugador','Jin-su Kim','South Korea'),
        (648,'Jugador','Young-gwon Kim','South Korea'),
        (649,'Jugador','In-beom Hwang','South Korea'),
        (650,'Jugador','Yong Lee','South Korea'),
        (651,'Jugador','Jae-sung Lee','South Korea'),
        (652,'Jugador','Woo-young Jung','South Korea'),
        (653,'Jugador','Gue-sung Cho','South Korea'),
        (654,'Jugador','Seung-ho Paik','South Korea'),
        (655,'Jugador','Ui-jo Hwang','South Korea'),
        (656,'Jugador','Hee-chan Hwang','South Korea'),
        (657,'Jugador','Heung-min Son','South Korea'),
        (658,'Jugador','Chang-hoon Kwon','South Korea'),
        (659,'Historica','Uruguay 1930','Uruguay'),
        (660,'Jugador','Min-kyu Song','South Korea'),
        (661,'Historica','Brazil 1958','Brasil'),
        (662,'Historica','Italy 1938','Italia'),
        (663,'Historica','Brazil 1970','Brasil'),
        (664,'Historica','England 1966','Inglaterra'),
        (665,'Historica','Italy 1982','Italia'),
        (666,'Historica','Argentina 1978','Argentina'),
        (667,'Historica','France 1998','Francia'),
        (668,'Historica','Germany 1990','Alemania'),
        (669,'Historica','France 2018','Francia'),
        (670,'Historica','Spain 2010','Epaña');