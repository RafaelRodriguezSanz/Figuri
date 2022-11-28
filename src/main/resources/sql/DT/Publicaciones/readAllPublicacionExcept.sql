SELECT *
FROM "PUBLICACIONES"
WHERE id_figurita_usuario NOT IN 
		(SELECT id_figurita_usuario
		FROM "FIGURITAS_USUARIOS"
		WHERE id_usuario = ?);