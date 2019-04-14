<?php

include 'koneksi.php';

$nama = $_POST["name"];
$nama_login = $_POST["username"];
$password = $_POST["password"];

$sql = "INSERT INTO penulis (nama, nama_login, password)
VALUES ('$nama', '$nama_login', md5('$password'))";

$result = array();

if ($conn->query($sql) === TRUE) {
	$result['status'] = 0;
	$result['message'] = "New record created successfully";
} else {
	$result['status'] = 1;
	$result['message'] = "Error: " . $sql . "<br>" . $conn->error;
}

echo json_encode($result);
$conn->close();

?>