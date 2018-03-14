## What is this?
This repository contains the source code of "OSV broncode programma 4 en 5 versie 2.21.4" as downloaded from kiesraad.nl on 13 March 2018.

https://www.kiesraad.nl/verkiezingen/osv-en-eml/ondersteunende-software-verkiezingen-osv
https://www.kiesraad.nl/verkiezingen/adviezen-en-publicaties/formulieren/2016/osv/osv-bestanden/osv-broncode-programma-4-en-5-versie-2.21.4

## Why?
Dutch elections rely on this code. I wanted to see what it looked like, and analyse it using lgtm.com.

## How do I build/compile this?
The original source archive (see commit [43d1692](https://github.com/sjvs/ondersteunende-software-verkiezingen/commit/43d1692bff975ff54f58a00dfc7f03ee8e404c22)) contains various Eclipse projects with Java code. If you use Eclipse, you may be able to import the projects straight into your IDE.

Note that none of the dependencies (JAR files) were included in the published archive, and it was in many cases unclear which versions of these dependencies should be used to compile and/or run the code. I added the 'jars' directory, which contains a set of JAR files that made it almost possible for me to build the software: [I had to change one line of code](https://github.com/sjvs/ondersteunende-software-verkiezingen/commit/879b48b26fac99dfb7c1f2306ae09ea771c0a669).

## How do I run this?
I don't know, but it will almost likely not run in its current state. Things to keep in mind:

1. I'm not actually interested in running this code; I wanted to have a look at it and analyse it using lgtm.com. If you have improvements to the build process or dependencies that make this software work: feel free to file a pull request!
2. For many dependencies I had to guess which versions were required exactly to make the code build. You may well get errors at runtime if you try to run this code because of (1) I guessed the version of one or more dependencies wrongly, and (2) missing run-time dependencies.
3. [I had to change one line of code](https://github.com/sjvs/ondersteunende-software-verkiezingen/commit/879b48b26fac99dfb7c1f2306ae09ea771c0a669) to make this compile.

## What's the license?
I don't know. The code was published by the Dutch Kiesraad authority, but a license was not specified. If you think I violated the terms of a license, please let me know.