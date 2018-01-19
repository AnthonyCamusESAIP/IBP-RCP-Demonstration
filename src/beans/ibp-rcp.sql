CREATE DATABASE IF NOT EXISTS `ibp-rcp`;
USE `ibp-rcp`;
CREATE TABLE `campagne` (
  `idCampagne` int(11) NOT NULL,
  `nomCampagne` varchar(255) NOT NULL,
  `idProjet` int(11) NOT NULL
);
CREATE TABLE `projet` (
  `idProjet` int(11) NOT NULL,
  `nomProjet` varchar(255) NOT NULL,
  `idVersion` int(11) NOT NULL
);
CREATE TABLE `test` (
  `idTest` int(11) NOT NULL,
  `date` date NOT NULL,
  `heure` time NOT NULL,
  `statut` enum('N/A','Passed','Failed','Not Completed') NOT NULL,
  `idCampagne` int(11) NOT NULL,
  `idTesteur` int(11) NOT NULL,
  `nomTest` varchar(255) NOT NULL
);
CREATE TABLE `testeur` (
  `idTesteur` int(11) NOT NULL,
  `nomTesteur` varchar(50) NOT NULL
);
CREATE TABLE `version` (
  `idVersion` int(11) NOT NULL,
  `nomVersion` varchar(20) NOT NULL
);
INSERT INTO `version` (`idVersion`, `nomVersion`) VALUES
(1, 'Version0');
ALTER TABLE `campagne`
  ADD PRIMARY KEY (`idCampagne`),
  ADD KEY `campagne_ibfk_1` (`idProjet`);
ALTER TABLE `projet`
  ADD PRIMARY KEY (`idProjet`),
  ADD KEY `nomProjet` (`nomProjet`),
  ADD KEY `idVersion` (`idVersion`);
ALTER TABLE `test`
  ADD PRIMARY KEY (`idTest`),
  ADD KEY `test_ibfk_1` (`idCampagne`),
  ADD KEY `test_ibfk_3` (`idTesteur`);
ALTER TABLE `testeur`
  ADD PRIMARY KEY (`idTesteur`);
ALTER TABLE `version`
  ADD PRIMARY KEY (`idVersion`);
ALTER TABLE `campagne`
  MODIFY `idCampagne` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=634;
ALTER TABLE `projet`
  MODIFY `idProjet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;
ALTER TABLE `test`
  MODIFY `idTest` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=247;
ALTER TABLE `testeur`
  MODIFY `idTesteur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=229;
ALTER TABLE `version`
  MODIFY `idVersion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
ALTER TABLE `campagne`
  ADD CONSTRAINT `campagne_ibfk_1` FOREIGN KEY (`idProjet`) REFERENCES `projet` (`idProjet`);
ALTER TABLE `projet`
  ADD CONSTRAINT `projet_ibfk_1` FOREIGN KEY (`idVersion`) REFERENCES `version` (`idVersion`);
ALTER TABLE `test`
  ADD CONSTRAINT `test_ibfk_1` FOREIGN KEY (`idCampagne`) REFERENCES `campagne` (`idCampagne`),
  ADD CONSTRAINT `test_ibfk_3` FOREIGN KEY (`idTesteur`) REFERENCES `testeur` (`idTesteur`);
COMMIT;