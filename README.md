# Supply-Chain-Management-System

In this project we design an application to calculate the cheapest combination of available inventory items that can be used to fill a specific order. 
For example, if in a university, a faculty needs one executive chair, the application should be able to calculate the most cost-effective way of assembling the chair from other used furniture items. 
Perhaps one used chair only has usable legs and cushion foam, but another has a usable seat and arms. Together they can be used to create a new chair based on the costs of usable and available components.
Faculties should be able to order the suggested items. If the needed components are available, an order form should be generated and the furniture items should be removed from inventory. 
In addition to the financial benefits for the university, there are environmental benefits to reusing furniture: reuse prevents perfectly functional goods from ending up in the landfill, while conserving resources and materials required to manufacture brand new furniture. 
Ultimately, achieving this goal will help the environment while saving money, time and energy!

How it works:


https://user-images.githubusercontent.com/77895143/141569068-be48f9d4-c0ec-4f5c-a4fc-a60b8819507d.mp4


How it is made possible:


https://user-images.githubusercontent.com/77895143/141576909-213589f9-568e-4e35-9c50-38decf089845.mp4




In conlusions, our application does the following:

1.Takes in user input for 
a) a furniture category, b) its type, and c) the number of items requested.

2.Calculates and outputs the cheapest option for creating the requested pieces of furniture orspecify if the request is not possible to fill.
-Furniture items must be purchased as a whole. Single components cannot be purchased separately. 
-Each furniture item can only be built from items of the same type (e.g.mesh chair, desk lamp, etc.). Components are not interchangeable across types (including light bulbs).
-Even used furniture costs money! The price of an item will depend on its number of usable components and their condition.
-If there are multiple combinations for the cheapest price, select any one combination.

3.If the request can be filled, produce a formatted order form in a .txt format. 

4.When an order form is produced, the database should also be updated to specify that the selected items are no longer available in inventory.

5.If a request cannot be filled, the names of suggested manufacturers will be included in the output message. All possible manufacturers are included in each furniture table. Note that some of the manufacturers do not sell all furniture items

This project was completed by Kunal Dhawan, Arindam Mishra, Kaitlin Culligan in Winter 2021.

Note: The following project contains 3 jar files, inventory.sql which were supplied by Hack Your Learning/ Department of Electrical and Software Engineering, University of Calgary for this project in the course ENSF 409.


TLDR: This Java application calculates the cheapest combination of reusable inventory items that can be used to manufacture a specific order requested by user. It utilizes a MySQL database (inventory) provided by Hack Your Learning to extract data pertaining to the availability of reusable parts.
