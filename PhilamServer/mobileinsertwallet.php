
<?php
include 'predefiner.php';
$feedcontent = array();

$email = $_POST['email'];
$money = $_POST['money'];
$processtype = $_POST['processtype'];

 $sql = "INSERT INTO `philam_wallets` (`pw_waleltid`, `pw_userid`, `pw_processedmoney`, `pw_processtype`, `pw_processdate`) VALUES (NULL, (select pcc_accountid from philam_client_credentials where pcc_email = '".$email."'), ".$money.", ".$processtype.", CURRENT_TIMESTAMP)";
$info = "";

$result = mysqli_query($conn, $sql);
if (mysqli_num_rows($result) > 0) {
    $info = "true";
    }
 else {
    $info = "false";
}
echo($inf);
?> 