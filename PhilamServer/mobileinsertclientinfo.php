<?php
include 'predefiner.php';
$feedcontent = array();

$fullname = $_POST['fullname'];
$birthdate = $_POST['birthdate'];
$address = $_POST['address'];
$email = $_POST['email'];
$contact = $_POST['contact'];
$password = $_POST['password'];
$contactoption = $_POST['contactoption'];
$contacttime = $_POST['contacttime'];
$newsletter = $_POST['newsletter'];

 $sql = "INSERT INTO `philam_client_credentials` (`pcc_accountid`, `pcc_fullname`, `pcc_birthdate`, `pcc_address`, `pcc_email`, `pcc_contact`, `pcc_password`, `pcc_dateregistered`, `pcc_contactoption`, `pcc_contacttime`, `pcc_newsletter`) VALUES (NULL, '".$fullname."', '".$birthdate."', '".$address."', '".$email."', '".$contact."', (select md5('".$password."')), CURRENT_TIMESTAMP, '".$contactoption."', '".$contacttime."', $newsletter)";
$info = "";
try {
$result = mysqli_query($conn, $sql);
        $info = "true";
} catch (Exception $e) {
    $info= "Invalid Transaction!".$e.intl_get_error_message();
}
echo($info);
?> 