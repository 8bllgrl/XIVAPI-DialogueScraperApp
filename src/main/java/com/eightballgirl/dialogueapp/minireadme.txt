This is a placeholder.

This is just so that I don't forget what these versions do, before I make a full README on the subject.

Version 1:
This version of the application lets you see both the speaker and lines spoken about a character. This is the standard
format that I envisioned this program to behave. You input a name, and it searches for every instance of that name and
separates it into two sections: Every time the character has spoken, and every time the character has been spoken about.
Every time the character has spoken will be at the top and is recognized by the 'All caps' listing of the name. This is
true for all of the CSV files.
Row 1 describes the character who is speaking at the end of the string in all capital letters. This row also displays
which csv file the dialogue is taken from.
Row 2 is what the character declared in row 1 is saying. It is the dialogue that the character speaks in either a cut-
scene or a quest.

This writes a .txt file beginning with the character's name in all caps, and then suffixed by _dialogue.txt.

Version 2:

This version of the application has a simpler function. Once you input a name, it will search for every line of dialogue
that character has spoken amongst every quest and cutscene CSV file provided.

This writes a .txt file beginning with the character's name in all caps, and then suffixed by _dialogue.txt.

PLEASE NOTE:
For this version, please specify where you would like the file to be located at line 209.
