DROP PROCEDURE IF EXISTS cliente__update;
DELIMITER $$
CREATE PROCEDURE cliente__update(IN p_id int,
                                IN p_nombre varchar(50),
                                IN p_apellido varchar(50),
                                IN p_edad int,
                                IN p_foto varchar(250))
BEGIN
	update cliente
	set nombre = p_nombre,
	    apellido=p_apellido,
	    edad=p_edad,
	    foto=p_foto
	where id = p_id;
END;