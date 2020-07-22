# DevopsSpringBootApp

Here are the steps to Integrate and deploy a containerized Spring Boot Application with REST API and MYSQL database using Jenkins, Git, Maven  and AWS EC2. 

#### Overview:
We will see how to containerize our spring boot application having REST API calls and uses MYSQL database in the backend, by writing Dockerfile. We write our spring boot java code in Eclipse and load our code into a git repository. In the men while launch an EC2 instance by allowing securiy group port 80 to be opened. Launch jenkins on local machine, install plugins for maven , git and also aws ec2. Configure jenkins system configuration and global tool configurations with the details of maven, git and ec2. Later create a jenkins job to build the code and give us the jar file. We can also configure the job's post build step to deploy the jar file on ec2 once build is done. Login into your ec2 instance and make a docker file to execute the jar our jenkins gave. Build a image for our spring boot application using docker installed on our ec2. Later, pull the mysql image from docker hub, run it as a container, and initiate a test db where it can allow connection to your spring boot application. Finally run your spring boot image to make it a container and bind the port 8080 to 80 so that our container port will be mapped to the host port and our application can run on 80 port of ec2. 
Check the application on  http://yourec2publicip:80/yourpagename . Check the given api calls like POST , GET. 

##### Steps for Jenkins, EC2, MYSQL, Eclipse (oxygen) 
Steps to write a simple hello world program in eclipse oxygen:

Step1: Open eclipse -> create a new spring boot project . include dependencies spring web , Mysql and devtools and jpa, thymeleaf. 

Step2: Create a sample hello world program or just copy and clone my git hub url so that you can import my project structure in your application. 

Step3: My project contains one controller, one repository, one model, and 4 views which basically does these things: 
        takes the input parameters in http request from the user (using postman on post API request for /create page)
        saves them in mysql data base into a test db 
        up on the users request to get the details, displays the details from db with in a table in the page viewAll (Get API request on page /viewAll)
        
step4: write a application.properties file where you will give your user name and password for your db and include hibernate related credentials in it (see the file applcation.properties in the code above) also include the port no 3306 to make your mysql run on that port

Steps for jenkins installation and CI with Git, maven  

Step1: Install jenkins executable file on your local computer and give the admin password when it prompts for password. 

Step2: After loging in to the eclipse make sure that you change admin password for ease of use next time.

Step3: You can skip the step install plugins when asked at the time of installation. 

Now install required plugins as mentioned below but every time make sure you click the button "install with out restarting"

Step4: Go to manage jenkins->mange plugins-> install git hub plugin

Step5: Go to manage jenkins-> global tool configuration-> give the details of git -> you can give the path of git as /usr/bin/git or if you have the path of git where it is located, you can just give that. 

Step6: Again go back to manage jenkins page -> go to manage plugins -> install maven integration, maven info and maven deployment plugins. 

Step7: go to manage jenkins-> global tool configuration->scroll down-> in maven tab, ask jenkins to install maven automatically (just tick the check box)

step8: again go to manage jenkins->manage plugins install aws ec2 plugin -> go back to manage jenkins-> configure system -> scroll down -> you will find publish over ssh -> give the details where your ec2keypair.pem is located in your machine , paste the key in the key text box, give ssh server name as ec2server, hostname as your public dns name, and ec2-user as username, remote directory ec2-user

Step9: You are now good to build your job 

Step10: go to home page, click new item-> select maven project -> click ok

step11: it take you to a page to enter some details .. give source code managemnt as git -> give your git url where you have placed your code-> in build-> given clean package install-> in post build action select publish artifacts over ssh -> give your e2 details this is where the jar file will be sitting on your ec2. 
click ok and get out the job

step12: click on the job you have build and ask jenkins to build your job by cicking build now. 

step13: it will show you the log what it is doing in show console log button on your left side. click it and done your jar is now ready on ec2 machine

Steps for building docker images and running your application on ec2:
step1: launch your ec2 using ssh login command along with the key pair you have given in the jenkins and install docker on it and start your docker daemon. 

step2: locate your jar file and write a docker file in that directory (better if you write in the same directory or you can also give it in another directory but make sure you will give the correct path for the docker file to locate your jar file)

Step3: write a docker file as i have given in the code above (see Dockerfile)

step4: this docker file gets your jar file and executes it, it also pulls openjdk:8 image from the docker hub . 

step5: build the image by using command
###### docker build -t somenameforyourdockerimage . (. says that hey you have your docker file in this directory so take that and run my jar)
Step6: pull the mysql image from docker hub using this command
###### docker pull mysql:latest
step7: check whether all images are preset using this command
###### docker images
step8: chekc if there is any container running using this command
###### docker ps -a (or) docker container ls
step9: now run your mysql image with this command 
###### dokcer run --name mysql-standalone -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=test -e MYSQL_USER=username -e MYSQL_PASSWORD=password -d mysql
The above command says that connect to test schema using password as root password and create a db for me with the username and password that i have given in the spring boot appplication.properties file (which i am also mentioning the same here) and run this conatiner using mysql image which i have pulled from docker hub and run this container in background as a daemon

step10: Now run your spring boot appplication image with the command
###### docker run -p 80:8080 --name yourcontainername --link mysql-standalone:mysql yourdockerimagename
The above command says that , please run my image called yourdockerimagename on port no 80 (if my tomcat is running on 8080 then map 8080 to 80 so that i can run my application on 80 port) and if you want database connections, i have already run a conatiner for you you can just link that container from this image . 
Now if you want to see the application logs, you can use this command
###### docker container logs yourcontainername

Now go to postman and give a post request on http://yourec2publicdnsname:80/create
enter the details in the body text box(click raw to enter the details in json format) displayed below and make sure you give the headers as json (by default it will be text)

After entering couple of details, give get request on http://yourec2publicdnsname:80/viewAll
displays all the details you have entered. 
You can try the same url http://yourec2publicdnsname:80/viewAll on the browser to view the table format. 

##### Congrats !!! you have successfully deployed your containerized spring boot application with mysql db and performed CRUD operations on AWSEC2.

        
