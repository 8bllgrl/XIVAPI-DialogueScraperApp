# XIV Dialogue Inspector

<p align="center">
    <img src="https://i.imgur.com/wb0jY3i.png" width="1000" alt="Dialogue Scraper Banner">
</p>

## Find information from FFXIV

XIV Dialogue inspector is a program that allows you to search for dialogue from within the multiplayer online role-playing game Final Fantasy XIV developed and published by [Square Enix](https://en.wikipedia.org/wiki/Square_Enix). This program is used to locate words, most likely nouns, inside of the dialogue files of the game. The results of this program are in chronological order. 

This program uses resources sourced from [xivapi](https://xivapi.com/) in order to work.

## A game file scraper specifically tailored towards character dialogue

⚠️Please use IntelliJ or Eclipse to use this program for the time being. My contacts are at the bottom of this README if you need assistance. I’d be happy to help you. 

By putting in a word through the console, This program let’s you easily search for information within the dialogue files of the game and receive an output .txt file containing a long list of every instance of that search. Dialogue files are formatted inside of CSV files, also known as Comma Separated Values. This program uses this information to determine the speaker, and what they are saying. 

## In regards to NpcYell.csv

Unfortunately, NpcYell.csv is not used inside of this program. This is due to the fact that this file does not contain any character name associations inside of it, therefore no information of who is speaking can be gathered from it. I apologize for this inconvenience. For this case, I recommend you take a look at either the [NpcYell.csv from xivapi](https://github.com/xivapi/ffxiv-datamining/blob/master/csv/NpcYell.csv), or  using the site [lore search](https://loresearch.net/). 

This feature is incomplete, but I have started on replacing any instance of the warrior of light’s name with a gender neutral term. [WARRIOR OF LIGHT] and [he/she] or [sir/madam] will be used in place of code that determines the player character’s gender and name. Keep in mind, this is incomplete, and there are some errors regarding this feature.


## Why?

If you are a roleplayer, or wish to use this program to find all dialogue of a specific character, I recommend you look into using Version 2 of this program. 

This is for people who are looking for a way to look at the dialogue of FFXIV. It is good for roleplayers, lore searching, and finding information from the MMORPG Final Fantasy 14. You can also use this to find quotes from the game. 

Features:

- Reads the Dialogue CSV files of Final Fantasy 14.
- Chronological order
- Sorts through all dialogue in the game except for the NpcYell.csv.
- Allows for the user to search for all dialogue of a specific character, or to search for all instances of a word.

## How to install 

This java program was made with the amazon Corretto 16 version of java. ZIP downloads will be available soon.

Currently to run these programs it is best to close the repository onto your computer by using git, and then opening the project in a program such as IntelliJ or Eclipse. At some point I will create ZIP files for this project that include the .exe and/or jar files for easier usage. [Guide on how to clone a repository.](https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository) This program uses java [amazon corretto 16](https://docs.aws.amazon.com/corretto/latest/corretto-16-ug/downloads-list.html), make sure you have java downloaded on your computer. You can install this version of java through a program like [IntelliJ](https://www.jetbrains.com/idea/) which has a built in function to download java JDKs. If you need assistance, refer to my contacts at the bottom of this README!

## How to use

Inside of this program there are two folders. Version 1, and Version 2. 

*The output of the files generated through this program are stored inside of the “output” folder inside of the /src. ( located  at ‘XIVAPI-Dialogue-Scraper/src/main/java/com/eightballgirl/dialogueapp/output/’ )*


This program is NOT LENIENT on the type of input you pass into it. Misspells will not be corrected for you. If the character you are looking up has special characters, such as Y’shtola or G’raha tia, try looking up “Graha” or “Yshtola” instead.

### Version 1

Any word can be passed into Version 1. The program will only give an output if any dialogue in the game has been found to contain the word that has been typed in.

Version 1  is good for looking when a specific object or any sort of noun has been mentioned in the game, as well as who mentioned it.

Examples of words that will give a proper output for Version _ 1 :

- Estinien
- Sharlayan
- Viera
- Carrot

### Version 2

For version 2 of this program, you should be putting in a character name as an input. 

If you want accurate results with Version 2 of this program, put in a character’s name. Using race names may work if the character is anonymous, however it works best if you use a character that has a specified name.

Version 2 is strictly for the purpose of finding the dialogue of a specific character that has been passed in. It will not look for instances of the character being spoken of. This will not work for nouns that do not match up with character names. (unless this character’s name contains that word.)

Examples of words that will give a proper output for Version _ 2 :

- Yshtola
- Estinien
- Alphinaud
- Raubahn

Words that will NOT give a proper output for Version _ 2:

- Carrot

All files created with the Version 2 of the program will have the suffix “_dialogueAbridged”. All files created from the Version 1 of this program will end with the suffix “_dialogue”.

## Contributions

You may use anything in this code for your own purposes, and copy any bit of code for your own projects. However, this code as a whole has been created by me, and you should not claim it as your own. If you wish to add contributions, contact me through discord or send me an email.

Email: its.sagebelknap@gmail

Discord: XyzzY🛸#4675

Discord ID: 410254652391555123



Built with [https://xivapi.com/](https://xivapi.com/) . ALL FINAL FANTASY XIV CONTENT IS PROPERTY OF SQUARE ENIX CO., LTD
