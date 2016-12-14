# ArendVanDormalen-pset6
Final assignment for Native App Studio, a course on the University of Amsterdam.

This app enables users to search in the collection of the Rijksmuseum in Amsterdam using their API. 
Users can sign in in order to save favorite artworks. 
Registering, signing in and saving/retrieving favorites is done with Firebase.

Database queries are done with AsyncTasks in order to avoid freezing the UI.
In these AsyncTasks an HttpRequestHelper is called.
Lists for displaying search results and favorite artworks are filled through a custom Adapter.

Several Activities exist. 
The user starts on a home screen, which links to a log-in screen and a screen where database queries can be entered.
Filling in the log-in screen (through either logging in or registering) leads to a screen where favorites are shown.
Clicking on one of the items in the list in the search or favorites screen will open an activity where a single artwork is shown with detailed information.
Here an item can be favorited or unfavorited.

---
Peer review feedback(13-12-2016):

Ik heb naar je app bestanden gekeken en het ziet er op het eerste opzicht goed uit! 
Goed gestructureerd en het ziet er gestroomlijnd uit. 
Ik vond het wel heel moeilijk te begrijpen wat waar gebeurt omdat er weinig comments bij staan en je veel verschillende bestanden hebt. 
Ik raad je aan om een stuk meer comments te gebruiken met waarom je code gebruikt (mijn eigen richtlijn is na elke witregel een comment). 
Omdat je veel bestanden hebt zou ik wanneer je een readme maakt daar ook duidelijk inzet welke bestanden wanneer waarnaar verwijzen, misschien met een soort flowchart. 
Verder is het voor zover ik kon zien helemaal goed, ik kwam niks overbodigs of onhandigs tegen ofzo!
