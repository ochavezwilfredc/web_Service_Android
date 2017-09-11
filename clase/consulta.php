<?php
include('functions.php');
	$id_emp=$_GET["id"];


if($resultset=getSQLResultSet("  SELECT * 
  	FROM `empleado` WHERE id ='$id_emp'")){
	while ($row = $resultset->fetch_array(MYSQLI_NUM)){
		echo json_encode($row);
	}
}

?>


