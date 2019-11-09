<?php
include 'predefiner.php';
$feedcontent = array();

$email = $_POST['email'];
$query="select pw_processedmoney , pw_processtype, pw_processdate, pw_processfee from philam_wallets where pw_userid = (select pcc_accountid from philam_client_credentials where pcc_email = '".$email."' )";
	if ($result = $conn->query($query)) 
while($row = $result->fetch_array(MYSQLI_ASSOC))
$feedcontent [] = $row;
$output = json_encode(array('wallethistory' => $feedcontent	));
echo $output;
$conn->close();
?> 