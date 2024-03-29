DROP PROCEDURE IF EXISTS cliente__insert;
DELIMITER $$
CREATE PROCEDURE cliente__insert(IN p_nombre varchar(50),
                                   IN p_apellido varchar(50),
                                   IN p_edad int)
BEGIN
	insert into cliente(nombre,
	                    apellido,
	                    edad)
	                    values(p_nombre,
                                p_apellido,
                                p_edad);
END;