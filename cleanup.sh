#!/bin/bash
rm -rf core/.settings
rm -rf desktop/.settings
rm -rf .settings
rm -rf core/bin
rm -rf desktop/bin
gradle cleanEclipse
rm -rf .gradle
