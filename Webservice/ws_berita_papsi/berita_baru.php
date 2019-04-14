<?php

include 'koneksi.php';

$id_penulis = $_POST["authorId"];
$judul = $_POST["title"];
$isi = $_POST["content"];
$gambar = "";

if (isset($_POST["picture"]) && !empty($_POST["picture"])) {
	$gambar = $_POST["picture"];
}

$sql = "INSERT INTO berita (id_penulis, judul, isi, gambar)
VALUES ($id_penulis, '$judul', '$isi', '$gambar')";

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