DELETE FROM "OFERTAS"
	WHERE id_publicación IN 
		(SELECT id_publicacion
		FROM "PUBLICACIONES"
		WHERE id_figurita_usuario IN 
			(SELECT id_figurita_usuario
			FROM "PUBLICACIONES"
			INTERSECT  
			SELECT id_figurita_usuario
			FROM "FIGURITAS_USUARIOS"
			WHERE id_usuario = ?)
		);

DELETE FROM "PUBLICACIONES"
	WHERE id_figurita_usuario IN 
		(SELECT id_figurita_usuario
		FROM "PUBLICACIONES"
		INTERSECT  
		SELECT id_figurita_usuario
		FROM "FIGURITAS_USUARIOS"
		WHERE id_usuario = ?);

DELETE FROM "FIGURITAS_USUARIOS"
WHERE id_usuario=?;
DELETE FROM "USUARIOS"
WHERE ci = ?;