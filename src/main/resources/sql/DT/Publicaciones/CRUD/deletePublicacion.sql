DELETE FROM "OFERTAS"
	WHERE id_publicación IN 
		(SELECT id_publicación
		FROM "OFERTAS"
		INTERSECT  
		SELECT id_publicación
		FROM "PUBLICACIONES"
		WHERE id_publicacion = ? );

DELETE FROM "PUBLICACIONES"
WHERE id_publicacion = ? ;