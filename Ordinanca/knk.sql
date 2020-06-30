-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 30, 2020 at 10:34 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `knk`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctors`
--

CREATE TABLE `doctors` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `prof` varchar(20) NOT NULL,
  `office` varchar(20) NOT NULL,
  `tel` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `doctorshift`
--

CREATE TABLE `doctorshift` (
  `Id` int(11) NOT NULL,
  `doctorName` varchar(20) NOT NULL,
  `date` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doctorshift`
--

INSERT INTO `doctorshift` (`Id`, `doctorName`, `date`) VALUES
(1, 'Deliza', 2020),
(5, 'deliza1', 2020),
(6, 'flokart', 2020);

-- --------------------------------------------------------

--
-- Table structure for table `medicament`
--

CREATE TABLE `medicament` (
  `id` int(11) NOT NULL,
  `Medicament` varchar(256) NOT NULL,
  `Indication` varchar(256) NOT NULL,
  `Quantity` varchar(256) NOT NULL,
  `Origin` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `medicament`
--

INSERT INTO `medicament` (`id`, `Medicament`, `Indication`, `Quantity`, `Origin`) VALUES
(1, 'Antibiotic', 'Infection', '100', 'India'),
(3, 'OKI', 'everything', '200', 'China'),
(5, 'Penicilin', 'Anti-infective', '150', 'India'),
(6, 'Aspirin', 'Analgesic', '200', 'China'),
(7, 'Vacinnes', 'Imune System', '240', 'Canada');

-- --------------------------------------------------------

--
-- Table structure for table `patients`
--

CREATE TABLE `patients` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `doctorName` varchar(20) NOT NULL,
  `age` varchar(10) NOT NULL,
  `date` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `result` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patients`
--

INSERT INTO `patients` (`id`, `name`, `doctorName`, `age`, `date`, `type`, `result`) VALUES
(16, 'aurola', 'sfsdfdsa', '18', '12/02/2020', '2', 'asdadds'),
(18, 'aurolaaa', 'sfsdfdsa', '18', '12/02/2020', '2', 'asdadds'),
(19, 'aurolaaaa', 'sfsdfdsa', '18', '12/02/2020', '2', 'asdadds'),
(20, 'aurola', 'sfsdfdsa', '18', '12/02/2020', '2', 'asdadds');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `email`) VALUES
(17, 'aurola', '$2a$10$lruIcH08uP6I8J.rg.df/.P7et01VtwO/dUE.Tadrs7DEHE3HE8Lq', 'aurola@gmail.com'),
(18, 'rina', '$2a$10$Ial/qjEcuwm3o.pOF2kYNOanOy6nJ30AWzRaWO11PAmJI9jRadRr.', 'rina@gmail.com'),
(19, 'rvv', '$2a$10$nkgIOIMscx/a7wLvVenzfOHqATMLVRUInJUHkRwsnveAThka9CC8.', 'vwvrev@gmail.com'),
(20, 'ble', '$2a$10$ZkB7K3e3ayqKIYBFrrBpB.f/abdWV3hJ0j2TFB.GLddCq1Mz0GMRW', 'ble@gmail.com'),
(21, 'gresa', '$2a$10$M1rGPzuFvhGbgujJu8akgeZ3x8UK2lyACgaXa2NoXMZlTQDpS6twu', 'gresa@gmail.com'),
(22, 'gg', '$2a$10$SiZAzIf1Lsv2jbZNb2pTSeIn/KhOfNbDi4xy5zSNA/nYA.P8mKuoy', 'gg@gmail.com'),
(23, 'bb', '$2a$10$oKExBrIYXbYHfpZ.ozSWwupKK1t7pR/BTZD0/jYNtd7th/xvjoMlK', 'bb@gmail.com'),
(24, 'aa', '$2a$10$JAqo/ckZCFZTmmSQ6xwBPeNdjcFHJt229y3VssUSfQIuZxcR1S4hS', 'aa@gmail.com'),
(25, 'bes', '$2a$10$YEBZJwWSoqPk.pmvpDmNheWzL9.XNRLS0YbvucKTomzD3iD9QS46S', 'bes@gmail.com'),
(26, 'yy', '$2a$10$n5oGJTcm4TWZetV9P1FFO.Hf4uqhOhEpkafhRDruSWv6Np7zw5uHa', 'rthtrh@gmail.com'),
(28, 'www', '$2a$10$X6jj9FOqUGbqsXYtUmLXa.uw5u6efbywt2IFs77dr/vXcY26Qxbd.', 'www@gmail.com'),
(29, 'mmm', '$2a$15$eQNJ9d/b9MJjj5ZQkMOo0O0ujBDlzVoxf4ujNRxGeE9Cv/DM1O7zG', 'mmm@gmail.com'),
(30, 'eee', '$2a$15$oLy93K6R8.MTZKqJObQZN.2/WpmJuT1xzcXSYkcjo.rg.RQ8OOfwG', 'eee@gmail.com'),
(31, 'rrrr', '$2a$15$9h41Ildz9g9npEHzQB7K9uSSjsSXpD0i1JqsJky1FAkfax8lsasfi', 'rrrr@gmail.com'),
(32, 'kkk', '$2a$15$MsqlX4rg0aVa7.DID9bREeCHdoQUyENdobeDE/mfXUhf4snZqClbe', 'kkk@gmail.com'),
(33, 'aurolazeneli', '$2a$15$fmjhciD.Cq7DexThTP1T.uRb7fcEH507t4lkO24UNhv2GbcAiBwXq', 'auadsd@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doctors`
--
ALTER TABLE `doctors`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `doctorshift`
--
ALTER TABLE `doctorshift`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `medicament`
--
ALTER TABLE `medicament`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `patients`
--
ALTER TABLE `patients`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `doctors`
--
ALTER TABLE `doctors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `doctorshift`
--
ALTER TABLE `doctorshift`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `medicament`
--
ALTER TABLE `medicament`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `patients`
--
ALTER TABLE `patients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
