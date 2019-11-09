
<?php
include 'predefiner.php';
$feedcontent = array();

$email = $_POST['email'];
$money = $_POST['money'];
$processtype = $_POST['processtype'];

 $sql = "INSERT INTO `philam_wallets` (`pw_waleltid`, `pw_userid`, `pw_processedmoney`, `pw_processtype`, `pw_processdate`) VALUES (NULL, (select pcc_accountid from philam_client_credentials where pcc_email = '".$email."'), ".$money.", ".$processtype.", CURRENT_TIMESTAMP)";

$info = "";
try {
$result = mysqli_query($conn, $sql);
        $info = "true";
} catch (Exception $e) {
    $info= "Invalid Transaction!".$e.intl_get_error_message();
}

echo($sql);
?> 