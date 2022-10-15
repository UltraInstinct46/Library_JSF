-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 15, 2022 at 02:11 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_library`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `p_total_bayar_ujian` (IN `id` INT(12))   BEGIN
    SELECT SUM(ujian.harga) AS "Total Harga" FROM siswa INNER JOIN ujian ON siswa.id_ujian = ujian.id_ujian WHERE siswa.id_ujian = id;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `data_anggota`
--

CREATE TABLE `data_anggota` (
  `id_anggota` int(10) NOT NULL,
  `NIM` int(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `nama_anggota` varchar(50) NOT NULL,
  `kelas` varchar(5) NOT NULL,
  `tempat_lahir` varchar(15) NOT NULL,
  `tanggal_lahir` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_anggota`
--

INSERT INTO `data_anggota` (`id_anggota`, `NIM`, `username`, `nama_anggota`, `kelas`, `tempat_lahir`, `tanggal_lahir`) VALUES
(13, 123, 'admin3', '1', 'SE1', '1', '2022-04-01');

-- --------------------------------------------------------

--
-- Table structure for table `data_buku`
--

CREATE TABLE `data_buku` (
  `id_buku` int(8) NOT NULL,
  `judul_buku` varchar(50) NOT NULL,
  `penerbit` varchar(50) NOT NULL,
  `pengarang` varchar(50) NOT NULL,
  `tahun_penerbit` year(4) NOT NULL,
  `kategori` varchar(30) NOT NULL,
  `stok` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_buku`
--

INSERT INTO `data_buku` (`id_buku`, `judul_buku`, `penerbit`, `pengarang`, `tahun_penerbit`, `kategori`, `stok`) VALUES
(39, 'chihaya', 'chihaya', 'test', 2001, 'chihaya', 1),
(44, 'test', 'test', 'test', 0000, 'test', 0);

-- --------------------------------------------------------

--
-- Table structure for table `data_peminjambuku`
--

CREATE TABLE `data_peminjambuku` (
  `id_peminjam` int(8) NOT NULL,
  `username` varchar(50) NOT NULL,
  `id_buku` int(8) NOT NULL,
  `status` varchar(30) NOT NULL,
  `tgl_pinjam` date NOT NULL,
  `tgl_pengembalian` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `data_peminjambuku`
--
DELIMITER $$
CREATE TRIGGER `pengembalian_buku` AFTER UPDATE ON `data_peminjambuku` FOR EACH ROW BEGIN
UPDATE data_buku SET stok = stok + 1 WHERE id_buku = NEW.id_buku;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `pinjam_buku` AFTER INSERT ON `data_peminjambuku` FOR EACH ROW BEGIN
UPDATE data_buku SET stok = data_buku.stok - 1 WHERE id_buku = NEW.id_buku;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `data_petugas`
--

CREATE TABLE `data_petugas` (
  `id_petugas` int(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `nama_petugas` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `no_telp` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_petugas`
--

INSERT INTO `data_petugas` (`id_petugas`, `username`, `nama_petugas`, `alamat`, `no_telp`) VALUES
(1, 'admin1', 'Adhisti Roshandy', 'Depok', '085691920708'),
(2, 'admin2', 'Fahmi Alamsyah Nugroho', 'Citayam', '085881693638'),
(3, 'admin3', 'Restuaji Eliansyah', 'Bogor', '085678654780');

-- --------------------------------------------------------

--
-- Table structure for table `multiuser_login`
--

CREATE TABLE `multiuser_login` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `login_type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `multiuser_login`
--

INSERT INTO `multiuser_login` (`username`, `password`, `login_type`) VALUES
('adhisti', '2101', 'Mahasiswa'),
('admin1', '123', 'Admin'),
('admin2', '4356', 'Admin'),
('admin3', '9090', 'Admin'),
('fahmi', '2103', 'Mahasiswa'),
('restu', '123', 'Mahasiswa');

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `id_siswa` int(11) NOT NULL,
  `id_ujian` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`id_siswa`, `id_ujian`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ujian`
--

CREATE TABLE `ujian` (
  `id_ujian` int(11) NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ujian`
--

INSERT INTO `ujian` (`id_ujian`, `harga`) VALUES
(1, 20000),
(2, 35000),
(3, 50000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_anggota`
--
ALTER TABLE `data_anggota`
  ADD PRIMARY KEY (`id_anggota`),
  ADD UNIQUE KEY `NIM` (`NIM`) USING BTREE,
  ADD KEY `foreign keylk` (`username`);

--
-- Indexes for table `data_buku`
--
ALTER TABLE `data_buku`
  ADD PRIMARY KEY (`id_buku`);

--
-- Indexes for table `data_peminjambuku`
--
ALTER TABLE `data_peminjambuku`
  ADD PRIMARY KEY (`id_peminjam`) USING BTREE,
  ADD KEY `foreign key` (`id_buku`);

--
-- Indexes for table `data_petugas`
--
ALTER TABLE `data_petugas`
  ADD PRIMARY KEY (`id_petugas`),
  ADD KEY `foreign key_petugas` (`username`);

--
-- Indexes for table `multiuser_login`
--
ALTER TABLE `multiuser_login`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`id_siswa`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `data_anggota`
--
ALTER TABLE `data_anggota`
  MODIFY `id_anggota` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `data_buku`
--
ALTER TABLE `data_buku`
  MODIFY `id_buku` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `data_peminjambuku`
--
ALTER TABLE `data_peminjambuku`
  MODIFY `id_peminjam` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `data_petugas`
--
ALTER TABLE `data_petugas`
  MODIFY `id_petugas` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `data_anggota`
--
ALTER TABLE `data_anggota`
  ADD CONSTRAINT `foreign keylk` FOREIGN KEY (`username`) REFERENCES `multiuser_login` (`username`);

--
-- Constraints for table `data_peminjambuku`
--
ALTER TABLE `data_peminjambuku`
  ADD CONSTRAINT `foreign key` FOREIGN KEY (`id_buku`) REFERENCES `data_buku` (`id_buku`);

--
-- Constraints for table `data_petugas`
--
ALTER TABLE `data_petugas`
  ADD CONSTRAINT `foreign key_petugas` FOREIGN KEY (`username`) REFERENCES `multiuser_login` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
