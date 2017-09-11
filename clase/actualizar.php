<?php

 if($_SERVER['REQUEST_METHOD']=='POST'){
 
 //Consiguiendo valores
 $id_emp=$_POST["id"];
 $nom = $_POST['nombre'];
 $des = $_POST['designacion'];
 $sal = $_POST['salario'];
 
 //Creando una consulta sql
 $sql = "UPDATE `empleado` SET `nombre`='$nom',`designacion`='$des',`salario`='$sal' WHERE id ='$id_emp'";
 
 //Importing our db connection script
 require_once('dbconnect.php');
 
 //Ejecutando consulta en la base datos
 if(mysqli_query($con,$sql)){
	echo 'Empleado Actualizado satisfactoriamente';
	 }else{
		echo 'No se puede Actualizar empleado';
	 }
 
 //Closing the database 
 mysqli_close($con);
 }
?>


