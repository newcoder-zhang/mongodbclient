import com.huawei.www.MongoJClient;
import com.mongodb.client.MongoDatabase;

import java.util.HashMap;

public class Test {

    public static void main(String[] args) {
        String db="mydb";
        MongoDatabase data = MongoJClient.getDB(db);
        String cname="myc2";
        String id="_id";
        String idvalue="{ \"$oid\" : \"5a7d5d2663b04f16b456834a\" }";
       // MongoJClient.listDB();
//        MongoJClient.listCollections(db);
        MongoJClient.listDocument(db,cname);
//        MongoJClient.updataDocument(db,"myc2","name","zl","wuhan");
        HashMap<String,Object> map=new HashMap<String, Object>();
        map.put("name","lily");
        map.put("sex","female");
//        MongoJClient.insertDocument(db,"myc2",map);
//        MongoJClient.delDocument(db,"myc2","add","wuhan");
//        MongoJClient.replaceDocument(db,"myc2","name","lucy","sex","female");
        MongoJClient.updateDocument_replace(db,cname,"age","32","height","179");
//        MongoJClient.updateDocument_del(db,cname,"sex","female");
//        MongoJClient.updateDocument_add(db,cname,"sex","female","add","sh");
        MongoJClient.listDocument(db,cname);
    }
}
