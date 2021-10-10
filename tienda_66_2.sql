-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-10-2021 a las 14:59:42
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Base de datos: `tienda_66_2`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `ID` int(10) UNSIGNED NOT NULL,
  `CEDULA` int(10) NOT NULL,
  `NOMBRE` varchar(64) NOT NULL,
  `DIRECCION` varchar(64) NOT NULL,
  `TELEFONO` int(12) UNSIGNED NOT NULL,
  `CORREO` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`ID`, `CEDULA`, `NOMBRE`, `DIRECCION`, `TELEFONO`, `CORREO`) VALUES
(1, 3, 'Pepito Wahssermanheit', 'Av. 64-47 SUR 17D', 1231234512, 'holamundo@gmail.com'),
(5, 123456, 'Euserbio Guataneme', 'cll 23-44 sur n 37-60', 3223583750, 'EG@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `CODIGO` bigint(20) UNSIGNED NOT NULL,
  `NOMBRE` varchar(50) NOT NULL,
  `NIT` bigint(20) UNSIGNED NOT NULL,
  `PRECIO_COMPRA` double NOT NULL,
  `IVA_COMPRA` double NOT NULL,
  `PRECIO_VENTA` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`CODIGO`, `NOMBRE`, `NIT`, `PRECIO_COMPRA`, `IVA_COMPRA`, `PRECIO_VENTA`) VALUES
(1, 'Melocotones', 1, 25505, 19, 30351),
(2, 'Manzanas', 3, 18108, 19, 21549),
(4, 'Lechuga', 3, 29788, 19, 35448),
(5, 'Tomates', 1, 12739, 19, 15159),
(6, 'Calabaza', 1, 21315, 19, 25365),
(7, 'Apio', 2, 19249, 19, 22906),
(8, 'Pepino', 2, 10958, 19, 13040),
(9, 'Champiñones', 2, 11046, 19, 13145),
(10, 'Leche', 5, 21150, 19, 25169),
(11, 'Queso', 5, 26571, 19, 31619),
(12, 'Huevos', 2, 12445, 19, 14810),
(13, 'Requesón', 1, 14329, 19, 17052),
(14, 'Crema agria', 1, 14856, 19, 17679),
(15, 'Yogur', 5, 14941, 19, 17780),
(16, 'Ternera', 5, 29335, 19, 34909),
(17, 'Salmón salvaje', 5, 11878, 19, 14135),
(18, 'Patas de cangrejo', 1, 29951, 19, 35642);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `ID` int(10) UNSIGNED NOT NULL,
  `NIT` int(10) NOT NULL,
  `NOMBRE` varchar(64) NOT NULL,
  `DIRECCION` varchar(64) NOT NULL,
  `TELEFONO` int(12) UNSIGNED NOT NULL,
  `CIUDAD` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`ID`, `NIT`, `NOMBRE`, `DIRECCION`, `TELEFONO`, `CIUDAD`) VALUES
(1, 1, 'Carambola S.A.', 'cll 48 C no 17-28', 8888888, 'Medellín'),
(3, 2, 'Mundoaventura', 'Cll aventura', 5, 'Cúcuta'),
(4, 3, 'Constructores S.A', 'cll 54 C norte', 3124554123, 'Bogotá'),
(8, 4, 'Alpinito & asociados Ltda.', 'Cr 47-72 h 24', 314290334, 'Bogotá');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `ID` int(10) UNSIGNED NOT NULL,
  `CEDULA` int(10) NOT NULL,
  `NOMBRE` varchar(64) NOT NULL,
  `CORREO` varchar(64) NOT NULL,
  `USUARIO` varchar(32) NOT NULL,
  `CONTRASENIA` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`ID`, `CEDULA`, `NOMBRE`, `CORREO`, `USUARIO`, `CONTRASENIA`) VALUES
(1, 1111111111, 'Kevin', 'k@gmail.com', 'kevin pro', 'Contraseña'),
(3, 22342342, 'Andres Martínez', 'am@gmail.com', 'admin123', '12345');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `CEDULA` (`CEDULA`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`CODIGO`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `NIT` (`NIT`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `USUARIO` (`USUARIO`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
