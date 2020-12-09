INSERT INTO clientes(nombre,apellido,email,create_at ,foto) VALUES ('Ignacio','L', 'nacho@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('John','Doe', 'profesor@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');
INSERT INTO clientes(nombre,apellido,email,create_at, foto) VALUES ('Pepe','Doe', 'pepe@bolsaideas.com', '2017-08-28','');


/* Populate tabla productos */

INSERT INTO productos(nombre,precio,create_at) VALUES('Panasonic Pantalla LCD', 259990 , NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES('Sony Camara Digital', 159990 , NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES('PS5', 359990 , NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES('XBOX X ', 123456 , NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES('PC GAMING', 3000 , NOW());


INSERT INTO facturas(descripcion,observacion,cliente_id,create_at) VALUES ('Factura equipos de oficina',null,1,NOW());
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (1,1,1);
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (2,1,4);
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (1,1,5);
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (1,1,2);

INSERT INTO facturas(descripcion,observacion,cliente_id,create_at) VALUES ('Factura bicicleta','Es muy buena',1,NOW());
INSERT INTO facturas_items(cantidad,factura_id,producto_id) VALUES (3,2,2);

INSERT INTO users ( username,password,enabled) VALUES('admin', '$2a$10$jOqUD8BGaFGSFDOfrRP23eEg2pd.QnmWpWgGLNuM4ZUTiycjNh286', 1)
INSERT INTO users ( username,password,enabled) VALUES('nacho', '$2a$10$2Ud1NQJoKEOV04St9di.0eDG6ga8MVA4yGgK5ZuJP4lVOK/8MI0iu', 1)

INSERT INTO authorities ( user_id ,authority) VALUES('1','ROLE_USER')
INSERT INTO authorities ( user_id ,authority) VALUES('1','ROLE_ADMIN')
INSERT INTO authorities ( user_id ,authority) VALUES('2','ROLE_USER')


