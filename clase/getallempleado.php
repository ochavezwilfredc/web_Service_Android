<?php 

 	require_once('dbconnect.php');
 	$sql = "SELECT * FROM empleado";


 	$r = mysqli_query($con,$sql);

 	$result = array();

 	while ($row = mysqli_fetch_array($r)) {
		array_push($result,array("id" => $row['id'],"nombre" => $row['nombre']));
 	}

 	echo json_encode(array('resultado' => $result));
 	mysqli_close($con);

?>