# Text-Simplifier
A Text Simplifier in Java based around the Newspeak concept first coined by George Orwell.

GMITbabble - Common Parlance Text Simplifier v0.1

This text simplifier translates direct console input from the user or takes input from a file and writes an output file with the specified name to the current directory. 
The text simplifier builds a mapping of thesaurus words to their google word equivalent and correlates matches in user input. The application uses a suite of parsers to 
perform the user selected action from the main menu. User direct input mode allows the user to type direct input to the console and get the translated version as output. 
Translated output will be shown in cyan in the console while any untranslated word will be shown in red. For file input the application takes parameters for the file to be 
translated, the google word or simplified words file, the thesaurus file and then asks for a name for the output translation file. The application requires the file input 
parameters in the form of the path/to/file and it requires any name you like as a string for the output text file which will be placed in the current directory. 
The main UI for this application is a showMenu() loop which runs until the user selects the option to quit. The program then farewells the user before shutting down. 
The main method for the application resides in the Runner Class. 

Instructions

To use the text simplifier if compiled from source in the command line.

cd into the source directory -  

cd src/ (may be different slash for mac users).

From the source directory type: java ie.gmit.dip.Runner

If launching from the Jar file use:
java –cp ./simplifier.jar ie.gmit.dip.Runner

Usage
From the main user menu select an option 1 – 3.
Option 1: allows direct user input mode and writes output to the console. Select this option and enter your input when prompted and use chars < ## > or double hash in English, 
to terminate input stream on the next input line. Note for default mode to work you will need the requirement stipulated files in the current directory. 
“assume that the list of 1000 Googlewords and the thesaurus are stored in the current directory and accessible as ./google-1000.txtand ./MobyThesaurus2.txt.”

Option 2: allows for file input parameters to be given. After selecting this option the user will be prompted to enter the path/to/file for the translation target as well as the 
simplified words file and thesaurus file. You will then be prompted for a name for the output file which is taken as a simple user input String. The translated file with your 
chosen filename will then be written to the current directory. If you fail to provide or provide incorrect parameters, the application will generate an error indicative of the 
issue and prompt the user to try again. Otherwise it will inform the user of the successful file creation after outputting the translated version to console.

Option 3: shuts down the application.
