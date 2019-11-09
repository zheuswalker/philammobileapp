<?php
include 'predefiner.php';
$feedcontent = array();

$email = $_POST['email'];


 $sql = "select pcc_fullname, pcc_email, pcc_contact from philam_client_credentials where pcc_email = '".$email."'";
if ($result = $conn->query($sql)) 
while($row = $result->fetch_array(MYSQLI_ASSOC))
$feedcontent [] = $row;
$output = json_encode(array('profiledetails' => $feedcontent));
echo $output;
$conn->close();
 echo $sql;
?> 