-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Gen 28, 2018 alle 17:02
-- Versione del server: 10.1.28-MariaDB
-- Versione PHP: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `progettodatabase`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `autista`
--

CREATE TABLE `autista` (
  `CodiceFiscale` varchar(45) NOT NULL,
  `Cognome` varchar(45) NOT NULL,
  `Nome` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `autista`
--

INSERT INTO `autista` (`CodiceFiscale`, `Cognome`, `Nome`) VALUES
('DCRMRC88B02A662T', 'De Carlo', 'Marco'),
('DVVRRT80A01H501S', 'De Vivo', 'Roberto'),
('RBRSLS80A41B157A', 'Sallusti', 'Roberta'),
('RMNRSL77D56A662B', 'Romano', 'Rossella');

-- --------------------------------------------------------

--
-- Struttura della tabella `autobus`
--

CREATE TABLE `autobus` (
  `NumeroAutobus` int(10) NOT NULL,
  `Modello` varchar(45) NOT NULL,
  `NumeroPosti` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `autobus`
--

INSERT INTO `autobus` (`NumeroAutobus`, `Modello`, `NumeroPosti`) VALUES
(2, 'Alfa Romeo mille AU7P - Pistoiesi AU 811', 40),
(4, 'MAN A21', 60),
(12, 'BB 3001.12', 30),
(16, 'ALFA Romeo mille AU10P - Menarini Monocar', 38);

-- --------------------------------------------------------

--
-- Struttura della tabella `biglietto`
--

CREATE TABLE `biglietto` (
  `Fascia` varchar(45) NOT NULL,
  `Prezzo` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `biglietto`
--

INSERT INTO `biglietto` (`Fascia`, `Prezzo`) VALUES
('ExtraUrbano', 1.5),
('Urbano', 1.3),
('Urbano Altri Comuni', 1.3);

-- --------------------------------------------------------

--
-- Struttura della tabella `controllore`
--

CREATE TABLE `controllore` (
  `Codice` int(11) NOT NULL,
  `Nome` varchar(45) NOT NULL,
  `Cognome` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `controllore`
--

INSERT INTO `controllore` (`Codice`, `Nome`, `Cognome`) VALUES
(1244, 'Roberto', 'Galli'),
(3483, 'Mario', 'DeCrescenzo'),
(4322, 'Priscilla', 'Raso'),
(7823, 'Brigida', 'Verdi'),
(8473, 'Mirko', 'Acqua'),
(8942, 'Francesco', 'Romano');

-- --------------------------------------------------------

--
-- Struttura della tabella `orariarrivo`
--

CREATE TABLE `orariarrivo` (
  `OrarioA` time NOT NULL,
  `Tratta` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `orariarrivo`
--

INSERT INTO `orariarrivo` (`OrarioA`, `Tratta`) VALUES
('09:40:00', '2 Ligea-Sordina'),
('10:20:00', '2 Ligea-Sordina'),
('11:00:00', '16 Pastena-Giovi-Casa Manzo'),
('11:00:00', '2 Ligea-Sordina'),
('11:00:00', '4 Pompei-Salerno'),
('11:30:00', '16 Pastena-Giovi-Casa Manzo'),
('11:40:00', '2 Ligea-Sordina'),
('12:00:00', '12 S.Eustachio-P.za S.Francesco'),
('12:00:00', '2 Ligea-Sordina'),
('12:40:00', '2 Ligea-Sordina'),
('14:00:00', '12 S.Eustachio-P.za S.Francesco'),
('14:20:00', '2 Ligea-Sordina'),
('15:00:00', '2 Ligea-Sordina'),
('16:00:00', '12 S.Eustachio-P.za S.Francesco'),
('16:30:00', '16 Pastena-Giovi-Casa Manzo'),
('16:40:00', '4 Pompei-Salerno'),
('18:00:00', '12 S.Eustachio-P.za S.Francesco'),
('20:00:00', '4 Pompei-Salerno'),
('22:00:00', '12 S.Eustachio-P.za S.Francesco'),
('23:50:00', '12 S.Eustachio-P.za S.Francesco');

-- --------------------------------------------------------

--
-- Struttura della tabella `oraripartenza`
--

CREATE TABLE `oraripartenza` (
  `OrarioP` time NOT NULL,
  `Tratta` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `oraripartenza`
--

INSERT INTO `oraripartenza` (`OrarioP`, `Tratta`) VALUES
('07:40:00', '2 Ligea-Sordina'),
('08:00:00', '16 Pastena-Giovi-Casa Manzo'),
('08:00:00', '4 Pompei-Salerno'),
('08:20:00', '2 Ligea-Sordina'),
('09:00:00', '2 Ligea-Sordina'),
('09:40:00', '2 Ligea-Sordina'),
('10:00:00', '12 S.Eustachio-P.za S.Francesco'),
('10:00:00', '16 Pastena-Giovi-Casa Manzo'),
('10:00:00', '4 Pompei-Salerno'),
('10:20:00', '2 Ligea-Sordina'),
('11:00:00', '2 Ligea-Sordina'),
('12:00:00', '12 S.Eustachio-P.za S.Francesco'),
('12:20:00', '2 Ligea-Sordina'),
('13:00:00', '2 Ligea-Sordina'),
('13:00:00', '4 Pompei-Salerno'),
('14:00:00', '12 S.Eustachio-P.za S.Francesco'),
('15:00:00', '16 Pastena-Giovi-Casa Manzo'),
('15:00:00', '4 Pompei-Salerno'),
('16:00:00', '12 S.Eustachio-P.za S.Francesco'),
('18:00:00', '4 Pompei-Salerno'),
('20:00:00', '12 S.Eustachio-P.za S.Francesco'),
('22:00:00', '12 S.Eustachio-P.za S.Francesco');

-- --------------------------------------------------------

--
-- Struttura della tabella `passeggeromultato`
--

CREATE TABLE `passeggeromultato` (
  `CodiceFiscale` varchar(45) NOT NULL,
  `Nome` varchar(45) NOT NULL,
  `Cognome` varchar(45) NOT NULL,
  `Indirizzo` varchar(45) NOT NULL,
  `RecapitoTelefonico` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `passeggeromultato`
--

INSERT INTO `passeggeromultato` (`CodiceFiscale`, `Nome`, `Cognome`, `Indirizzo`, `RecapitoTelefonico`) VALUES
('FRNMRC76C15A089O', 'Marco', 'Franco', 'Via Dominicis 24', '3287465782'),
('MROMRA81E20A271G', 'Mario', 'Moro', 'Via Manzoni 23', '32847563123'),
('MSTDNL84D08D612B', 'Daniele', 'Maestri', 'Via Rossi 12', '3294331231');

-- --------------------------------------------------------

--
-- Struttura della tabella `sanzioni`
--

CREATE TABLE `sanzioni` (
  `Codice` int(10) NOT NULL,
  `Controllore` int(15) NOT NULL,
  `DataEmissione` date NOT NULL,
  `Importo` double NOT NULL,
  `Passeggero` varchar(45) NOT NULL,
  `Scadenza` date NOT NULL,
  `Tratta` varchar(45) NOT NULL,
  `Causale` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `sanzioni`
--

INSERT INTO `sanzioni` (`Codice`, `Controllore`, `DataEmissione`, `Importo`, `Passeggero`, `Scadenza`, `Tratta`, `Causale`) VALUES
(321, 3483, '2017-12-02', 30, 'MSTDNL84D08D612B', '2017-12-31', '2 Ligea-Sordina', 'Senza biglietto'),
(948, 3483, '2017-01-12', 25, 'FRNMRC76C15A089O', '2017-12-12', '4 Pompei-Salerno', 'Biglietto non obliterato'),
(1234, 8942, '2017-12-12', 40, 'MROMRA81E20A271G', '2018-12-03', '2 Ligea-Sordina', 'Senza biglietto'),
(4213, 7823, '2017-12-14', 38.5, 'MSTDNL84D08D612B', '2018-01-02', '16 Pastena-Giovi-Casa Manzo', 'Biglietto non obliterato');

-- --------------------------------------------------------

--
-- Struttura della tabella `tratta`
--

CREATE TABLE `tratta` (
  `NomeTratta` varchar(45) NOT NULL,
  `Biglietto` varchar(20) NOT NULL,
  `NumeroAutobus` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `tratta`
--

INSERT INTO `tratta` (`NomeTratta`, `Biglietto`, `NumeroAutobus`) VALUES
('12 S.Eustachio-P.za S.Francesco', 'Urbano', 12),
('16 Pastena-Giovi-Casa Manzo', 'Urbano', 16),
('2 Ligea-Sordina', 'Urbano', 2),
('4 Pompei-Salerno', 'ExtraUrbano', 4);

-- --------------------------------------------------------

--
-- Struttura della tabella `turno`
--

CREATE TABLE `turno` (
  `Data` date NOT NULL,
  `Ora` time NOT NULL,
  `Autista` varchar(45) NOT NULL,
  `Autobus` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `turno`
--

INSERT INTO `turno` (`Data`, `Ora`, `Autista`, `Autobus`) VALUES
('2016-12-01', '10:00:00', 'RBRSLS80A41B157A', 2),
('2017-12-12', '10:00:00', 'DCRMRC88B02A662T', 2),
('2017-12-12', '18:00:00', 'RBRSLS80A41B157A', 2),
('2017-08-12', '19:00:00', 'RBRSLS80A41B157A', 4),
('2017-08-21', '09:00:00', 'RMNRSL77D56A662B', 4),
('2017-10-12', '15:00:00', 'DCRMRC88B02A662T', 4),
('2017-12-12', '08:00:00', 'RBRSLS80A41B157A', 4),
('2017-12-12', '14:00:00', 'DVVRRT80A01H501S', 16);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `autista`
--
ALTER TABLE `autista`
  ADD PRIMARY KEY (`CodiceFiscale`);

--
-- Indici per le tabelle `autobus`
--
ALTER TABLE `autobus`
  ADD PRIMARY KEY (`NumeroAutobus`);

--
-- Indici per le tabelle `biglietto`
--
ALTER TABLE `biglietto`
  ADD PRIMARY KEY (`Fascia`);

--
-- Indici per le tabelle `controllore`
--
ALTER TABLE `controllore`
  ADD PRIMARY KEY (`Codice`);

--
-- Indici per le tabelle `orariarrivo`
--
ALTER TABLE `orariarrivo`
  ADD PRIMARY KEY (`OrarioA`,`Tratta`),
  ADD KEY `fk_trattaA` (`Tratta`);

--
-- Indici per le tabelle `oraripartenza`
--
ALTER TABLE `oraripartenza`
  ADD PRIMARY KEY (`OrarioP`,`Tratta`),
  ADD KEY `fk_trattaP` (`Tratta`);

--
-- Indici per le tabelle `passeggeromultato`
--
ALTER TABLE `passeggeromultato`
  ADD PRIMARY KEY (`CodiceFiscale`);

--
-- Indici per le tabelle `sanzioni`
--
ALTER TABLE `sanzioni`
  ADD PRIMARY KEY (`Codice`),
  ADD KEY `fk_Control` (`Controllore`),
  ADD KEY `fk_Pass` (`Passeggero`),
  ADD KEY `fk_trat` (`Tratta`);

--
-- Indici per le tabelle `tratta`
--
ALTER TABLE `tratta`
  ADD PRIMARY KEY (`NomeTratta`),
  ADD KEY `Biglietto` (`Biglietto`),
  ADD KEY `fk_Numero` (`NumeroAutobus`);

--
-- Indici per le tabelle `turno`
--
ALTER TABLE `turno`
  ADD PRIMARY KEY (`Data`,`Ora`,`Autista`),
  ADD KEY `fk_autista` (`Autista`),
  ADD KEY `fk_Autobus` (`Autobus`);

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `orariarrivo`
--
ALTER TABLE `orariarrivo`
  ADD CONSTRAINT `fk_trattaA` FOREIGN KEY (`Tratta`) REFERENCES `tratta` (`NomeTratta`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `orariarrivo_ibfk_1` FOREIGN KEY (`Tratta`) REFERENCES `tratta` (`NomeTratta`);

--
-- Limiti per la tabella `oraripartenza`
--
ALTER TABLE `oraripartenza`
  ADD CONSTRAINT `fk_trattaP` FOREIGN KEY (`Tratta`) REFERENCES `tratta` (`NomeTratta`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `oraripartenza_ibfk_1` FOREIGN KEY (`Tratta`) REFERENCES `tratta` (`NomeTratta`);

--
-- Limiti per la tabella `sanzioni`
--
ALTER TABLE `sanzioni`
  ADD CONSTRAINT `fk_Control` FOREIGN KEY (`Controllore`) REFERENCES `controllore` (`Codice`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_Pass` FOREIGN KEY (`Passeggero`) REFERENCES `passeggeromultato` (`CodiceFiscale`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_trat` FOREIGN KEY (`Tratta`) REFERENCES `tratta` (`NomeTratta`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sanzioni_ibfk_1` FOREIGN KEY (`Controllore`) REFERENCES `controllore` (`Codice`),
  ADD CONSTRAINT `sanzioni_ibfk_2` FOREIGN KEY (`Passeggero`) REFERENCES `passeggeromultato` (`CodiceFiscale`),
  ADD CONSTRAINT `sanzioni_ibfk_3` FOREIGN KEY (`Tratta`) REFERENCES `tratta` (`NomeTratta`);

--
-- Limiti per la tabella `tratta`
--
ALTER TABLE `tratta`
  ADD CONSTRAINT `fk_Biglietto` FOREIGN KEY (`Biglietto`) REFERENCES `biglietto` (`Fascia`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_Numero` FOREIGN KEY (`NumeroAutobus`) REFERENCES `autobus` (`NumeroAutobus`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tratta_ibfk_1` FOREIGN KEY (`NumeroAutobus`) REFERENCES `autobus` (`NumeroAutobus`),
  ADD CONSTRAINT `tratta_ibfk_2` FOREIGN KEY (`Biglietto`) REFERENCES `biglietto` (`Fascia`);

--
-- Limiti per la tabella `turno`
--
ALTER TABLE `turno`
  ADD CONSTRAINT `fk_Autobus` FOREIGN KEY (`Autobus`) REFERENCES `autobus` (`NumeroAutobus`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_autista` FOREIGN KEY (`Autista`) REFERENCES `autista` (`CodiceFiscale`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `turno_ibfk_1` FOREIGN KEY (`Autista`) REFERENCES `autista` (`CodiceFiscale`),
  ADD CONSTRAINT `turno_ibfk_2` FOREIGN KEY (`Autobus`) REFERENCES `autobus` (`NumeroAutobus`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
