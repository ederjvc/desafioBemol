CREATE TABLE IF NOT EXISTS `usuario` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nome` varchar(200),
    `email` varchar(50),
    `data_nascimento` timestamp,
    `lagradouro` varchar(200)`,
    `numero` varchar(10),
    `complemento` varchar(200)`,
    `cep` varchar(15),
    `bairro` varchar(100),
    `cidade` varchar(100),
    `uf` varchar(20)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;