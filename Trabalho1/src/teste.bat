@echo off
title Teste para trabalho de IASA
javac Controller.java Environment.java Direction.java Agent.java ./tiles/Tile.java ./tiles/Crossed.java ./tiles/Empty.java ./tiles/InitialPoint.java ./tiles/Object.java ./tiles/Target.java
java Controller
pause