-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.7.24 - MySQL Community Server (GPL)
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Copiando estrutura para tabela db_aula_2021.aluno
CREATE TABLE IF NOT EXISTS `aluno` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `matricula` varchar(20) COLLATE utf8mb4_bin NOT NULL DEFAULT '0',
  `nome` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `dt_nascimento` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Copiando dados para a tabela db_aula_2021.aluno: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` (`id`, `matricula`, `nome`, `dt_nascimento`) VALUES
	(1, '123456', 'Ana', '2000-04-28'),
	(2, '098765', 'Jair', '1998-03-12'),
	(3, '655555', 'Lisa', '2001-03-12');
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;

-- Copiando estrutura para tabela db_aula_2021.curso
CREATE TABLE IF NOT EXISTS `curso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) DEFAULT NULL,
  `departamento_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `departamento_id` (`departamento_id`),
  CONSTRAINT `curso_ibfk_1` FOREIGN KEY (`departamento_id`) REFERENCES `departamento` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela db_aula_2021.curso: ~7 rows (aproximadamente)
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` (`id`, `nome`, `departamento_id`) VALUES
	(1, 'Informática FIC', 1),
	(2, 'Engenharia Mecânica', 2),
	(3, 'Téc. em Informática', 1),
	(4, 'Téc. em Mecânica', 2),
	(5, 'Téc. em Alimentos', 5),
	(6, 'Ciência da Computação', 1),
	(7, 'Téc. em Moda', 7),
	(8, 'Téc. em Mecatrônica', null);
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;

-- Copiando estrutura para tabela db_aula_2021.departamento
CREATE TABLE IF NOT EXISTS `departamento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela db_aula_2021.departamento: ~7 rows (aproximadamente)
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
INSERT INTO `departamento` (`id`, `nome`) VALUES
	(1, 'Informática'),
	(2, 'Mecânica'),
	(3, 'Informática'),
	(4, 'Administração'),
	(5, 'Alimentos'),
	(6, 'Mecatronica'),
	(7, 'Moda');
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;

-- Copiando estrutura para tabela db_aula_2021.disciplina
CREATE TABLE IF NOT EXISTS `disciplina` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(64) NOT NULL DEFAULT 'A Definir',
  `ementa` varchar(512) NOT NULL DEFAULT 'Não cadastrada',
  `cpf_prof` bigint(20) NOT NULL,
  `curso_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cpf_prof` (`cpf_prof`),
  KEY `FK_curso_disciplina` (`curso_id`),
  CONSTRAINT `FK_curso_disciplina` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`),
  CONSTRAINT `disciplina_ibfk_1` FOREIGN KEY (`cpf_prof`) REFERENCES `professor` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela db_aula_2021.disciplina: ~10 rows (aproximadamente)
/*!40000 ALTER TABLE `disciplina` DISABLE KEYS */;
INSERT INTO `disciplina` (`id`, `nome`, `ementa`, `cpf_prof`, `curso_id`) VALUES
	(2, 'BD II', 'Ementa 2...', 9067549002, 1),
	(3, 'Redes', 'Ementa 1...', 54420066511, 6),
	(4, 'Eng. de Software', 'Ementa 2...', 9067549002, 6),
	(5, 'Banco de Dados I', 'Ementa 3...', 9067549002, 3),
	(6, 'História', 'Historia Geral', 55500055511, 2),
	(7, 'Banco de Dados', 'Fundamentos de Banco de Dados', 11120044410, 1),
	(9, 'Banco de Dados', 'Fundamentos de Banco de Dados', 9067549777, 6),
	(11, 'Alimentos', 'Engenharia de Alimentos', 50077733, 5),
	(12, 'Lógica de Programação', 'Fundamentos da Programação', 11120044410, 6),
	(14, 'Costura', 'Tecnologias da Moda', 78038204837, 7);
/*!40000 ALTER TABLE `disciplina` ENABLE KEYS */;

-- Copiando estrutura para tabela db_aula_2021.ingrediente
CREATE TABLE IF NOT EXISTS `ingrediente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `contem_glutem` tinyint(1) DEFAULT NULL,
  `contem_lactose` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela db_aula_2021.ingrediente: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `ingrediente` DISABLE KEYS */;
INSERT INTO `ingrediente` (`id`, `nome`, `contem_glutem`, `contem_lactose`) VALUES
	(1, 'Molho de Tomate', 1, 0),
	(2, 'Queijo', 1, 1),
	(3, 'Pepperoni', 1, 0),
	(4, 'Brócolis', 0, 0),
	(9, 'Frango', 1, 0),
	(10, 'Calabresa', 1, 0),
	(11, 'Queijo Zero', 0, 0),
	(12, 'Molho de Tomate Zero', 0, 0),
	(13, 'Azeitona', 0, 0);
/*!40000 ALTER TABLE `ingrediente` ENABLE KEYS */;

-- Copiando estrutura para tabela db_aula_2021.pizza
CREATE TABLE IF NOT EXISTS `pizza` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sabor` varchar(50) NOT NULL,
  `preco` decimal(5,2) DEFAULT NULL,
  `calorias` int(11) DEFAULT NULL,
  `contem_glutem` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela db_aula_2021.pizza: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `pizza` DISABLE KEYS */;
INSERT INTO `pizza` (`id`, `sabor`, `preco`, `calorias`, `contem_glutem`) VALUES
	(1, 'Calabresa', 44.50, 2200, 1),
	(2, 'Quatro Queijos', 48.00, 2600, 1),
	(3, 'Pepperoni', 54.40, 2500, 1),
	(4, 'Brócolis', 42.00, 1300, 0),
	(5, 'Pepperoni Especial', 54.40, 2800, 1);
/*!40000 ALTER TABLE `pizza` ENABLE KEYS */;

-- Copiando estrutura para tabela db_aula_2021.pizza_ingrediente
CREATE TABLE IF NOT EXISTS `pizza_ingrediente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pizza_id` int(11) NOT NULL,
  `ingrediente_id` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `unidade_medida` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pizza_id` (`pizza_id`),
  KEY `ingrediente_id` (`ingrediente_id`),
  CONSTRAINT `pizza_ingrediente_ibfk_1` FOREIGN KEY (`pizza_id`) REFERENCES `pizza` (`id`),
  CONSTRAINT `pizza_ingrediente_ibfk_2` FOREIGN KEY (`ingrediente_id`) REFERENCES `ingrediente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela db_aula_2021.pizza_ingrediente: ~12 rows (aproximadamente)
/*!40000 ALTER TABLE `pizza_ingrediente` DISABLE KEYS */;
INSERT INTO `pizza_ingrediente` (`id`, `pizza_id`, `ingrediente_id`, `quantidade`, `unidade_medida`) VALUES
	(1, 1, 1, 5, 'ml'),
	(2, 3, 1, 3, 'ml'),
	(3, 3, 3, 2, 'mg'),
	(4, 4, 4, 4, 'mg'),
	(5, 1, 2, 5, 'ml'),
	(6, 2, 2, 3, 'ml'),
	(7, 3, 2, 2, 'mg'),
	(8, 4, 12, 4, 'mg'),
	(9, 3, 1, 2, 'mg'),
	(10, 1, 10, 3, 'mg'),
	(11, 4, 11, 3, 'mg'),
	(12, 4, 13, 5, 'mg');
/*!40000 ALTER TABLE `pizza_ingrediente` ENABLE KEYS */;

-- Copiando estrutura para tabela db_aula_2021.professor
CREATE TABLE IF NOT EXISTS `professor` (
  `cpf` bigint(20) NOT NULL,
  `nome` varchar(128) DEFAULT NULL,
  `salario` decimal(7,2) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `departamento_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cpf`),
  KEY `FK_professor_departamento` (`departamento_id`),
  CONSTRAINT `FK_professor_departamento` FOREIGN KEY (`departamento_id`) REFERENCES `departamento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela db_aula_2021.professor: ~9 rows (aproximadamente)
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` (`cpf`, `nome`, `salario`, `data_nascimento`, `departamento_id`) VALUES
	(50077733, 'Alex', 5000.00, '1989-11-08', 1),
	(557549111, 'Flavia', 5280.00, '2000-05-20', 1),
	(9067549002, 'Joao', 2640.00, '2000-10-22', 7),
	(9067549777, 'Jose da Silva', 5280.00, '1970-05-18', 6),
	(9067549999, 'Joaquim', 3960.00, '1990-11-23', 2),
	(11120044410, 'Lucas', 5000.00, '1989-11-08', 2),
	(54420066511, 'Leandro', 3500.00, '1995-10-08', 3),
	(55500055511, 'Ana', 2000.00, '2002-01-05', 1),
	(78038204837, 'Maria', 3000.00, '1998-12-20', 5);
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
