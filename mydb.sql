-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 12 Mar 2022, 16:03
-- Wersja serwera: 8.0.28
-- Wersja PHP: 7.4.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `mydb`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `myorder`
--

CREATE TABLE `myorder` (
  `idOrder` int NOT NULL,
  `dateOfOrder` date NOT NULL,
  `dateOfReceive` date NOT NULL,
  `User_idUser` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `orderproduct`
--

CREATE TABLE `orderproduct` (
  `idOrderProduct` int NOT NULL,
  `price` double NOT NULL,
  `Order_idOrder` int NOT NULL,
  `Product_idProduct` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `product`
--

CREATE TABLE `product` (
  `idProduct` int NOT NULL,
  `name` longtext NOT NULL,
  `category` longtext NOT NULL,
  `size` longtext NOT NULL,
  `description` longtext NOT NULL,
  `releaseDate` date NOT NULL,
  `price` double NOT NULL,
  `availability` tinyint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `role`
--

CREATE TABLE `role` (
  `idRole` int NOT NULL,
  `nameRole` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `idUser` int NOT NULL,
  `name` longtext NOT NULL,
  `surname` longtext NOT NULL,
  `birthdate` date NOT NULL,
  `email` longtext NOT NULL,
  `login` longtext NOT NULL,
  `pass` longtext NOT NULL,
  `Role_idRole` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `myorder`
--
ALTER TABLE `myorder`
  ADD PRIMARY KEY (`idOrder`),
  ADD KEY `fk_Order_User1_idx` (`User_idUser`);

--
-- Indeksy dla tabeli `orderproduct`
--
ALTER TABLE `orderproduct`
  ADD PRIMARY KEY (`idOrderProduct`),
  ADD KEY `fk_OrderBook_Order1_idx` (`Order_idOrder`),
  ADD KEY `fk_OrderBook_Book1_idx` (`Product_idProduct`);

--
-- Indeksy dla tabeli `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`idProduct`);

--
-- Indeksy dla tabeli `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`idRole`);

--
-- Indeksy dla tabeli `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idUser`),
  ADD KEY `fk_User_Role_idx` (`Role_idRole`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `myorder`
--
ALTER TABLE `myorder`
  MODIFY `idOrder` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `orderproduct`
--
ALTER TABLE `orderproduct`
  MODIFY `idOrderProduct` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `product`
--
ALTER TABLE `product`
  MODIFY `idProduct` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `role`
--
ALTER TABLE `role`
  MODIFY `idRole` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `user`
--
ALTER TABLE `user`
  MODIFY `idUser` int NOT NULL AUTO_INCREMENT;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `myorder`
--
ALTER TABLE `myorder`
  ADD CONSTRAINT `fk_Order_User1` FOREIGN KEY (`User_idUser`) REFERENCES `user` (`idUser`);

--
-- Ograniczenia dla tabeli `orderproduct`
--
ALTER TABLE `orderproduct`
  ADD CONSTRAINT `fk_OrderBook_Book1` FOREIGN KEY (`Product_idProduct`) REFERENCES `product` (`idProduct`),
  ADD CONSTRAINT `fk_OrderBook_Order1` FOREIGN KEY (`Order_idOrder`) REFERENCES `myorder` (`idOrder`);

--
-- Ograniczenia dla tabeli `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_User_Role` FOREIGN KEY (`Role_idRole`) REFERENCES `role` (`idRole`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
