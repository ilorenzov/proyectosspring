INSERT INTO clientes(nombre,apellido,email,create_at ,foto) VALUES ('Andres','Guzman', 'profesor@bolsaideas.com', '2017-08-28');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('John','Doe', 'profesor@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');


/* Populate tabla productos */

INSERT INTO productos(nombre,precio,create_at) VALUES('Panasonic Pantalla LCD', 259990 , NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES('Sony Camara Digital', 159990 , NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES('PS5', 359990 , NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES('XBOX X ', 123456 , NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES('PC GAMING', 3000 , NOW())
;
INSERT INTO facturas(descripcion,observacion,cliente_id,create_at) VALUES ('Factura equipos de oficina',null,1,NOW());
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (1,1,1);
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (2,1,4);
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (1,1,5);
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (1,1,2);

INSERT INTO facturas(descripcion,observacion,cliente_id,create_at) VALUES ('Factura bicicleta','Es muy buena',1,NOW());
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (3,2,2);
