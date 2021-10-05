/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  jefer
 * Created: Sep 7, 2021
 */
DROP DATABASE IF EXISTS cajeroautomatico;
CREATE SCHEMA cajeroautomatico;
USE cajeroautomatico;

CREATE TABLE Usuario(
    id VARCHAR (30) PRIMARY KEY,
    nombre VARCHAR (30),
    saldo int,
    clave VARCHAR(4)
);

INSERT INTO Usuario (id, nombre, saldo,clave)
VALUES
(20191020088, "Jeferson Jimenez", 12500000, "9904" );

select * from Usuario
SELECT * FROM Usuario where id = '20191020088'