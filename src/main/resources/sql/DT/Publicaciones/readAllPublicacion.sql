SELECT *
FROM "PUBLICACIONES"
WHERE id_figurita_usuario IN 
		(SELECT id_figurita_usuario
		FROM "FIGURITAS_USUARIOS"
		WHERE id_usuario = ?);