<?php
include 'predefiner.php';
$feedcontent = array();

$servicename = $_POST['servicename'];

$query="select ppo_productname, ppo_issueage, ppo_coverage,ppo_productequity, ppo_buyingoption, ppo_productdesc, ppo_providents, ppo_fineprint from philam_products_offered INNER join philam_services_offered on philam_products_offered.ppo_serviceid =  philam_services_offered.pso_service_offeredid where ppo_productname = '".$servicename."'";
	if ($result = $conn->query($query)) 
while($row = $result->fetch_array(MYSQLI_ASSOC))
$feedcontent [] = $row;
$output = json_encode(array('productdetails' => $feedcontent	));
echo $output;
$conn->close();
?> 

