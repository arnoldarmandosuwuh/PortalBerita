<?php

include 'koneksi.php';

$id = $_POST["id"];
$judul = $_POST["title"];
$isi = $_POST["content"];
$gambar = "";

if (isset($_POST["picture"]) && !empty($_POST["picture"])) {
	$gambar = $_POST["picture"];
}

$sql = "UPDATE berita SET judul='$judul', isi='$isi', gambar='$gambar' WHERE id=$id";

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