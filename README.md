# objectdetectionandroid
Object Detection With Android

Case Study
Introduction:
This project is assigned by CSU to me for the final project in “Android App Development”. This application will run on Android platform which will detect fruits and their names through image processing. 
Explanation of Use Case 1:
1.	Name of Use Case: Take picture
2.	Description: User will take a still image of the fruit or group of fruits from an android phone camera through the app so that the user can browse it in next step for image processing to recognize their names. User will tap on “Take Picture” button to open the camera app.
3.	Trigger: The user sees fruits in the basket and opens the app (Sherlock’s Fruit) to take a picture. 
4.	Actors: 
a.	The app user:
The user opens the app (Sherlock’s Fruit) and take a picture to save the image in the phone’s storage.
5.	Stakeholders:
a.	Charles Sturt University:
CSU provides me the task to make an app that recognize fruits from an android app through image processing.
6.	Related Use Cases:
“Take picture again” use case will be invoked if the picture is blurry. So, the user has to retake image of the fruit.
7.	Pre-Conditions:
a.	Users phone storage is enough for the taken picture.
b.	Working camera app of the phone to take picture.
8.	Post-Conditions:
a.	Image is successfully browsed, and ready for image processing.
b.	Could not browse image.
c.	Wrong image is selected.
9.	Normal Flow:
This use case starts when user takes out his phone to take picture.
Actor	Application
1.	User opens the app and taps on “Take Picture” to takes picture of fruits.	
	2.	Camera app will process the image and saves into the phone’s storage.

10.	Alternate Flows:
a.	User takes multiple pictures of fruits:
Normal Flow’s step 1 and 2 is repeated.

b.	User deletes the captured picture:
Actor	Application
1.	User browse into the phone’s gallery.	
	2.	Gallery application is opened on the phone’s system.
3.	User selects the image to delete.	
4.	User choose to delete the image.	
	5.	System process the task and deletes the image from the phone’s storage.

11.	Exception Flows:
a.	Camera application crashes:
After the user opens the camera application it shows an error message like, “Camera app stopped”.
b.	Gallery app crashes:
When user tries to open the gallery application system will show an error message like, “Gallery app stopped”.

12.	Key Scenarios:
a.	Image is successfully captured.
b.	Image cannot be captured because of technical issues
13.	Other Quality Requirements:
a.	Easy user interaction to open camera app within the application.

Explanation of Use Case 2:
“Take picture again” use case is invoked when the user takes a blurry image or fails to store image in the phone’s storage or due to various reasons. This use case is same as use case 1 explained above. 

Explanation of Use Case 3:
1.	Name of Use Case: Browse Image
2.	Description: User will browse image through the app from phone’s storage after the first use case is completed successfully.
3.	Trigger: User finishes taking picture successfully.
4.	Actors: 
a.	The app user:
The user taps on to the “Browse Image” button from the app to choose the image.
5.	Stakeholders:
a.	Charles Sturt University:
CSU provides me the task to make an app that recognize fruits from an android app through image processing.
6.	Related Use Cases:
“Take picture again” use case will be invoked if the picture is blurry. So, the user must retake image of the fruit and browse it again.
7.	Pre-Conditions:
a.	The image is not blurry or clear enough for image processing.
8.	Post-Conditions:
a.	Image is successfully browsed, and ready for image processing.
b.	Could not browse image.
c.	Image not found.
9.	Normal Flow:
This use case starts when user captures the image and the image is stored on the phone’s storage.
Actor	Application
1.	User opens the app and tap on “Browse Image” button.	
2.	User will select the specific image from the specified directory.	
	3.	Application will show the image in the “Image Preview”.

10.	Alternate Flows:
a.	User selects the wrong image:
Normal Flow’s step 1, 2 and 3 is repeated.

b.	User cancels the process:
Actor	Application
1.	User closes the app.	
	2.	Application responds to close function.

11.	Exception Flows:
a.	The application fails to browse the image because of application or system failure.
12.	Key Scenarios:
a.	Image is successfully browsed and previewed in the “Image Preview”.
b.	Image could not be browsed because of application crash.
13.	Other Quality Requirements:
a.	Previews the image for review before processing.
 
Explanation of Use Case 4:
1.	Name of Use Case: Recognize the fruit
2.	Description: User will tap the “Recognize Fruit” button to let application process the image and display its name in the text box.
3.	Trigger: The user browses the image successfully. 
4.	Actors: 
a.	The app user:
After the above two use case, the user will tap the “Recognize Fruit” button for further development.
5.	Stakeholders:
a.	Charles Sturt University:
CSU provides me the task to make an app that recognize fruits from an android app through image processing.
6.	Related Use Cases:
“Take picture again” and “Browse Image” use case will be invoked if the picture is blurry or no picture was taken successfully. So, the user has to retake image of the fruit, browse it and tap “Recognize Fruit” again.
7.	Pre-Conditions:
a.	Image is browsed and previewed in the “Image Preview” section.
8.	Post-Conditions:
a.	Image is successfully processed, and fruits are recognized.
b.	Could not recognize fruits.
c.	Fruits recognized but with wrong name.
9.	Normal Flow:
This use case starts when user browse the image and image is previewed successfully in the app.
Actor	Application
1.	After the above three use cases is complete, the user will tap the “Recognize Fruit” button.	
	2.	Application will process the function and image is processed.
	3.	After successful image processing, name of fruit will be displayed in the text box.

10.	Alternate Flows:
a.	Image fails to process due to picture quality:
Use case 1, 2, 3 and 4 is repeated.

b.	User choose to discard the process:
Actor	Application
1.	User closes the application.	
	2.	Application will close as the close application function is invoked.

11.	Exception Flows:
a.	Image fails to recognize fruit as unknown fruit or because of blurry image.
12.	Key Scenarios:
a.	Image is successfully processed, and fruit is recognized.
b.	Image fails to recognize fruit.
13.	Other Quality Requirements:
a.	Displays result in percentage of how accurate the image is processed.
![image](https://github.com/goliathp/objectdetectionandroid/assets/59287096/16d980d2-5614-428a-b4e2-7f6ff7d6b8c4)
