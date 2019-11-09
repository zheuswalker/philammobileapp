<?php
include 'predefiner.php';
$feedcontent = array();

$email = $_POST['email'];
$password = $_POST['password'];

 $sql = "SELECT pcc_accountid FROM `philam_client_credentials` WHERE pcc_email ='".$email."' and pcc_password = (select md5('".$password."'))";
$info = "";

$result = mysqli_query($conn, $sql);
if (mysqli_num_rows($result) > 0) {
    $info = "true";
    }
 else {
    $info = "false";
}
echo($info);
?> 