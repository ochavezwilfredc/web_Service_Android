<?php


 if($_SERVER['REQUEST_METHOD']=='GET'){
 
 //Consiguiendo valores
$id_emp=$_GET["id"];
 
 //Creando una consulta sql
 $sql = "DELETE FROM `empleado` WHERE id='$id_emp'";
 
 //Importing our db connection script
 require_once('dbconnect.php');
 
 //Ejecutando consulta en la base datos
 if(mysqli_query($con,$sql)){
	echo 'Empleado Eliminado satisfactoriamente';
 }else{
	echo 'No se puede Eliminar empleado';
 }
 
 //Closing the database 
 mysqli_close($con);
include('functions.php');
	$id_emp=$_GET["id"];

	
	

 }

?>


