<?php

include 'koneksi.php';

$id = $_POST["commentId"];

$sql = "DELETE from komentar WHERE id=$id";

$result = array();
if ($conn->query($sql) === TRUE) {
	$result['status'] = 0;
	$result['message'] = "Record deleted successfully";
} else {
    $result['status'] = 1;
	$result['message'] = "Error: " . $sql . "<br>" . $conn->error;
}

echo json_encode($result);
$conn->close();

?>