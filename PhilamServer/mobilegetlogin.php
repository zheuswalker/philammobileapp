
<?php
include 'predefiner.php';
$feedcontent = array();

$email = $_POST['email'];
$password = $_POST['password'];

 $sql = "select pcc_accountid from philam_client_credentials where pcc_email = '".$email."' and pcc_password = (select md5('".$password."'))";
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