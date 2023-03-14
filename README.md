Welcome to Falcon Project
---

<h3>Reporting 15/03</h2>

<p>Before starting project, i've decided to connect a H2 Database to save Falcon informations.<br>
That's tables: <br>
-> MESSAGE_USER(NAME varchar(255), ID integer, MESSAGE varchar(255), POST_DATE timestamp, REPLY_ID integer, REPLY_MESSAGE varchar(255))<br>
-> USERNAME(NAME varchar(255)) <br>

After initialize my db, I've started to pull TP5 to have a selector server. Then, I created a class named MessageManagement. <br>
His role is to understand client request when the connection is begin (take a look to see his functionalities). <br>
The heart of this class is 'commandAnalyse' methodµ. First of all, program split command sentence to extract features and then, <br>
move to the different options. <br></p>

Options done: <br>
-
<p>
    - PUBLISH <br>
    - RCV_MSG <br>
    - REPUBLISH <br>
    - REPLY <br>

All these option were been tested in the 'test' package and their Client were been created.
</p>

To be continued: <br>
-

I'm created the 'Gestion des flux' art but not finished ...






