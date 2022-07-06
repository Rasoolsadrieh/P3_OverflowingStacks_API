# Project 3 - Overflowing Stacks

## Project Description:
Overflowing Stacks is an upcoming banking application that allows users to send money from one account to another account. Users will be able to connect the bank account to our site to access their balance. This application would be good for the person who wants to have an account to accept money from others. An example would be a Landlord having an account and his tenets sending him money to his account every month. 

## Technologies Used:
Back-end:
- Java 8 version 1.8.0_322
- Spring
- Spring-boot
- Lombok
- Azure
- Kotlin
- Zxing
- Taimos


Front-end:
- React JS  version 18.2.0
- Node js version 16.15.0
- Docker
- MUI
- Axios
- React Qr code
- React Router


## Users can:

- Register and log in
- Edit their profile bio
- Post images and text to their profile
- Send and Receive Money
- Reset password

## Getting Started
Install JDK 8, Maven, Git, and NPM.
Repository Clone
Clone the frontend and backend using the following commands:
- git clone https://github.com/Revature-Overflowing-Stacks/P3_OverflowingStacks_API
- git clone https://github.com/Revature-Overflowing-Stacks/P3_OverflowingStacks_FE

Setting Up Azure

First you will need to create an account with Azure

You will need to input your credit card to sign up for a free account.(you can remove this later)

With the naming convention on the thing you will create in Azure can be named anything and it will say you can name it anything. But if you want to have a structured naming convention then just name each thing you create in Azure with OverflowingStacks and variations of it as you can have duplicate names for the same database for example.


## FrontEnd

For front end you need to create a container registry (name it what you like and make sure it is using the basic SKU)

It will ask you for a resource group go ahead and create one and anytime it asks you to add a resource group add the one you create now

In the container registry you need to go into settings and enable admin user

Once that is completed you will need to create an app service (name it what you like and make sure it is using the free SKU also). For this app service you are going to add in the instance details sections that you will publish in a docker container and the OS is Linux. After that you can hit review and create. Make sure the app service is the free tier then go ahead and create your app service.

When that has deployed go ahead and hit the go to resource button

Once there go to deployment center:
- In source make sure it is set to Container Registry
- In Registry Setting
- Registry source is Azure Container Registry
- Registry is the registry container you created
- For Image and tag(Azure should populate this for you)

Once all of those setting look right hit save at the top and Azure will start deploying your front-end


Once Azure has deployed you still need to deploy the backend before you can start making requests



## Backend
Now you must connect your Backend with an Azure Account that connected the Font-end

First you need to SQL database you can name this whatever you like 

For the SQL database you will need to create a new server which you can name whatever you like

For the Login and password make sure to have something easily memorable.

Once you have a login and password click on ok and it will take you back to the creation for the database resource 

Next you need to set the compute and storage to the basic tier

After that make the Backup storage redundancy to locally

Then go to the networking tab

In the networking tab make the networking connectivity to public endpoint. Once you click that in the firewall rules right under it will pop up two rules you can say yes turn both them to yes. Then you can create your database server


Once the server and database is created you will be able to create an app service for your API

One thing to note on the server for your database you need to add in the networking setting on the resource itself a new Firewall rule where you add a openAll with 0.0.0.0 in the Start IPv4 address and in the End IPv4 address  put 255.255.255.255

So for creating an app service for your API it is the almost the same set up for the front-end but just a little different

In the instance Details you will want to select code for the publish the for the runtime stack you want to select Java 8 and the OS will be Linux and Region can be what best for your group. After that make sure it is on the Free tier and you can create the app service. 

Once it is deployed click on the go to resource button

First you need to set up some configuration so go to the configuration tab on the left or you can search for it on the left. Once in the configuration tab you need what is listed below:

- DBPASS = the password of your database
- DBUSER = the username of your database
- DBURL = the URL of your database which can be gotten from DBeaver it should look like this: jdbc:sqlserver://;serverName=;databaseName=;encrypt=true if you can’t find the link in for the server name put the server name that is located on dbeaver and the database name is your schema of your server.

Once complete go to the deployment center in the app service

You are going to deploy with Github actions so click on the Github actions and connect your Github account to Azure

Once your Github account is connected it will allow you to choose the organization that you pulled from then you can select the repository and select the branch you want to deploy on being updated.

Hit save and Azure will be automatically deploying the API for you.




## Frontend instructions

Make sure you have React JS version 18.2.0 and Node js version 16.15.0 installed.

Use “git clone https://github.com/Revature-Overflowing-Stacks/P3_OverflowingStacks_FE.git”

Next you will need to navigate inside the cloned repo and run “npm i” to install all the needed packages

“Npm start” will then start the application

You should now be able to connect to the UI at the url provided by the console

Users will need to register 

We are using Google auth for authentication. Users will need to download an auth service so that 
they can input the secret code.

Users will then login using their authenticator app




## Backend instructions

Download Intellij community edition  as this was the IDE we used and if you already have it downloaded all you need to do is open the project when you cloned it down to your system. In Intellji go to the project settings to make sure the SDK is 1.8 and the Java Version is 1.2.0_322 as this is the java version we leveraged. Make sure it is a maven project and all of the dependencies are loaded. If the plugin for sprint boot is red it should be fine as it was red for all of us and it still ran so don’t worry about it. Once this is complete you are ready to code on the API.
