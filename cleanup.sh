#!/bin/bash
rm -rf .gradle/2.10/taskArtifacts/*
rm -rf core/.settings
rm -rf desktop/.settings
rm -rf .settings
gradle cleanEclipse
