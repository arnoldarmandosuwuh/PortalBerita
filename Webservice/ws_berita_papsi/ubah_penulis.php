<?php

include 'koneksi.php';

$id = $_POST["id"];
$nama = $_POST["name"];
$foto = "";

if (isset($_POST["photo"]) && !empty($_POST["photo"])) {
	$foto = $_POST["photo"];
}

$sql = "UPDATE penulis SET nama='$nama', foto='$foto' WHERE id=$id";

$result = array();
if ($conn->query($sql) === TRUE) {
	$result['status'] = 0;
	$result['message'] = "Record updated successfully";
} else {
    $result['status'] = 1;
	$result['message'] = "Error: " . $sql . "<br>" . $conn->error;
}

echo json_encode($result);
$conn->close();

?>