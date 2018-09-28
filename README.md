# KiWi: A corpus and concordance generation web application

A web application in Java that provides corpus and concordance generation functionality to registered users. The application is intended for professional translators, who once registered, can generate their own corpora by uploading txt, html or pdf files. The user can then search the corpus for one or more words in context. The full-text search algorithm will match any substring, not just seperate words. KiWi uses a suffix array to provide the core functionality. The implementation of the suffix array is by [Sedgwick & Kayne](https://algs4.cs.princeton.edu/63suffix/SuffixArrayX.java.html) The app was developed as my final project for the MSc Computer Science in Newcastle University and is not intended for commercial use.

![alt tag](https://user-images.githubusercontent.com/32340325/46225223-83c00680-c350-11e8-9116-3fce788fa20a.png)
![alt tag](https://user-images.githubusercontent.com/32340325/46225225-84589d00-c350-11e8-84dd-39782ea88158.png)
![alt tag](https://user-images.githubusercontent.com/32340325/46225224-84589d00-c350-11e8-9285-a2961e0fab78.png)


# Built With
•  Eclipse Oxygen IDE

![alt tag](https://user-images.githubusercontent.com/32340325/46225226-84f13380-c350-11e8-8830-42f914ac66ca.png)
![alt tag](https://user-images.githubusercontent.com/32340325/46225229-84f13380-c350-11e8-8285-2fa1e80a6c14.png)


# Dependencies

The app requires the Java 1.8.0_144 JDK, as well as the following dependencies: 

Server-side:
1. Apache  PDFBox  2.0.11  for  processing  PDF  files,  including  the 
following libraries: 
  •  fontbox-2.0.11.jar 
  •  pdfbox 2.0.11.jar 
  •  commons-text-1.4.jar 
  •  common-lang3.jar 
  •  commons-logging-1.1.2.jar 
2  The Jsoup library Jsoup-1.11.3.jar for processing html files. 
3.  The Apache Commons IO 2.6 File Utilities (commons-io-2.6.jar) 
4.  The Java Standard Tag Library (JSTL), jstl1.2.jar 
5.  The  JDBC  (mysql-connector-java-5.1.46.jar)  driver   



Client-side: 
1.  The Bootstrap 4.0 framework 
2.  The jQuery 3.3.1 framework 
3.  The jQuery validate plugin.  
4.  The JavaScript mark.js library for word highlighting 
5.   JavaScript ECMAScript 6 (2015) 
6.  The Popper.js library for modals. 


