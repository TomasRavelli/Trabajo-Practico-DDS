INSERT INTO tpds.clasificacion_ticket VALUES (1,'Cambios de Configuracion de Sistema Operativo de PC');
INSERT INTO tpds.clasificacion_ticket VALUES (2,'Problemas en el funcionamiento del SO de PC y utilitarios');
INSERT INTO tpds.clasificacion_ticket VALUES (3,'Solicitud de instalacion de aplicaciones');
INSERT INTO tpds.clasificacion_ticket VALUES (4,'Mal funcionamiento de Hardware');
INSERT INTO tpds.clasificacion_ticket VALUES (5,'Problemas en la autenticacion en los distintos sistemas');
INSERT INTO tpds.clasificacion_ticket VALUES (6,'Problemas de acceso a la red local o remota');
INSERT INTO tpds.clasificacion_ticket VALUES (7,'Solicitud de usuarios de red');
INSERT INTO tpds.clasificacion_ticket VALUES (8,'Solicitud de usuarios para Sistemas informaticos que utiliza la empresa');
INSERT INTO tpds.clasificacion_ticket VALUES (9,'Modificacion en los perfiles de usuarios');
INSERT INTO tpds.clasificacion_ticket VALUES (10,'Solicitud de Cambio de Contrasenias');
INSERT INTO tpds.clasificacion_ticket VALUES (11,'Problemas en los Sistemas de la empresa');
INSERT INTO tpds.clasificacion_ticket VALUES (12,'Problemas con el correo electronico');
INSERT INTO tpds.clasificacion_ticket VALUES (13,'Solicitud de cuentas de correo electronico');
INSERT INTO tpds.clasificacion_ticket VALUES (14,'Solicitud de nuevos puestos de trabajo');
INSERT INTO tpds.clasificacion_ticket VALUES (15,'Solicitud de soporte en el uso de alguna aplicacion o sistema');
INSERT INTO tpds.clasificacion_ticket VALUES (16,'Otros');

INSERT INTO tpds.grupo_de_resolucion VALUES (1,'Mesa de Ayuda');
INSERT INTO tpds.grupo_de_resolucion VALUES (2,'Unidades de soporte');
INSERT INTO tpds.grupo_de_resolucion VALUES (3,'Servicio tecnico');
INSERT INTO tpds.grupo_de_resolucion VALUES (4,'Administrador de Base de Datos');
INSERT INTO tpds.grupo_de_resolucion VALUES (5,'Administrador SUSE Linux');
INSERT INTO tpds.grupo_de_resolucion VALUES (6,'Administrador Proxy y correo electronico');
INSERT INTO tpds.grupo_de_resolucion VALUES (7,'Administrador DEBIAN');
INSERT INTO tpds.grupo_de_resolucion VALUES (8,'Redes LAN');
INSERT INTO tpds.grupo_de_resolucion VALUES (9,'Comunicaciones');
INSERT INTO tpds.grupo_de_resolucion VALUES (10,'Desarrollo Sistema Comercial');
INSERT INTO tpds.grupo_de_resolucion VALUES (11,'Desarrollo Sistema RRHH');
INSERT INTO tpds.grupo_de_resolucion VALUES (12,'Desarrollo Sistema de Reclamos');


INSERT INTO tpds.estado VALUES (1,'','Abierto en Mesa de Ayuda');
INSERT INTO tpds.estado VALUES (2,'','Abierto derivado');
INSERT INTO tpds.estado VALUES (3,'','Cerrado');
INSERT INTO tpds.estado VALUES (4,'','Solucionado a la espera OK');


INSERT INTO tpds.empleado VALUES (10000,'empleado1','Malena','12345','12345',1);
INSERT INTO tpds.empleado VALUES (11111,'empleado2','Pilar','12345','12345',2);
INSERT INTO tpds.empleado VALUES (22222,'empleado3','Martin','12345','12345',3);
INSERT INTO tpds.empleado VALUES (33333,'empleado4','Tomas','12345','12345',4);
INSERT INTO tpds.empleado VALUES (44444,'empleado5','Gaston','12345','12345',5);
INSERT INTO tpds.empleado VALUES (55555,'empleado6','Emanuel','12345','12345',6);
INSERT INTO tpds.empleado VALUES (66666,'empleado7','Camila','12345','12345',7);
INSERT INTO tpds.empleado VALUES (77777,'empleado8','Hernan','12345','12345',8);
INSERT INTO tpds.empleado VALUES (88888,'empleado9','Juan Martin','12345','12345',9);
INSERT INTO tpds.empleado VALUES (99999,'empleado10','Santiago','12345','12345',10);

INSERT INTO tpds.usuario  VALUES (10000,'Malena','10101');
INSERT INTO tpds.usuario  VALUES (99999,'Santiago','11111');
INSERT INTO tpds.usuario VALUES (88888,'Juan Martin','22222');
INSERT INTO tpds.usuario VALUES (77777,'Hernan','33333');
INSERT INTO tpds.usuario VALUES (66666,'Camila','44444');
INSERT INTO tpds.usuario VALUES (55555,'Emanuel','55555');



insert into tpds.ultimo_numero_ticket values(1,0);