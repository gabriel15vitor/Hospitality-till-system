-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 23, 2025 at 12:45 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospitality`
--

-- --------------------------------------------------------

--
-- Table structure for table `drinks`
--

CREATE TABLE `drinks` (
  `ID` int(11) NOT NULL,
  `Type_of_Drink` text NOT NULL,
  `Name` text NOT NULL,
  `Pint` double NOT NULL,
  `Twothirds` double NOT NULL,
  `Half` double NOT NULL,
  `OneThird` double NOT NULL,
  `Bottle` double NOT NULL,
  `250ml` double NOT NULL,
  `175ml` double NOT NULL,
  `125ml` double NOT NULL,
  `50ml` double NOT NULL,
  `25ml` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `drinks`
--

INSERT INTO `drinks` (`ID`, `Type_of_Drink`, `Name`, `Pint`, `Twothirds`, `Half`, `OneThird`, `Bottle`, `250ml`, `175ml`, `125ml`, `50ml`, `25ml`) VALUES
(1, 'Beer', 'Lumina', 6, 4.12, 3.15, 2.16, 0, 0, 0, 0, 0, 0),
(2, 'Beer', 'Soundwave', 6.9, 4.74, 3.62, 2.48, 0, 0, 0, 0, 0, 0),
(3, 'Beer', 'Mesmerist', 5.5, 3.78, 2.89, 1.98, 0, 0, 0, 0, 0, 0),
(4, 'Beer', 'Pastel', 5, 3.43, 2.62, 1.8, 0, 0, 0, 0, 0, 0),
(5, 'Beer', 'Station hill', 6.1, 4.19, 3.2, 2.2, 0, 0, 0, 0, 0, 0),
(6, 'Beer', 'Brokendream', 6.9, 4.74, 3.62, 2.48, 0, 0, 0, 0, 0, 0),
(7, 'Beer', 'Shattered Dream', 5, 3.43, 2.62, 1.8, 0, 0, 0, 0, 0, 0),
(8, 'Beer', 'Mavka', 7, 4.81, 3.68, 2.52, 0, 0, 0, 0, 0, 0),
(9, 'Beer', 'Grapefruit', 6, 4.12, 3.15, 2.16, 0, 0, 0, 0, 0, 0),
(10, 'Beer', 'Second Serve', 7.1, 4.88, 3.73, 2.56, 0, 0, 0, 0, 0, 0),
(11, 'Beer', 'Santo', 5.8, 3.98, 3.04, 2.09, 0, 0, 0, 0, 0, 0),
(12, 'Cider', 'Apple Cider', 6, 4.2, 3.55, 2.1, 0, 0, 0, 0, 0, 0),
(13, 'Cider', 'Cherry Cider', 6, 4.2, 3.55, 2.1, 0, 0, 0, 0, 0, 0),
(14, 'Wine', 'Malbec', 0, 0, 0, 0, 30, 11, 8.25, 5.5, 0, 0),
(15, 'Wine', 'Merlot', 0, 0, 0, 0, 28, 10.27, 7.7, 5.13, 0, 0),
(16, 'Wine', 'Pinot Noir', 0, 0, 0, 0, 32, 11.73, 8.8, 5.87, 0, 0),
(17, 'Wine', 'Sauvignon Blanc', 0, 0, 0, 0, 26, 9.53, 7.15, 4.77, 0, 0),
(18, 'Wine', 'Pinot Grigio', 0, 0, 0, 0, 24, 8.8, 6.6, 4.4, 0, 0),
(19, 'Wine', 'Viognier', 0, 0, 0, 0, 29, 10.63, 7.98, 5.32, 0, 0),
(20, 'Wine', 'French Rose', 0, 0, 0, 0, 27, 9.9, 7.43, 4.95, 0, 0),
(21, 'Spirit', 'White Gin', 0, 0, 0, 0, 0, 0, 0, 0, 5, 2.7),
(22, 'Spirit', 'White Rum', 0, 0, 0, 0, 0, 0, 0, 0, 6, 3.1),
(23, 'Spirit', 'Bourbon', 0, 0, 0, 0, 0, 0, 0, 0, 7.5, 3.5),
(24, 'Spirit', 'Absolut', 0, 0, 0, 0, 0, 0, 0, 0, 4, 2.5),
(25, 'Spirit', 'Pink Gin', 0, 0, 0, 0, 0, 0, 0, 0, 8, 4.6),
(26, 'Spirit', 'Spiced Rum', 0, 0, 0, 0, 0, 0, 0, 0, 7, 3.8),
(27, 'Spirit', 'Foreign Whisky', 0, 0, 0, 0, 0, 0, 0, 0, 8.5, 5),
(28, 'Soft', 'Coke', 0, 0, 0, 0, 3, 0, 0, 0, 0, 0),
(29, 'Soft', 'Diet coke', 0, 0, 0, 0, 2.8, 0, 0, 0, 0, 0),
(30, 'Soft', 'Orange Juice', 0, 0, 0, 0, 4, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `food`
--

CREATE TABLE `food` (
  `ID` int(11) NOT NULL,
  `Type_of_plate` text NOT NULL,
  `Name` text NOT NULL,
  `Price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `food`
--

INSERT INTO `food` (`ID`, `Type_of_plate`, `Name`, `Price`) VALUES
(31, 'Starter', 'Smoky Charred Corn Ribs', 7.5),
(32, 'Starter', 'Fire-Grilled Halloumi', 8),
(33, 'Starter', 'Ember-Roasted Tomato Soup', 6.5),
(34, 'Starter', 'Crispy Spiced Calamari', 9),
(35, 'Main', 'Flame-Seared Ribeye', 24),
(36, 'Main', 'Charcoal-Grilled Salmon', 19.5),
(37, 'Main', 'Wood-Fired Wild Mushroom Risotto', 16),
(38, 'Main', 'Smoked BBQ Jackfruit Burger', 14.5),
(39, 'Side', 'Maple-Glazed Carrots', 5.5),
(40, 'Side', 'Truffle Parmesan Fries', 6),
(41, 'Side', 'Chili Butter Greens', 5),
(42, 'Side', 'Garlic Roasted Potatoes', 5.5),
(43, 'Dessert', 'Molten Chocolate Lava Cake', 7.5),
(44, 'Dessert', 'Caramelized Pear & Almond Tart', 6.5),
(45, 'Dessert', 'Toasted Marshmallow Cheesecake', 7);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `transaction_id` int(11) NOT NULL,
  `transaction_date` datetime NOT NULL,
  `total_amount` double(10,2) NOT NULL,
  `items` longtext NOT NULL,
  `Transaction_type` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`transaction_id`, `transaction_date`, `total_amount`, `items`, `Transaction_type`) VALUES
(32, '2025-02-18 13:06:57', 26.28, '2<->Pint 5<->Pint 6<->Half 10<->1/3 10<->Pint', 'card'),
(33, '2025-02-18 13:08:20', 39.12, '32<-> 34<-> 34<-> 34<-> 1<->2/3', 'card'),
(34, '2025-02-19 05:06:50', 12.00, '1<->Pint 1<->Pint', 'card'),
(35, '2025-02-19 05:32:46', 16.48, '1<->2/3 1<->2/3 1<->2/3 1<->2/3', 'card'),
(36, '2025-02-19 06:53:12', 31.43, '32<-> 32<-> 1<->Pint 1<->Pint 4<->2/3', 'cash'),
(37, '2025-02-19 18:41:19', 9.80, '28<->Bottle 29<->Bottle 30<->Bottle', 'card'),
(38, '2025-02-19 21:06:03', 18.30, '9<->Pint 9<->Pint 9<->Half 9<->Half', 'card'),
(39, '2025-02-20 03:08:27', 15.00, '31<-> 31<->', 'card'),
(40, '2025-02-20 22:41:29', 0.00, '32<-> 32<->', 'cash'),
(41, '2025-02-20 22:48:55', 22.00, '1<->Pint 1<->Pint 4<->Pint 4<->Pint', 'card');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `drinks`
--
ALTER TABLE `drinks`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`transaction_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `drinks`
--
ALTER TABLE `drinks`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `food`
--
ALTER TABLE `food`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
