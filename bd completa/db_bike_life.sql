-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 25-08-2024 a las 18:39:44
-- Versión del servidor: 8.0.30
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_bl_sindatos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id_categoria_PK` int NOT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id_categoria_PK`, `nombre`) VALUES
(17, 'LLANTA PISTA '),
(21, 'RIN '),
(22, 'BICICLETA MONTAÑA '),
(23, 'BICICLETA RUTA'),
(36, 'LLANTA MONTAÑA'),
(37, 'FRENO'),
(38, 'LLANTA MONTAÑA'),
(39, 'TENEDOR'),
(40, 'NEUMATICOS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `documento` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallepedidos`
--

CREATE TABLE `detallepedidos` (
  `id_detalle_PK` int NOT NULL,
  `id_pedido_FK` int NOT NULL,
  `id_producto_FK` int NOT NULL,
  `cantidad` int NOT NULL,
  `precio_sin_iva` float NOT NULL,
  `precio_con_iva` float NOT NULL,
  `precio_iva` float NOT NULL,
  `porcentaje_IVA` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `id_pedido_PK` int NOT NULL,
  `id_cliente_FK` int NOT NULL,
  `id_usuario_FK` int NOT NULL,
  `fecha_pedido` date NOT NULL,
  `total` float NOT NULL,
  `tipo_pedido` enum('venta','reparacion') NOT NULL DEFAULT 'venta',
  `mano_obra` float DEFAULT '0',
  `fecha_entrega_pedido` date DEFAULT NULL,
  `descripcion` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id_producto_PK` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `precioCompra` float DEFAULT NULL,
  `precioVenta` float DEFAULT NULL,
  `stock` int NOT NULL,
  `Categoria_FK` int DEFAULT NULL,
  `Proveedor_FK` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id_producto_PK`, `nombre`, `precioCompra`, `precioVenta`, `stock`, `Categoria_FK`, `Proveedor_FK`) VALUES
(36, 'RIN 21 ', 10000, 14000, 15, 21, 10),
(37, 'RIN 27', 12000, 18000, 13, 21, 13),
(38, 'LLANTA RIN 27', 20000, 25000, 10, 17, 11),
(39, 'TENEDOR RIN 26', 40000, 45000, 0, 39, 10),
(40, 'BICICLETA NIÑO RIN 21', 600000, 650000, 9, 22, 11),
(41, 'RIN 29', 30000, 32000, 2, 21, 11),
(42, 'FRENO DISCO', 30000, 35000, 18, 37, 11),
(43, 'FRENO DISCO ', 35000, 40000, 21, 37, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `id_proveedor_PK` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `contacto_nombre` varchar(100) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`id_proveedor_PK`, `nombre`, `contacto_nombre`, `telefono`, `email`) VALUES
(10, 'TREK', 'Pepito Perez', '3201524544', 'correo@gmail.com'),
(11, 'SHIMANO', 'Luca Sexto', '3212458652', 'luca@gmail.com'),
(12, 'GW', 'Mke Tayson', '3224558862', 'mike11@gmail.com'),
(13, 'PIRINEOS', 'Ricardo Narizon', '3124584752', 'correo@correo.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reportes`
--

CREATE TABLE `reportes` (
  `fechaReporte` date DEFAULT NULL,
  `fechaInicioReporte` date DEFAULT NULL,
  `fechaFinReporte` date DEFAULT NULL,
  `usuarioGenerador` int DEFAULT NULL,
  `tipoReporte` enum('VENTA','REPARACION') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `reportes`
--

INSERT INTO `reportes` (`fechaReporte`, `fechaInicioReporte`, `fechaFinReporte`, `usuarioGenerador`, `tipoReporte`) VALUES
('2024-08-22', '2024-08-22', '2024-08-22', 1102548251, 'VENTA'),
('2024-08-22', '2023-08-22', '2024-08-22', 1102548251, 'VENTA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id_PK` int NOT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id_PK`, `nombre`) VALUES
(1, 'ADMIN'),
(2, 'VENDEDOR');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `identificacion_PK` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `edad` int NOT NULL,
  `contrasena` varchar(250) NOT NULL,
  `correo` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `estado` enum('ACTIVO','INACTIVO') DEFAULT NULL,
  `id_rol_FK` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`identificacion_PK`, `nombre`, `apellido`, `edad`, `contrasena`, `correo`, `telefono`, `estado`, `id_rol_FK`) VALUES
(1102548251, 'Juan ', 'Olave', 18, '$2a$10$WSzSGwhyjV2qgQg7pCWBxuqv3rb.51jRMI3xPr.psdwSy1yTlSDw2', 'olavejuan1805@gmail.com.co', '3229126084', 'ACTIVO', 1),
(1132590523, 'Enzy Zulay', 'Angarita Bermudez', 15, '$2a$10$aoDmdWicX1w9EwYh.nSjWegAi4XQqmnBHw9TayMYJrqUCqA5y81gi', 'enzy@gmail.com', '3229876344', 'ACTIVO', 1),
(1234546544, 'Juan', 'Barrera', 24, '$2a$10$p46XTfMCWf1STuV8vfZ4aeSEyBKM9RMz.86r/vQeahm0C0KkadSJu', 'correo@correo.con', '3222434433', 'INACTIVO', 2),
(1234567890, 'Juan', 'Olave', 19, '$2a$10$B.0ka7J3e4fLBE7lI1eGC.c1SXMFio8zywPY61YZSoIwK/HLrL302', 'juan@correo.com', '1234567890', 'INACTIVO', 1),
(1234567899, 'Juancho', 'Macario', 21, '$2a$10$kSA2s/hpmXWFCfiGqVo/yeU.RjdlTSTVxrsH.gi9B1Hiaq1P84OHS', 'correo@correo.com', '3224234234', 'INACTIVO', 1),
(1234567999, 'Juancho', 'Olave', 18, '$2a$10$pVt9tWn.gS/WBkpsJpe/s.pequhPxOphtuG5p4SE9qR6MXQcLFcbm', 'correo@correo.com', '3322254848', 'INACTIVO', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id_categoria_PK`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`documento`);

--
-- Indices de la tabla `detallepedidos`
--
ALTER TABLE `detallepedidos`
  ADD PRIMARY KEY (`id_detalle_PK`),
  ADD KEY `id_pedido` (`id_pedido_FK`),
  ADD KEY `id_producto` (`id_producto_FK`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id_pedido_PK`),
  ADD KEY `id_cliente` (`id_cliente_FK`),
  ADD KEY `id_usuario` (`id_usuario_FK`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id_producto_PK`),
  ADD KEY `Categoria_FK` (`Categoria_FK`),
  ADD KEY `Proveedor_FK` (`Proveedor_FK`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`id_proveedor_PK`);

--
-- Indices de la tabla `reportes`
--
ALTER TABLE `reportes`
  ADD KEY `usuarioGenerador` (`usuarioGenerador`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id_PK`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`identificacion_PK`),
  ADD KEY `fk_usuarios_roles` (`id_rol_FK`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id_categoria_PK` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT de la tabla `detallepedidos`
--
ALTER TABLE `detallepedidos`
  MODIFY `id_detalle_PK` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id_pedido_PK` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id_producto_PK` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `id_proveedor_PK` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id_PK` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `fk_pedidos_usuarios` FOREIGN KEY (`id_usuario_FK`) REFERENCES `usuarios` (`identificacion_PK`),
  ADD CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`id_cliente_FK`) REFERENCES `clientes` (`documento`);

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`Categoria_FK`) REFERENCES `categorias` (`id_categoria_PK`),
  ADD CONSTRAINT `productos_ibfk_2` FOREIGN KEY (`Proveedor_FK`) REFERENCES `proveedores` (`id_proveedor_PK`);

--
-- Filtros para la tabla `reportes`
--
ALTER TABLE `reportes`
  ADD CONSTRAINT `reportes_ibfk_1` FOREIGN KEY (`usuarioGenerador`) REFERENCES `usuarios` (`identificacion_PK`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `fk_usuarios_roles` FOREIGN KEY (`id_rol_FK`) REFERENCES `rol` (`id_PK`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
