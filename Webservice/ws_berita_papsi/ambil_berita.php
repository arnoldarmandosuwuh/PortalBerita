<?php

include 'koneksi.php';

if (isset($_GET["authorId"])) $id_penulis = $_GET["authorId"];
if (isset($_GET["content"])) $isi = $_GET["content"];
if (isset($_GET["startDate"])) $tanggal_buat_awal = $_GET["startDate"];
if (isset($_GET["endDate"])) $tanggal_buat_akhir = $_GET["endDate"];

$sql = "SELECT b.*, p.nama FROM berita b JOIN penulis p ON b.id_penulis = p.id where 1=1";

if (isset($id_penulis))
    $sql .= " AND b.id_penulis = $id_penulis";

if (isset($isi))
    $sql .= " AND (b.isi LIKE '%$isi%' OR b.judul LIKE '%$isi%')";

if (isset($tanggal_buat_awal))
    $sql .= " AND b.tanggal_buat > STR_TO_DATE('$tanggal_buat_awal', '%d/%m/%Y')";

if (isset($tanggal_buat_akhir))
    $sql .= " AND b.tanggal_buat < STR_TO_DATE('$tanggal_buat_akhir', '%d/%m/%Y') + INTERVAL 1 DAY";

$sql .= " ORDER BY b.tanggal_buat DESC";

$query = $conn->query($sql);

$result = array();
if ($query) {
    $list = array();
    while($row = $query->fetch_assoc()) {
    	$berita = array();
    	$berita['id'] = $row['id'];
        $berita['authorId'] = $row['id_penulis'];
    	$berita['author'] = $row['nama'];
    	$berita['title'] = $row['judul'];
    	$berita['content'] = $row['isi'];
    	$berita['picture'] = $row['gambar'];
        $berita['dateCreated'] = $row['tanggal_buat'];
        array_push($list, $berita);
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