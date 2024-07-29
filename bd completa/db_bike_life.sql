-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 29-07-2024 a las 06:25:29
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
-- Base de datos: `db_bike_life`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id_categoria_PK` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `Rin` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `identificacion_PK` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL
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
  `precio_unitario` decimal(10,2) NOT NULL
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
  `total` decimal(10,2) NOT NULL,
  `tipo_pedido` enum('venta','reparacion') NOT NULL DEFAULT 'venta',
  `mano_obra` double DEFAULT '0',
  `fecha_entrega_pedido` date NOT NULL,
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
(1, 'Nombre del Producto', 100, 150, 10, NULL, NULL),
(19, 'Tensor 9 velocidades de rosca', 40000, 45000, 20, NULL, NULL),
(20, 'Tensor 9 velocidades de rosca', 40000, 45000, 20, NULL, NULL),
(21, 'Bicicleta niño 12', 0, 0, 20, NULL, NULL),
(22, 'Bicicleta niño 12', 0, 0, 20, NULL, NULL),
(23, 'Bicicleta Adulto rin 29', 0, 0, 10, NULL, NULL),
(24, 'Bicicleta Adulto rin 29', 0, 0, 10, NULL, NULL),
(25, 'Bicicleta aluminio ', 0, 0, 0, NULL, NULL),
(26, 'Bicicleta aluminio ', 0, 0, 0, NULL, NULL),
(27, 'ola', 0, 0, 0, NULL, NULL),
(28, 'ola', 0, 0, 0, NULL, NULL),
(29, 'CHAO', 0, 0, 0, NULL, NULL);

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id_PK` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `salario` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id_PK`, `nombre`, `salario`) VALUES
(1, 'ADMIN', 100),
(2, 'VENDEDOR', 100);

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
  `id_rol_FK` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`identificacion_PK`, `nombre`, `apellido`, `edad`, `contrasena`, `correo`, `telefono`, `id_rol_FK`) VALUES
(123, 'Juan', 'Olave', 19, '222222', 'olave@correo.com', '3229126084', 1),
(1234, 'Juan', 'Olave', 19, '12345', 'juan@correo.com', '1234567890', 2);

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
  ADD PRIMARY KEY (`identificacion_PK`);

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
  MODIFY `id_categoria_PK` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `detallepedidos`
--
ALTER TABLE `detallepedidos`
  MODIFY `id_detalle_PK` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id_pedido_PK` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id_producto_PK` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `id_proveedor_PK` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id_PK` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detallepedidos`
--
ALTER TABLE `detallepedidos`
  ADD CONSTRAINT `fk_detallepedidos_pedidos` FOREIGN KEY (`id_pedido_FK`) REFERENCES `pedidos` (`id_pedido_PK`),
  ADD CONSTRAINT `fk_detallepedidos_productos` FOREIGN KEY (`id_producto_FK`) REFERENCES `productos` (`id_producto_PK`);

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `fk_pedidos_clientes` FOREIGN KEY (`id_cliente_FK`) REFERENCES `clientes` (`identificacion_PK`),
  ADD CONSTRAINT `fk_pedidos_usuarios` FOREIGN KEY (`id_usuario_FK`) REFERENCES `usuarios` (`identificacion_PK`);

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`Categoria_FK`) REFERENCES `categorias` (`id_categoria_PK`),
  ADD CONSTRAINT `productos_ibfk_2` FOREIGN KEY (`Proveedor_FK`) REFERENCES `proveedores` (`id_proveedor_PK`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `fk_usuarios_roles` FOREIGN KEY (`id_rol_FK`) REFERENCES `rol` (`id_PK`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
