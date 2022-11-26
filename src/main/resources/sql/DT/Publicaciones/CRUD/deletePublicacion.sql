DELETE FROM "OFERTAS"
	WHERE id_publicacion IN 
		(SELECT id_publicacion
		FROM "OFERTAS"
		INTERSECT  
		SELECT id_publicacion
		FROM "PUBLICACIONES"
		WHERE id_publicacion = ? );

DELETE FROM "PUBLICACIONES"
WHERE id_publicacion = ? ;