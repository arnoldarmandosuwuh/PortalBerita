-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 20, 2019 at 01:54 AM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `berita_papsi`
--

-- --------------------------------------------------------

--
-- Table structure for table `berita`
--

CREATE TABLE `berita` (
  `id` int(5) NOT NULL,
  `id_penulis` int(5) NOT NULL,
  `judul` varchar(500) NOT NULL,
  `isi` varchar(10000) NOT NULL,
  `gambar` varchar(9999) NOT NULL,
  `tanggal_buat` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `berita`
--

INSERT INTO `berita` (`id`, `id_penulis`, `judul`, `isi`, `gambar`, `tanggal_buat`) VALUES
(1, 1, 'Belajar Android', 'Belajar Android adalah hal yang menyenangkan dan mendamaikan hati', 'https://seeklogo.com/images/A/android-logo-9E4539A7DE-seeklogo.com.png', '2018-03-18 00:09:20'),
(2, 1, 'Navigation Drawer', 'Navigation drawer tidak lepas dari fragment. fragment merupakan sub activity.', '', '2018-03-19 12:54:51'),
(3, 1, 'JSON', 'JSON adalah format penulisan untuk transaksi data', 'https://cdn-images-1.medium.com/max/512/1*BzD9VGIbTmQpANue7eC1Rw.png', '2018-03-19 13:49:43'),
(4, 1, 'Gradle', 'Gradle adalah tools untuk manajemen library', 'https://versioneye.files.wordpress.com/2014/08/gradle-icon-512x512.png', '2018-03-19 13:54:39'),
(5, 1, 'Kebakaran', 'Telah terjadi kebakaran di Jl. Parampampam dengan korban tewas sebanyak 4 orang.', 'https://awsimages.detik.net.id//community/media/visual/2017/06/19/9a67a404-4c77-4862-8979-c04291f51018_169.jpg?w', '2018-03-19 13:56:24'),
(6, 1, 'Lebaran Ketupat', 'Lebaran tahun ini melibatkan banyak personil keamanan.', 'https://cdn.brilio.net/news/2015/07/15/9718/750xauto-ini-lima-tradisi-idul-fitri-yang-bakal-bikin-kamu-selalu-rindu-150715o.jpg', '2018-03-19 13:58:10'),
(7, 1, 'Tumor Ganas', 'Tumor baru yang dinamakan tumor syalala telah menyebar luas hingga ke kutub selatan', 'https://assets-a2.kompasiana.com/items/album/2015/10/20/wanita-dan-kehidupannya-56263e959197737a0c155c26.jpg?t', '2018-03-19 14:00:15'),
(11, 17, 'Kepergian Zlatan Ibrahimovic', 'Zlatan Ibrahimovic dan Manchester United telah sepakat mengakhiri kerja sama di Old Trafford. Ibra dikabarkan akan melanjutkan kariernya ke LA Galaxy.\n\nUsai sudah kebersamaan MU dengan Ibra yang hampir berjalan dua tahun. Kepastian itu didapat setelah The Red Devils menyampaikannya lewat rilis di situs resminya. Kedua belah pihak sepakat mengakhiri kontrak lebih awal yang mulanya baru habis pada akhir musim ini.', 'https://akcdn.detik.net.id/community/media/visual/2017/11/19/3dbc1715-a680-4534-93b3-b4f28f8e86dd_169.jpg?w', '2018-03-23 05:33:16'),
(12, 17, 'GP Ala Pom Bensin', 'Entah apa yang terjadi di sebuah SPBU ini. Dibagikan oleh sebuah akun di Instagram terlihat kerumunan pengendara motor sedang menunggu di balik pagar pom bensin. \n\nKetika beberapa petugas membuka pagar, seluruh pengendara tersebut langsung ngegas, berjuang untuk cepat mengisi bensinnya.', 'https://akcdn.detik.net.id/community/media/visual/2018/03/22/0a95598a-063f-4e0a-a17b-592bdbba3ed5_169.png?w', '2018-03-23 05:35:59');

-- --------------------------------------------------------

--
-- Table structure for table `komentar`
--

CREATE TABLE `komentar` (
  `id` int(5) NOT NULL,
  `id_penulis` int(5) NOT NULL,
  `id_berita` int(5) NOT NULL,
  `isi` varchar(9999) NOT NULL,
  `tanggal_buat` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `komentar`
--

INSERT INTO `komentar` (`id`, `id_penulis`, `id_berita`, `isi`, `tanggal_buat`) VALUES
(1, 1, 1, 'aa', '2018-03-20 15:22:37'),
(2, 16, 6, 'wewewewdasd ', '2018-03-20 15:23:45'),
(3, 16, 7, 'sd sdf sdf sdf sdf sdf sdf sdf sdf sdf sdf dsf ', '2018-03-20 22:48:19'),
(5, 16, 7, 'adsfacsdfcadsfcdsafcdsafcsadcsdacfsd sad fsadcdasfdsf adsfcsadcsdacfa ', '2018-03-22 00:26:04'),
(8, 16, 8, 'vgvgfcgcf', '2018-03-22 13:43:22'),
(9, 16, 8, 'vgvgfcgcfsdfcsf', '2018-03-22 13:43:25'),
(11, 1, 11, 'Sangat disayangkan. padahal bagusan MU dari pada LA Galaxy.', '2018-03-23 02:47:30'),
(12, 17, 11, 'Mungkin lagi cari uang buat persiapan pensiun.', '2018-03-23 02:48:50');

-- --------------------------------------------------------

--
-- Table structure for table `penulis`
--

CREATE TABLE `penulis` (
  `id` int(5) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `nama_login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `foto` varchar(9999) NOT NULL,
  `tanggal_buat` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `penulis`
--

INSERT INTO `penulis` (`id`, `nama`, `nama_login`, `password`, `foto`, `tanggal_buat`) VALUES
(1, 'Bayu Charisma', 'charisma', 'f7c32acaab92de9ae346d2527c86b048', 'http://ethisecure.com/wordpress/wp-content/uploads/2013/01/Secure-server-05.png', '2018-03-11 12:38:17'),
(2, 'charisma', 'bayubayu', 'bayubayu', '', '2018-03-11 13:44:27'),
(9, 'putra putra', 'putraputra', 'putraputra', '', '2018-03-11 13:50:54'),
(12, 'dwidasar', 'dwidasar', 'dwidasar', '', '2018-03-11 13:55:13'),
(13, 'kedawung', 'kedawung', 'kedawung', '', '2018-03-11 13:57:02'),
(14, 'hahahaha', 'hahahaha', 'hahahaha', '', '2018-03-11 14:04:25'),
(15, 'Kepala Suku', 'kepalasuku', 'kepalasuku', '', '2018-03-20 06:21:33'),
(16, 'Kipas Angin Cosmos', 'kipasangin', 'fe1eee391afb7567ff222f5409eaad89', 'https://assets-a2.kompasiana.com/items/album/2015/10/20/wanita-dan-kehidupannya-56263e959197737a0c155c26.jpg?t', '2018-03-20 06:27:27'),
(17, 'Danang Wahyu', 'danang12', '563b1bcae7c7c4f897c4512fbac026b0', '', '2018-03-23 05:28:46');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `berita`
--
ALTER TABLE `berita`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `komentar`
--
ALTER TABLE `komentar`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `penulis`
--
ALTER TABLE `penulis`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nama_login` (`nama_login`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `berita`
--
ALTER TABLE `berita`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `komentar`
--
ALTER TABLE `komentar`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `penulis`
--
ALTER TABLE `penulis`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
