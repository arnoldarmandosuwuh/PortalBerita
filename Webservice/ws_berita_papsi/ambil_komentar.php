<?php

include 'koneksi.php';

$id_berita = $_GET["newsId"];

$sql = "SELECT k.*, p.nama, p.foto FROM komentar k JOIN penulis p ON k.id_penulis = p.id WHERE id_berita = $id_berita order by k.tanggal_buat desc";
$query = $conn->query($sql);

$result = array();
if ($query) {
    $list = array();
    if ($query->num_rows > 0) {
        while($row = $query->fetch_assoc()) {
        	$komentar = array();
        	$komentar['id'] = $row['id'];
            $komentar['authorId'] = $row['id_penulis'];
        	$komentar['newsId'] = $row['id_berita'];
        	$komentar['content'] = $row['isi'];
        	$komentar['dateCreated'] = $row['tanggal_buat'];
        	$komentar['authorName'] = $row['nama'];
            $komentar['authorPhoto'] = $row['foto'];
            array_push($list, $komentar);
        }
    }
    $result['status'] = 0;
    $result['message'] = "Success";
    $result['data'] = json_encode($list);
} else {
    $result['status'] = 1;
    $result['message'] = "0 result";
}
$conn->close();

echo json_encode($result);

?>