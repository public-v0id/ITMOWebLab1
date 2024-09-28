javac -cp .:../../../../../../output/fastcgi-lib.jar cores/*.java classes/*.java Main.java -d ../../../../../../output/
cd ../../../../../../output/
jar -cfm lab1.jar MANIFEST.mf ru/se/ifmo/web/lab1/Main.class ru/se/ifmo/web/lab1/cores/*.class ru/se/ifmo/web/lab1/classes/*.class com/fastcgi/FCGI*
cd ../../../
cp ITMOWebLab1/back/output/lab1.jar httpd-root/fcgi-bin/
java -DFCGI_PORT=24071 -jar httpd-root/fcgi-bin/lab1.jar
