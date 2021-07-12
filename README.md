# SpringBootProject
This is an example of modeling of a construction company using:
  - Hibernate 
  - Spring Boot 
  - RemoteMySQL
  
We first model all the data that we wanted to store about our company and afterwards we use springboot 
to create an API that can create, read, update, delete on JSON or XML (depending on our preference).

Company situation description:

We want to design a database for a small construction company.
This company has employees, identified by their SSN, and for whom we know theirname, address, phone number and category (workman, official, etc.). 
The company carries out different jobs (constructions) for a series of clients. 
-> Each client has aunique code, a name, an address, a phone number and an account number. A client can have one or many constructions (we don’t store data about clients for which we don’t have constructions), and a construction belongs to only one client. 
-> Each construction is identified by a unique code, a short name, an address, a budget,and the construction’s state (Planning, PreConstruction, Construction, Closed-Out). 
-> We also want the model to reflect how many constructions are active for each client.
-> We have a assigned a license for each construction which has an id a issue date, an issuer and a state(approved, not approved). 
-> We also have an inventory of materials, so each material is identified by a code, has a name, a price, and the quantity of material we have in the warehouse. 
Finally, this database will also be used to schedule the daily work of each employee.Thus, we want to know which construction has each of our employees participated on,the work he/she has done (frames, bricks, etc.).  We assume each employee is only in one construction each day.

The application runs over Tomcat. It is a web server that can read and write to the database using the same uri. Depending on the method used it can Create(Post and JSON/XML body),Read(GET request) ,Update(PUT and JSON/XML body), Delete(DELETE ex: entity/{id}).In order to change from the json format to XML the user just have to change the headers values:
Content-Type: application/xml
Accept: application/xml

There are different paths to access different information:
/{entity} -> Retrieves(GET) all the users or adds(POST) a new one
/{entity}/{id} ->CRUD an specific entity item

Since we were interested in the active constructions of the client, I have coded a custom serializer (main.java.com.tools.ClientSerializer) 
for the user entity to get that atribute. In the case of the dates, I have done the same thing for it since we were only interested
in some atributes of the ZonedDateTime.

Custom queries added to this project:
 Employee Repository Class:
Gets all the employees given a category
@Query("select e from Employee e where e.category like :category")
   List<Employee> findAllBycategory(@Param("category") String category);

Construciton Repository Class
Gets all the constructions with out a license
@Query("select c from Construction c where c.state like 'License not approved'")
    List<Construction> getConstructionsWithOutLicense();

MaterialUsage Repository Class
Gets all the materials which have been used more than a given quantity in a particular construction
@Query("select m from MaterialUsage m where quantity >= :q")
List<MaterialUsage> materialUsageGreatherThan(@Param("q") String q);

License Repository Class
Get all the licenses issued by a particular license issuer
    @Query("select l from License l where l.issuer like :licenseIssuer")

Paginated Query
//Retrieve the elements with less stock
Material Controller Class
@GetMapping("/materials/min")
public List<Material> retrieve3MostScarcematerials() 
