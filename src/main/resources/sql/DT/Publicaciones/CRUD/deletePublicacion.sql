DELETE FROM "OFERTAS"
WHERE id_publicacion = ? OR 
	  id_publicacion1 = ?;

UPDATE "OFERTAS"
SET id_publicacion2 = null
WHERE id_publicacion2 = ?;

UPDATE "OFERTAS"
SET id_publicacion3 = null
WHERE id_publicacion3 = ?;

DELETE FROM "PUBLICACIONES"
WHERE id_publicacion = ?;