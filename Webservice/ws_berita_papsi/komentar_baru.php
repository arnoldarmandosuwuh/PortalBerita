<?php

include 'koneksi.php';

$id_penulis = $_POST["authorId"];
$id_berita = $_POST["newsId"];
$isi = $_POST["content"];

$sql = "INSERT INTO komentar (id_penulis, id_berita, isi) VALUES ($id_penulis, $id_berita, '$isi')";

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