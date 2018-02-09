package com.huawei.www;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Map;

public class MongoJClient {
    public static String host = "localhost";
    public static int port = 27017;
    public static MongoClient client = new MongoClient(host, port);

    public static MongoDatabase getDB(String dbname) {
        return client.getDatabase(dbname);
    }

    public static void delDB(String dbname) {
        client.dropDatabase(dbname);
        System.out.println("删除" + dbname);

    }

    public static void listDB() {
        MongoIterable<String> dbs = client.listDatabaseNames();
        for (String name : dbs
                ) {
            System.out.println(name);
        }
    }

    public static void createCollection(String db, String cname) {
        MongoDatabase database = getDB(db);
        database.createCollection(cname);
        System.out.println("创建" + cname + " in " + db);
    }

    public static void listCollections(String db) {

        MongoDatabase database = getDB(db);
        MongoIterable<String> strings = database.listCollectionNames();
        for (String s : strings
                ) {
            System.out.println(s);
        }
    }

    public static MongoCollection<Document> getCollection(String db, String cname) {
        MongoDatabase database = getDB(db);
        return database.getCollection(cname);
    }

    public static void delCollection(String db, String cname) {
        getCollection(db, cname).drop();

    }

    public static void listDocument(String db, String cname) {
        System.out.println("数据................");
        MongoCollection<Document> collection = getCollection(db, cname);
        FindIterable<Document> docs = collection.find();
        for (Document doc : docs) {
            String s = doc.toJson();
            System.out.println(s);
        }
    }

    public static void delDocument(String db, String cname, String key, String value) {
        MongoCollection<Document> coll = getCollection(db, cname);
        Document doc = new Document(key, value);
        coll.deleteOne(doc);
    }

    /**
     * 只能对值进行修改,key不同则增加字段
     * @param db
     * @param cname
     * @param okey
     * @param ovalue
     * @param key
     * @param value
     */
    public static void updateDocument_replace(String db, String cname, String okey, String ovalue, String key,String value) {
        MongoCollection<Document> coll = getCollection(db, cname);
        Bson old = Filters.eq(okey, ovalue);
        Bson xin = Updates.set(key,value);
        coll.updateOne(old,xin);


    }

    public static void updateDocument_add(String db, String cname, String okey, String ovalue, String key,String value) {
        MongoCollection<Document> coll = getCollection(db, cname);
        Bson set = Updates.set(key,value);
        Bson old = Filters.eq(okey, ovalue);
        coll.updateOne(old,set);

    }

    public static UpdateResult updateDocument_del(String db, String cname, String key, String value) {
        MongoCollection<Document> coll = getCollection(db, cname);
        Bson set = Updates.unset(key);
        return coll.updateOne(new Document(key, value), set);

    }

    public static void replaceDocument(String db, String cname, String okey, String ovalue, String key, String value) {
        MongoCollection<Document> coll = getCollection(db, cname);
        coll.replaceOne(new Document(okey, ovalue), new Document(key, value));

    }


    public static void insertDocument(String db, String cname, Map<String, Object> map) {
        MongoCollection<Document> coll = getCollection(db, cname);

        coll.insertOne(new Document(map));

    }
}
