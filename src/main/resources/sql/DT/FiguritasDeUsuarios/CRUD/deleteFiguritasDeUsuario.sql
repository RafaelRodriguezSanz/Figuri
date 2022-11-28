DELETE FROM "OFERTAS"
WHERE id_publicacion IN 
		(SELECT id_publicacion
		FROM "PUBLICACIONES"
		WHERE id_figurita_usuario = ?);

DELETE FROM "OFERTAS"
WHERE id_publicacion1 IN 
		(SELECT id_publicacion
		FROM "PUBLICACIONES"
		WHERE id_figurita_usuario = ?);

UPDATE "OFERTAS"
SET id_publicacion2 = null
WHERE id_publicacion2 IN 
		(SELECT id_publicacion
		FROM "PUBLICACIONES"
		WHERE id_figurita_usuario = ?);

UPDATE "OFERTAS"
SET id_publicacion3 = null
WHERE id_publicacion3 IN 
		(SELECT id_publicacion
		FROM "PUBLICACIONES"
		WHERE id_figurita_usuario = ?);

DELETE FROM "PUBLICACIONES"
WHERE id_figurita_usuario = ?;

DELETE FROM "FIGURITAS_USUARIOS"
WHERE id_figurita_usuario = ?;