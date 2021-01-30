-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  sam. 30 jan. 2021 à 19:21
-- Version du serveur :  10.4.8-MariaDB
-- Version de PHP :  7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `rdvmedecin`
--

-- --------------------------------------------------------

--
-- Structure de la table `assignement`
--

CREATE TABLE `assignement` (
  `assignement_id` int(11) NOT NULL,
  `medecin_id` int(11) NOT NULL,
  `centre_id` int(11) NOT NULL,
  `specialite_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `centre`
--

CREATE TABLE `centre` (
  `centre_id` int(11) NOT NULL,
  `nom` varchar(60) NOT NULL,
  `adresse` varchar(60) NOT NULL,
  `code_postal` int(5) NOT NULL,
  `ville` varchar(25) NOT NULL,
  `telephone` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `centre`
--

INSERT INTO `centre` (`centre_id`, `nom`, `adresse`, `code_postal`, `ville`, `telephone`) VALUES
(1, 'Infirmerie Dauphine', 'Place du Maréchal de Lattre de Tassigny', 75016, 'Paris', '0122785210'),
(2, 'Centre Médical Dauphine', '37 Boulevard Lannes', 75116, 'Paris', '0140728707'),
(3, 'Alliance Vision Centre de santé Paris Est', '173 Boulevard de la Villette', 75010, 'Paris', '0142054242'),
(4, 'Centre médical Stalingrad', '3 Rue du Maroc', 75019, 'Paris', '0140056743'),
(5, 'Centre médical et dentaire du Canal', '4 Rue de Thionville', 75019, 'Paris', '0142402020'),
(6, 'Centre médical Cosem Saint-Lazare', '13 Rue de la Pépinière', 75008, 'Paris', '0158229000'),
(7, 'Cosem Saint-Michel', '3 Rue Thénard', 75005, 'Paris', '0153730303'),
(8, 'Centre Dentaire, Médical et Paramédical la Grange-aux-belles', '61 Rue de la Grange aux Belles', 75010, 'Paris', '0148037300'),
(9, 'Point Vision Paris Centre', '13 Boulevard de la Madeleine', 75001, 'Paris', '0184163944');

-- --------------------------------------------------------

--
-- Structure de la table `rdv`
--

CREATE TABLE `rdv` (
  `rdv_id` int(11) NOT NULL,
  `medecin_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `centre_id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `creneau` int(11) NOT NULL,
  `actif` tinyint(1) NOT NULL DEFAULT 1,
  `commentaire` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `specialite`
--

CREATE TABLE `specialite` (
  `specialite_id` int(11) NOT NULL,
  `specialite` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `specialite`
--

INSERT INTO `specialite` (`specialite_id`, `specialite`) VALUES
(1, 'Médecine générale'),
(2, 'Immunologie'),
(3, 'Cardiologie'),
(4, 'Chirurgie'),
(5, 'Dermatologie'),
(6, 'Gériatrie'),
(7, 'Gynécologie'),
(8, 'Hématologie'),
(9, 'Infectiologie'),
(10, 'Ophtalmologie'),
(11, 'Pédiatrie'),
(12, 'Orthopédie'),
(13, 'Neurologie'),
(14, 'Psychatrie'),
(15, 'Odontologie');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `utilisateur_id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `telephone` varchar(25) NOT NULL,
  `mail` varchar(100) NOT NULL,
  `naissance` year(4) NOT NULL,
  `sexe` varchar(10) NOT NULL,
  `adresse` varchar(60) NOT NULL,
  `code_postal` int(5) NOT NULL,
  `ville` varchar(25) NOT NULL,
  `role` varchar(100) NOT NULL,
  `actif` tinyint(1) NOT NULL DEFAULT 1,
  `motdepasse` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`utilisateur_id`, `nom`, `prenom`, `telephone`, `mail`, `naissance`, `sexe`, `adresse`, `code_postal`, `ville`, `role`, `actif`, `motdepasse`) VALUES
(1, 'xu', 'nicolas', '0622723426', 'nicolas.xu@dauphine.eu', 1997, 'homme', '265 rue du faubourg saint martin', 75010, 'Paris', 'PATIENT', 1, '$2a$12$Aevb3UsVyWTfi7Qtchg0fe/8BqAeiOx1RiqsR9df9mXk7yrIQZc3K'),
(2, 'monrocq', 'pierre', '0638487370', 'pierre.monrocq@dauphine.eu', 1998, 'femme', '16, Rue Louis Dain', 93400, 'Saint-Ouen-sur-Seine', 'MEDECIN', 1, '$2a$12$FYiEmzpMXTiTKCGe4Zdys.rCZxV6jRM1pJrsltxEqa1CxBh3l4Ex.'),
(3, 'admin', 'admin', '0144054405', 'admin@gmail.com', 0000, 'autre', '', 0, '', 'ADMIN', 1, '$2a$12$0M/7lU86joVBnnxk2M75quRLVB90spBmVKZAfdv/WPeWViSwUKEP6');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `assignement`
--
ALTER TABLE `assignement`
  ADD PRIMARY KEY (`assignement_id`),
  ADD KEY `fk_medecin_id` (`medecin_id`),
  ADD KEY `fk_centre_id` (`centre_id`),
  ADD KEY `fk_specialite_id` (`specialite_id`) USING BTREE;

--
-- Index pour la table `centre`
--
ALTER TABLE `centre`
  ADD PRIMARY KEY (`centre_id`);

--
-- Index pour la table `rdv`
--
ALTER TABLE `rdv`
  ADD PRIMARY KEY (`rdv_id`),
  ADD KEY `fk_centre_id` (`centre_id`),
  ADD KEY `fk_medecin_id` (`medecin_id`),
  ADD KEY `fk_patient_id` (`patient_id`);

--
-- Index pour la table `specialite`
--
ALTER TABLE `specialite`
  ADD PRIMARY KEY (`specialite_id`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`utilisateur_id`),
  ADD UNIQUE KEY `mail` (`mail`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `assignement`
--
ALTER TABLE `assignement`
  MODIFY `assignement_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `centre`
--
ALTER TABLE `centre`
  MODIFY `centre_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `rdv`
--
ALTER TABLE `rdv`
  MODIFY `rdv_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `specialite`
--
ALTER TABLE `specialite`
  MODIFY `specialite_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `utilisateur_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `assignement`
--
ALTER TABLE `assignement`
  ADD CONSTRAINT `fk_assignement_centre_id` FOREIGN KEY (`centre_id`) REFERENCES `centre` (`centre_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_assignement_medecin_id` FOREIGN KEY (`medecin_id`) REFERENCES `utilisateur` (`utilisateur_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_assignement_specialite_id` FOREIGN KEY (`specialite_id`) REFERENCES `specialite` (`specialite_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `rdv`
--
ALTER TABLE `rdv`
  ADD CONSTRAINT `fk_rdv_centre_id` FOREIGN KEY (`centre_id`) REFERENCES `centre` (`centre_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_rdv_medecin_id` FOREIGN KEY (`medecin_id`) REFERENCES `utilisateur` (`utilisateur_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_rdv_patient_id` FOREIGN KEY (`patient_id`) REFERENCES `utilisateur` (`utilisateur_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
