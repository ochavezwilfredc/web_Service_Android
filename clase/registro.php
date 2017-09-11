<?php include ('functions.php');
	$nombre=$_POST['nombre'];
	$designacion=$_POST['designacion'];
	$salario=$_POST['salario'];

ejecutarSQLCommand("INSERT INTO `empleado`(`nombre`, `designacion`, `salario`) VALUES ('$nombre','$designacion','$salario')

 ON DUPLICATE KEY UPDATE `nombre`='$nombre',
						 `designacion`=$designacion,
						 `salario`=$salario;");
						 

 ?>