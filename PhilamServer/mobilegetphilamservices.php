<?php
include 'predefiner.php';
$feedcontent = array();
$query="select pso_service_name,pso_icon, pso_service_desc,pst_service_servicename from philam_services_offered inner join philam_services_type on philam_services_type.pst_service_typeid = philam_services_offered.pso_service_type";
	if ($result = $conn->query($query)) 
while($row = $result->fetch_array(MYSQLI_ASSOC))
$feedcontent [] = $row;
$output = json_encode(array('services' => $feedcontent	));
echo $output;
$conn->close();
?> 