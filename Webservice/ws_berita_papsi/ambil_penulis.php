<?php

include 'koneksi.php';

$id_penulis = $_GET["authorId"];

$sql = "SELECT p.* FROM penulis p WHERE p.id = $id_penulis";
$query = $conn->query($sql);

$result = array();
if ($query->num_rows > 0) {
    while($row = $query->fetch_assoc()) {
    	$penulis = array();
    	$penulis['id'] = $row['id'];
        $penulis['name'] = $row['nama'];
    	$penulis['photo'] = $row['foto'];
    	$penulis['dateCreated'] = $row['tanggal_buat'];
    }
    $result['status'] = 0;
    $result['message'] = "Success";
    $result['data'] = json_encode($penulis);
} else {
    $result['status'] = 1;
    $result['message'] = "0 result";
}
$conn->close();

echo json_encode($result);

?>