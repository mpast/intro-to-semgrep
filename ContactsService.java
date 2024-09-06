package testcode.sqli;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;

public class ContactsService {

  private MongoDatabase db = MongoClientUtil.mongoClient.getDatabase("test");
  private MongoCollection<Document> collection = db.getCollection("contacts");

  public InsecureContactsService() {
    }

  public ArrayList<Document> basicDBObjectPut(String userName, String email) {
    BasicDBObject query = new BasicDBObject();
    // ruleid: mongodb-nosqli
    query.put("$where", "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"");

    MongoCursor<Document> cursor = collection.find(query).iterator();

    ArrayList<Document> results = new ArrayList<>();
    while (cursor.hasNext()) {
      Document doc = cursor.next();
      results.add(doc);
    }

    return results;
  }

  public ArrayList<Document> basicDBObjectPut(String userName, String email) {
    BasicDBObject query = new BasicDBObject();
    // ok: mongodb-nosqli
    query.put("$where", "this.sharedWith == \"CONSTANT\" && this.email == \"CONSTANT\"");

    MongoCursor<Document> cursor = collection.find(query).iterator();

    ArrayList<Document> results = new ArrayList<>();
    while (cursor.hasNext()) {
      Document doc = cursor.next();
      results.add(doc);
    }

    return results;
  }

  public ArrayList<Document> basicDBObjectPut(String userName, String email) {
    BasicDBObject query = new BasicDBObject();
    // ok: mongodb-nosqli
    query.put("sharedWith", userName);
    query.put("email", email);

    MongoCursor<Document> cursor = collection.find(query).iterator();
    ArrayList<Document> results = new ArrayList<>();
    while (cursor.hasNext()) {
      Document doc = cursor.next();
      results.add(doc);
    }

    return results;
  }

  public ArrayList<Document> basicDBObjectPutAll(String userName, String email) {
    BasicDBObject query = new BasicDBObject();
    HashMap<String, String> paramMap = new HashMap<>();
    // ruleid: mongodb-nosqli
    paramMap.put("$where", "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"");
    query.putAll(paramMap);

    MongoCursor<Document> cursor = collection.find(query).iterator();

    ArrayList<Document> results = new ArrayList<>();
    while (cursor.hasNext()) {
      Document doc = cursor.next();
      results.add(doc);
    }

    return results;
  }

  public ArrayList<Document> basicDBObjectPutAll(String userName, String email) {
    BasicDBObject query = new BasicDBObject();
    HashMap<String, String> paramMap = new HashMap<>();
    // ok: mongodb-nosqli
    paramMap.put("$where", "this.sharedWith == \"CONSTANT\" && this.email == \"CONSTANT\"");
    query.putAll(paramMap);

    MongoCursor<Document> cursor = collection.find(query).iterator();

    ArrayList<Document> results = new ArrayList<>();
    while (cursor.hasNext()) {
      Document doc = cursor.next();
      results.add(doc);
    }

    return results;
  }

  public ArrayList<Document> basicDBObjectPutAll(String userName, String email) {
    BasicDBObject query = new BasicDBObject();
    HashMap<String, String> paramMap = new HashMap<>();
    // ok: mongodb-nosqli
    paramMap.put("sharedWith", userName);
    paramMap.put("email", email);
    query.putAll(paramMap);

    MongoCursor<Document> cursor = collection.find(query).iterator();
    ArrayList<Document> results = new ArrayList<>();
    while (cursor.hasNext()) {
      Document doc = cursor.next();
      results.add(doc);
    }

    return results;
  }

  public ArrayList<Document> basicDBObjectAppend(String userName, String email) {
    BasicDBObject query = new BasicDBObject();
    // ruleid: mongodb-nosqli
    query.append("$where", "this.sharedWith == \"" + userName + "\" && this.email == \"" + email + "\"");

    MongoCursor<Document> cursor = collection.find(query).iterator();

    ArrayList<Document> results = new ArrayList<>();
    while (cursor.hasNext()) {
      Document doc = cursor.next();
      results.add(doc);
    }

    return results;
  }
}
