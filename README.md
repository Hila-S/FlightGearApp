# flightGear App

link to github - https://github.com/Hila-S/FlightGearApp.git  
link to an explanatory video - https://youtu.be/7NN0AZxEZt0  

# Summary:
This App connects to the flightGear flight simulator and allows us to control the rudders and the movement of the plane.  

# Prerequisites:
'android studio' environment to run the code.

# Installing:
Download and install the simulator on your computer https://www.flightgear.org/download/  
Config the following settings in the 'Settings' tab in the simulator:  
--telnet=socket,in,10,127.0.0.1,6400,tcp - the IP should be the intern IP of the computer.  
This will open communication socket - 'in' where you send commands to the simulator.  

# Running:
1. Downloads the project by git clone from - https://github.com/Hila-S/FlightGearApp.git  
2. In the flightGear click on 'Cessna C172P' and then 'Autostart'  
3. Run the program.  
4. You can now control the movement of the plane by moving the joystick and slides  

# Documentation and general explanation of the structure of the folders and main files in the project:
The project designed by “MVVM” architect.  
The View is the ‘main activity and he contain the ‘JoyStick’ that Showing the main controls of the plane.  
The view model is the connecting layer between the model and the view.  
We have one model, “FGPlayer”, that responsible for all the logic of the application.  
The view model and the model has data-binding between them, and also the view and the view model.   
this means that every time something changes in the view this updates the view model and the opposite, and every time something changes in the view model he immediately updates the model and the opposite.  

# Collaborators
This program was developed Hila Shechter and Liron Weizman, CS students in Bar-Ilan university, Israel.

