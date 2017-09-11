<?php 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 
 //Consiguiendo valores
 $nom = $_POST['nombre'];
 $des = $_POST['designacion'];
 $sal = $_POST['salario'];
 
 //Creando una consulta sql
 $sql = "INSERT INTO empleado (nombre,designacion,salario) VALUES ('$nom','$des','$sal')";
 
 //Importing our db connection script
 require_once('dbconnect.php');
 
 //Ejecutando consulta en la base datos
 if(mysqli_query($con,$sql)){
	echo 'Empleado registrado satisfactoriamente';
 }else{
	echo 'No se puede agregar empleado';
 }
 
 //Closing the database 
 mysqli_close($con);
 }